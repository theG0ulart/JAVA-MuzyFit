package com.wearedev.muzyfit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tab_avaliacoes")
public class AvaliacaoFisica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @CreatedDate
    private LocalDateTime dataAvaliacao = LocalDateTime.now();

    @Column(name = "peso_atual")
    private Double peso;

    @Column(name = "altura_atual")
    private Double altura;


}
