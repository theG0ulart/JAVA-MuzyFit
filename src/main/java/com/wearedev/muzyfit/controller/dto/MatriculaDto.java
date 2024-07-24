package com.wearedev.muzyfit.controller.dto;

import com.wearedev.muzyfit.entity.Matricula;

import java.time.LocalDateTime;

public record MatriculaDto(

        Long id,
        LocalDateTime dataMatricula) {

    public MatriculaDto(Matricula model) {
        this(
                model.getId(), model.getDataMatricula()
        );
    }

    public Matricula toModel() {
        Matricula matricula = new Matricula();
        matricula.setId(this.id);
        // A referência ao aluno deve ser ajustada no serviço, pois aqui temos apenas o ID
        matricula.setDataMatricula(this.dataMatricula);
        return matricula;
    }

}
