package com.wearedev.muzyfit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tab_alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @CPF
    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String rua;

    @Column(nullable = false)
    private String numeroCasa;

    @Column(nullable = false)
    private String bairro;

    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<AvaliacaoFisica> avaliacoes = new ArrayList<>();

}
