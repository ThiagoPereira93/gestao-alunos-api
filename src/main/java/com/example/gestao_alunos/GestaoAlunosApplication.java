package com.example.gestao_alunos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GestaoAlunosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoAlunosApplication.class, args);
	}

}
