package com.wearedev.muzyfit.service.impl;

import com.wearedev.muzyfit.entity.AvaliacaoFisica;
import com.wearedev.muzyfit.repository.AvaliacaoFisicaRepository;
import com.wearedev.muzyfit.service.IAvaliacaoFisicaService;
import com.wearedev.muzyfit.service.exception.BusinessException;
import com.wearedev.muzyfit.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class AvaliacaoFisicaServiceImpl implements IAvaliacaoFisicaService {

    private final AvaliacaoFisicaRepository avaliacaoFisicaRepository;

    public AvaliacaoFisicaServiceImpl(AvaliacaoFisicaRepository avaliacaoRepository) {
        this.avaliacaoFisicaRepository = avaliacaoRepository;
    }

    @Transactional
    public AvaliacaoFisica create(AvaliacaoFisica avaliacaoToCreate) {
        ofNullable(avaliacaoToCreate).orElseThrow(() -> new BusinessException("AvaliacaoFisica to create must not be null"));
        ofNullable(avaliacaoToCreate.getAluno()).orElseThrow(() -> new BusinessException("Aluno must not be null."));
        ofNullable(avaliacaoToCreate.getPeso()).orElseThrow(() -> new BusinessException("Peso must not be null."));
        ofNullable(avaliacaoToCreate.getAltura()).orElseThrow(() -> new BusinessException("Altura must not be null."));

        return this.avaliacaoFisicaRepository.save(avaliacaoToCreate);
    }

    @Transactional
    public AvaliacaoFisica update(Long id, AvaliacaoFisica avaliacaoToUpdate) {
        ofNullable(avaliacaoToUpdate).orElseThrow(() -> new BusinessException("AvaliacaoFisica to update must not be null"));
        ofNullable(avaliacaoToUpdate.getAluno()).orElseThrow(() -> new BusinessException("Aluno must not be null."));
        ofNullable(avaliacaoToUpdate.getPeso()).orElseThrow(() -> new BusinessException("Peso must not be null."));
        ofNullable(avaliacaoToUpdate.getAltura()).orElseThrow(() -> new BusinessException("Altura must not be null."));

        AvaliacaoFisica existingAvaliacao = this.avaliacaoFisicaRepository.findById(id).orElseThrow(NotFoundException::new);

        existingAvaliacao.setAluno(avaliacaoToUpdate.getAluno());
        existingAvaliacao.setPeso(avaliacaoToUpdate.getPeso());
        existingAvaliacao.setAltura(avaliacaoToUpdate.getAltura());
        existingAvaliacao.setDataAvaliacao(avaliacaoToUpdate.getDataAvaliacao());

        return this.avaliacaoFisicaRepository.save(existingAvaliacao);
    }

    @Transactional
    public void delete(Long id) {
        AvaliacaoFisica existingAvaliacao = this.avaliacaoFisicaRepository.findById(id).orElseThrow(NotFoundException::new);
        this.avaliacaoFisicaRepository.delete(existingAvaliacao);
    }

    @Transactional(readOnly = true)
    public List<AvaliacaoFisica> findAll(){
        return this.avaliacaoFisicaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public AvaliacaoFisica findById(Long id) {
        return this.avaliacaoFisicaRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<AvaliacaoFisica> findByAlunoId(Long alunoId) {
        return this.avaliacaoFisicaRepository.findByAlunoId(alunoId);
    }
}
