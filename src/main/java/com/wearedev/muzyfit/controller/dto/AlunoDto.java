package com.wearedev.muzyfit.controller.dto;

import com.wearedev.muzyfit.entity.Aluno;
import com.wearedev.muzyfit.entity.AvaliacaoFisica;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

public record AlunoDto (
        Long id,
        String nome,
        String cpf,
        String rua,
        String numeroCasa,
        String bairro,
        LocalDate dataNascimento,
        List<AvaliacaoDto> avaliacoes) {

    public AlunoDto(Aluno model){
        this(
                model.getId(),
                model.getNome(),
                model.getCpf(),
                model.getRua(),
                model.getNumeroCasa(),
                model.getBairro(),
                model.getDataNascimento(),
                ofNullable(model.getAvaliacoes()).orElse(emptyList()).stream().map(AvaliacaoDto::new).collect(Collectors.toList())
        );

    }

    public Aluno toModel(){
        Aluno model = new Aluno();
        model.setId(this.id);
        model.setNome(this.nome);
        model.setCpf(this.cpf);
        model.setRua(this.rua);
        model.setNumeroCasa(this.numeroCasa);
        model.setBairro(this.bairro);
        model.setDataNascimento(this.dataNascimento);
        model.setAvaliacoes(ofNullable(this.avaliacoes).orElse(emptyList()).stream().map(AvaliacaoDto::toModel).collect(toList()));
        return model;
    }
}
