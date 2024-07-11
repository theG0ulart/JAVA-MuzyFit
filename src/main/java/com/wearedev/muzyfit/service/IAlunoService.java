package com.wearedev.muzyfit.service;

import com.wearedev.muzyfit.entity.Aluno;

import java.util.List;

public interface IAlunoService {

    public List<Aluno> findAll();

    public Aluno findById(Long id);

    public Aluno create(Aluno alunoToCreate);

    public Aluno update(Long id, Aluno alunoToUpdate);

    public void delete(Long id);
}
