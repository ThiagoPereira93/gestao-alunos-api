package com.example.gestao_alunos.Controller;

import com.example.gestao_alunos.dto.AlunoReponse;
import com.example.gestao_alunos.dto.AlunoRequest;
import com.example.gestao_alunos.dto.MatriculaDTO;
import com.example.gestao_alunos.service.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService){
        this.alunoService =alunoService;
    }

    @PostMapping
    public ResponseEntity<AlunoReponse> criar(@RequestBody AlunoRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoService.salvar(request));
    }
    @GetMapping
    public List<AlunoReponse> listarTodos(){
        return alunoService.listarTodos();
    }
    @GetMapping("/{id}/matriculas")
    public List<MatriculaDTO> listarMatriculas(@PathVariable Long id){
        return alunoService.listarMatriculas(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoReponse> atualizar(@PathVariable Long id, @RequestBody AlunoRequest request){
        return ResponseEntity.ok(alunoService.atulizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id){
        alunoService.remover(id);
        return ResponseEntity.noContent().build();
    }

}
