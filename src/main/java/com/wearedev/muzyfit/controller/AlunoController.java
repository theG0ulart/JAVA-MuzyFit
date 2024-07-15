package com.wearedev.muzyfit.controller;

import com.wearedev.muzyfit.controller.dto.AlunoDto;
import com.wearedev.muzyfit.controller.exception.ErrorResponse;
import com.wearedev.muzyfit.service.exception.BusinessException;
import com.wearedev.muzyfit.service.exception.NotFoundException;
import com.wearedev.muzyfit.service.impl.AlunoServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alunos")

public record AlunoController(AlunoServiceImpl alunoService) {

    @GetMapping
    public ResponseEntity<List<AlunoDto>> findAll(){
        try{
            var alunos = alunoService.findAll();
            var alunoDto = alunos.stream().map(AlunoDto::new).collect(Collectors.toList());
            return ResponseEntity.ok(alunoDto);
        } catch (Exception ex) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try {
            var aluno = alunoService.findById(id);
            return ResponseEntity.ok(new AlunoDto(aluno));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(404).body(new ErrorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(new ErrorResponse("Internal Server Error: " + ex.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AlunoDto alunoDto){
        try{
            var aluno = alunoService.create(alunoDto.toModel());
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/${id}")
                    .buildAndExpand(aluno.getId())
                    .toUri();
            return ResponseEntity.created(location).body(new AlunoDto(aluno));
        } catch (BusinessException ex) {
            return ResponseEntity.status(400).body(new ErrorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(new ErrorResponse("Internal Server Error: " + ex.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AlunoDto alunoDto){
        try {

            var updatedAluno = alunoService.update(id, alunoDto.toModel()); // Pode ser um método de update, ajuste conforme necessário
            return ResponseEntity.ok(new AlunoDto(updatedAluno));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(404).body(new ErrorResponse(ex.getMessage()));
        } catch (BusinessException ex) {
            return ResponseEntity.status(400).body(new ErrorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(new ErrorResponse("Internal Server Error: " + ex.getMessage()));
        }
    }
}

