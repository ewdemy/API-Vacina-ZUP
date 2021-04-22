package com.mrcruz.controlevacina.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mrcruz.controlevacina.model.AplicacaoVacina;
import com.mrcruz.controlevacina.repository.AplicacaoVacinaRepository;

@RestController
@RequestMapping("/aplicacao")
public class AplicacaoVacinaController {
	
	@Autowired
	private AplicacaoVacinaRepository repository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AplicacaoVacina cadastrarAplicacao(@Valid @RequestBody AplicacaoVacina aplicacao) {
		return repository.save(aplicacao);
	}
	
	@GetMapping
	public List<AplicacaoVacina> listar(){
		return repository.findAll();
	}
	

}
