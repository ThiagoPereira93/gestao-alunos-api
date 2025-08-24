package com.example.gestao_alunos.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Matricula extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigoMatricula;
    private LocalDate dataInicio;
    private String nomeCurso;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;


}
