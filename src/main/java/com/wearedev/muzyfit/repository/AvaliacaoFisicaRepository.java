package com.wearedev.muzyfit.repository;

import com.wearedev.muzyfit.entity.AvaliacaoFisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoFisicaRepository extends JpaRepository<AvaliacaoFisica, Long> {

    List<AvaliacaoFisica> findByAlunoId(Long alunoId);
}
