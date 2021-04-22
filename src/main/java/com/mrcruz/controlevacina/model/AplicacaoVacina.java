package com.mrcruz.controlevacina.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class AplicacaoVacina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	private String NomeVacina;
	
	
	@OneToOne
	@JoinColumn(name = "cpf_usuario")
	private Usuario usuario;
	
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataAplicacao;
	
	public AplicacaoVacina() {
		
	}

	public AplicacaoVacina(String nomeVacina, Usuario usuario, LocalDate dataAplicacao) {
		this.NomeVacina = nomeVacina;
		this.usuario = usuario;
		this.dataAplicacao = dataAplicacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeVacina() {
		return NomeVacina;
	}

	public void setNomeVacina(String nomeVacina) {
		NomeVacina = nomeVacina;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDate getDataAplicacao() {
		return dataAplicacao;
	}

	public void setDataAplicacao(LocalDate dataAplicacao) {
		this.dataAplicacao = dataAplicacao;
	}
	
	

}
