package com.example.gestao_alunos.dto;

import java.time.LocalDate;
import java.util.List;

public record AlunoReponse(Long id, String nome, String telefone, LocalDate dataNascimento, List<MatriculaDTO> matriculas) {
}
