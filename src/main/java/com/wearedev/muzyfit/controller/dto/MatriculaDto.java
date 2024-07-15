package com.wearedev.muzyfit.controller.dto;

import com.wearedev.muzyfit.entity.Matricula;

import java.time.LocalDateTime;

public record MatriculaDto(

        Long id,
        Long alunoId,
        LocalDateTime dataMatricula) {

    public MatriculaDto(Matricula matricula) {
        this(
                matricula.getId(),
                matricula.getAluno().getId(),
                matricula.getDataMatricula()
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
