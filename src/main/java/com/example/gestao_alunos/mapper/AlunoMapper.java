package com.example.gestao_alunos.mapper;

import com.example.gestao_alunos.dto.AlunoReponse;
import com.example.gestao_alunos.dto.AlunoRequest;
import com.example.gestao_alunos.dto.MatriculaDTO;
import com.example.gestao_alunos.entities.Aluno;
import com.example.gestao_alunos.entities.Matricula;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlunoMapper {
    public Aluno toEntity(AlunoRequest request) {
        Aluno aluno = new Aluno();
        aluno.setNome(request.nome());
        aluno.setDataNascimento(request.dataNascimento());
        aluno.setTelefone(request.telefone());
        List<Matricula> matriculas = request.matriculas().stream().map(m -> {
            Matricula matricula = new Matricula();
            matricula.setCodigoMatricula(m.codigoMatricula());
            matricula.setDataInicio(m.dataInicio());
            matricula.setNomeCurso(m.nomeCurso());
            matricula.setAluno(aluno);
            return matricula;
        }).toList();
        aluno.setMatriculas(matriculas);
        return aluno;
    }

    public AlunoReponse toResponse(Aluno aluno) {
        List<MatriculaDTO> matriculaDTOS = aluno.getMatriculas().stream().map(m ->
                new MatriculaDTO(m.getCodigoMatricula(), m.getNomeCurso(), m.getDataInicio())).toList();
        return new AlunoReponse(aluno.getId(), aluno.getNome(), aluno.getTelefone(), aluno.getDataNascimento(), matriculaDTOS);
    }
}
