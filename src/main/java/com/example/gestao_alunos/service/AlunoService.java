package com.example.gestao_alunos.service;

import com.example.gestao_alunos.dto.AlunoReponse;
import com.example.gestao_alunos.dto.AlunoRequest;
import com.example.gestao_alunos.dto.MatriculaDTO;
import com.example.gestao_alunos.entities.Aluno;
import com.example.gestao_alunos.entities.Matricula;
import com.example.gestao_alunos.mapper.AlunoMapper;
import com.example.gestao_alunos.repository.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final AlunoMapper alunoMapper;

    public AlunoService(AlunoRepository alunoRepository, AlunoMapper alunoMapper) {
        this.alunoRepository = alunoRepository;
        this.alunoMapper = alunoMapper;
    }

    public AlunoReponse salvar(AlunoRequest request) {
        Aluno aluno = alunoMapper.toEntity(request);
        alunoRepository.save(aluno);
        return alunoMapper.toResponse(aluno);
    }

    public List<AlunoReponse> listarTodos() {
        return alunoRepository.findAll().stream().map(alunoMapper::toResponse).toList();
    }

    public List<MatriculaDTO> listarMatriculas(Long id) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));
        return aluno.getMatriculas().stream().map(m -> new MatriculaDTO(m.getCodigoMatricula(), m.getNomeCurso(), m.getDataInicio())).toList();
    }

    public void remover(Long id) {
        if (!alunoRepository.existsById(id)) {
            throw new EntityNotFoundException("ID do aluno não encontrado");
        }
        alunoRepository.deleteById(id);
    }

    public AlunoReponse atulizar(Long id, AlunoRequest request) {
        Aluno a = alunoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));
        a.setNome(request.nome());
        a.setTelefone(request.telefone());
        a.setDataNascimento(request.dataNascimento());


        for (MatriculaDTO m : request.matriculas()) {
            Matricula matricula = new Matricula();
            matricula.setCodigoMatricula(m.codigoMatricula());
            matricula.setDataInicio(m.dataInicio());
            matricula.setNomeCurso(m.nomeCurso());
            a.getMatriculas().add(matricula);
        }
        return alunoMapper.toResponse(alunoRepository.save(a));
    }

}
