package com.wearedev.muzyfit.service;

import com.wearedev.muzyfit.entity.Aluno;
import com.wearedev.muzyfit.entity.AvaliacaoFisica;

import java.util.List;

public interface IAvaliacaoFisicaService {

    public AvaliacaoFisica create(AvaliacaoFisica avaliacaoToCreate);

    public AvaliacaoFisica update(Long id, AvaliacaoFisica avaliacaoToUpdate);

    public void delete(Long id);

    public List<AvaliacaoFisica> findAll();

    public AvaliacaoFisica findById(Long id);

    public List<AvaliacaoFisica> findByAlunoId(Long alunoId);
}
