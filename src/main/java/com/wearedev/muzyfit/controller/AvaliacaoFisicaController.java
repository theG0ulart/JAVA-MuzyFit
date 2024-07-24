package com.wearedev.muzyfit.controller;

import com.wearedev.muzyfit.controller.dto.AvaliacaoDto;
import com.wearedev.muzyfit.controller.exception.ErrorResponse;
import com.wearedev.muzyfit.service.exception.BusinessException;
import com.wearedev.muzyfit.service.exception.NotFoundException;
import com.wearedev.muzyfit.service.impl.AlunoServiceImpl;
import com.wearedev.muzyfit.service.impl.AvaliacaoFisicaServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoFisicaController {

    private final AvaliacaoFisicaServiceImpl avaliacaoFisicaService;
    private final AlunoServiceImpl alunoService;

    public AvaliacaoFisicaController(AvaliacaoFisicaServiceImpl avaliacaoFisicaService, AlunoServiceImpl alunoService) {
        this.avaliacaoFisicaService = avaliacaoFisicaService;
        this.alunoService = alunoService;
    }

    @GetMapping
    public ResponseEntity<List<AvaliacaoDto>> findAll(){
        var avaliacoes = avaliacaoFisicaService.findAll();
        var avaliacoesDto = avaliacoes.stream().map(AvaliacaoDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(avaliacoesDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try {
            var avaliacao = avaliacaoFisicaService.findById(id);
            return ResponseEntity.ok(new AvaliacaoDto(avaliacao));
        } catch (NotFoundException ex){
            return ResponseEntity.status(404).body(new ErrorResponse(ex.getMessage()));
        }
    }

    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<?> findByAlunoId(@PathVariable Long alunoId){
        try{
            alunoService.findById(alunoId);
            var avaliacoes = avaliacaoFisicaService.findByAlunoId(alunoId);
            var avaliacoesDtos = avaliacoes.stream().map(AvaliacaoDto::new).collect(Collectors.toList());
            return ResponseEntity.ok(avaliacoesDtos);
        } catch (NotFoundException ex){
            return ResponseEntity.status(404).body(new ErrorResponse(ex.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AvaliacaoDto avaliacaoDto){
        try{
            var aluno = alunoService.findById(avaliacaoDto.alunoId());
            var avaliacao = avaliacaoDto.toModel();
            avaliacao.setAluno(aluno);
            var createdAvaliacao = avaliacaoFisicaService.create(avaliacao);
            return ResponseEntity.ok(new AvaliacaoDto(createdAvaliacao));
        } catch (NotFoundException ex){
            return ResponseEntity.status(404).body(new ErrorResponse(ex.getMessage()));
        } catch (BusinessException ex){
            return ResponseEntity.status(400).body(new ErrorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(new ErrorResponse("Internal server error" + ex.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AvaliacaoDto avaliacaoDto){
        try {
            var aluno = alunoService.findById(avaliacaoDto.alunoId());
            var avaliacao = avaliacaoDto.toModel();
            avaliacao.setAluno(aluno);
            var updatedAvaliacao = avaliacaoFisicaService.update(id, avaliacao);
            return ResponseEntity.ok(new AvaliacaoDto(updatedAvaliacao));
        } catch (NotFoundException ex){
            return ResponseEntity.status(404).body(new ErrorResponse(ex.getMessage()));
        } catch (BusinessException ex){
            return ResponseEntity.status(400).body(new ErrorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(new ErrorResponse("Internal server error" + ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            avaliacaoFisicaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.status(404).body(new ErrorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(new ErrorResponse("Internal Server Error: " + ex.getMessage()));
        }
    }
}
