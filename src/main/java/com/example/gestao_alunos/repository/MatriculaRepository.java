package com.example.gestao_alunos.repository;

import com.example.gestao_alunos.entities.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
}
