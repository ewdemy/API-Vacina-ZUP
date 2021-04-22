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

import com.mrcruz.controlevacina.exception.CpfExistenteException;
import com.mrcruz.controlevacina.model.Usuario;
import com.mrcruz.controlevacina.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario salvarUsuario(@Valid @RequestBody Usuario usuario) {
		if(repository.existsById(usuario.getCpf())) {
			throw new CpfExistenteException("CPF já cadastrado!");
		}
		return repository.save(usuario);
	}
	
	@GetMapping
	public List<Usuario> listar(){
		return repository.findAll();
	}
}
