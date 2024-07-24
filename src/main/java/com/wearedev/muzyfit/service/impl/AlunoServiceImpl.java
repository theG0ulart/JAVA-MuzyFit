package com.wearedev.muzyfit.service.impl;

import com.wearedev.muzyfit.entity.Aluno;
import com.wearedev.muzyfit.entity.AvaliacaoFisica;
import com.wearedev.muzyfit.entity.Matricula;
import com.wearedev.muzyfit.repository.AlunoRepository;
import com.wearedev.muzyfit.repository.MatriculaRepository;
import com.wearedev.muzyfit.service.IAlunoService;
import com.wearedev.muzyfit.service.exception.BusinessException;
import com.wearedev.muzyfit.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class AlunoServiceImpl implements IAlunoService {

    private final AlunoRepository alunoRepository;
    private final MatriculaRepository matriculaRepository;


    public AlunoServiceImpl(AlunoRepository repository,
                            MatriculaRepository matriculaRepository) {
        this.alunoRepository = repository;
        this.matriculaRepository = matriculaRepository;
    }

    @Transactional(readOnly = true)
    public List<Aluno> findAll(){
        return this.alunoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Aluno findById(Long id) {
        return this.alunoRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public Aluno create(Aluno alunoToCreate) {
        ofNullable(alunoToCreate).orElseThrow(()  -> new BusinessException("Aluno to create must not be null"));
        ofNullable(alunoToCreate.getNome()).orElseThrow(() -> new BusinessException("Aluno name must not be null."));
        ofNullable(alunoToCreate.getCpf()).orElseThrow(() -> new BusinessException("Aluno CPF must not be null."));
        ofNullable(alunoToCreate.getRua()).orElseThrow(() -> new BusinessException("Aluno street must not be null."));
        ofNullable(alunoToCreate.getNumeroCasa()).orElseThrow(() -> new BusinessException("Aluno house number must not be null."));
        ofNullable(alunoToCreate.getBairro()).orElseThrow(() -> new BusinessException("Aluno neighborhood must not be null."));
        ofNullable(alunoToCreate.getDataNascimento()).orElseThrow(() -> new BusinessException("Aluno birthday must not be null."));

        if(alunoToCreate.getId() != null) {
            throw new BusinessException("Aluno ID must be null when creating a new entity.");
        }

        if(alunoToCreate.getAvaliacoes() != null){
            for(AvaliacaoFisica avaliacao : alunoToCreate.getAvaliacoes()) {
                avaliacao.setAluno(alunoToCreate);
            }
        }

        if(alunoRepository.existsByCpf(alunoToCreate.getCpf())){
            throw new BusinessException("CPF já cadastrado!");
        }


        Aluno savedAluno = this.alunoRepository.save(alunoToCreate);

        Matricula matricula = new Matricula();
        matricula.setAluno(savedAluno);
        matriculaRepository.save(matricula);

        savedAluno.setMatricula(matricula);

        return savedAluno;
    }

    @Transactional
    public Aluno update(Long id, Aluno alunoToUpdate) {
        ofNullable(alunoToUpdate).orElseThrow(() -> new BusinessException("Aluno to update must not be null"));
        ofNullable(alunoToUpdate.getNome()).orElseThrow(() -> new BusinessException("Aluno name must not be null."));
        ofNullable(alunoToUpdate.getCpf()).orElseThrow(() -> new BusinessException("Aluno CPF must not be null."));
        ofNullable(alunoToUpdate.getRua()).orElseThrow(() -> new BusinessException("Aluno street must not be null."));
        ofNullable(alunoToUpdate.getNumeroCasa()).orElseThrow(() -> new BusinessException("Aluno house number must not be null."));
        ofNullable(alunoToUpdate.getBairro()).orElseThrow(() -> new BusinessException("Aluno neighborhood must not be null."));
        ofNullable(alunoToUpdate.getDataNascimento()).orElseThrow(() -> new BusinessException("Aluno birthday must not be null."));

        Aluno existingAluno = this.alunoRepository.findById(id).orElseThrow(NotFoundException::new);

        if (!existingAluno.getCpf().equals(alunoToUpdate.getCpf()) && alunoRepository.existsByCpf(alunoToUpdate.getCpf())) {
            throw new BusinessException("CPF já cadastrado!");
        }

        existingAluno.setNome(alunoToUpdate.getNome());
        existingAluno.setCpf(alunoToUpdate.getCpf());
        existingAluno.setRua(alunoToUpdate.getRua());
        existingAluno.setNumeroCasa(alunoToUpdate.getNumeroCasa());
        existingAluno.setBairro(alunoToUpdate.getBairro());
        existingAluno.setDataNascimento(alunoToUpdate.getDataNascimento());

        if (alunoToUpdate.getAvaliacoes() != null) {
            existingAluno.getAvaliacoes().clear();
            for (AvaliacaoFisica avaliacao : alunoToUpdate.getAvaliacoes()) {
                avaliacao.setAluno(existingAluno);
                existingAluno.getAvaliacoes().add(avaliacao);
            }
        }
        return this.alunoRepository.save(existingAluno);
    }

    @Transactional
    public void delete(Long id) {
        Aluno dbAluno = this.findById(id);
        this.alunoRepository.delete(dbAluno);
    }
}
