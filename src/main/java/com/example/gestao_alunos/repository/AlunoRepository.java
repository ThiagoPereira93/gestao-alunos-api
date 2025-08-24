package com.example.gestao_alunos.repository;

import com.example.gestao_alunos.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
