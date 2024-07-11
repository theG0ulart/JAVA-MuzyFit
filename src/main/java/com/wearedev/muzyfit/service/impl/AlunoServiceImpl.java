package com.wearedev.muzyfit.service.impl;

import com.wearedev.muzyfit.entity.Aluno;
import com.wearedev.muzyfit.repository.AlunoRepository;
import com.wearedev.muzyfit.service.IAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoServiceImpl implements IAlunoService {

    private final AlunoRepository repository;


    public AlunoServiceImpl(AlunoRepository repository) {
        this.repository = repository;
    }

    public List<Aluno> findAll(){
        return null;
    }

    @Override
    public Aluno findById(Long id) {
        return null;
    }

    @Override
    public Aluno create(Aluno alunoToCreate) {
        return null;
    }

    @Override
    public Aluno update(Long id, Aluno alunoToUpdate) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
