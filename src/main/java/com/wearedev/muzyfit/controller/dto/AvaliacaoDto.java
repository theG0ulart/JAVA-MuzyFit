package com.wearedev.muzyfit.controller.dto;

import com.wearedev.muzyfit.entity.AvaliacaoFisica;

import java.time.LocalDateTime;

public record AvaliacaoDto(
        Long id,
        Long alunoId,
        LocalDateTime dataAvaliacao,
        Double peso,
        Double altura){

    public AvaliacaoDto(AvaliacaoFisica avaliacao){
        this(
                avaliacao.getId(),
                avaliacao.getAluno().getId(),
                avaliacao.getDataAvaliacao(),
                avaliacao.getPeso(),
                avaliacao.getAltura()
        );
    }

    public AvaliacaoFisica toModel() {
        AvaliacaoFisica avaliacao = new AvaliacaoFisica();
        avaliacao.setId(this.id);
        // A referência ao aluno deve ser ajustada no serviço, pois aqui temos apenas o ID
        avaliacao.setDataAvaliacao(this.dataAvaliacao);
        avaliacao.setPeso(this.peso);
        avaliacao.setAltura(this.altura);
        return avaliacao;
    }

}
