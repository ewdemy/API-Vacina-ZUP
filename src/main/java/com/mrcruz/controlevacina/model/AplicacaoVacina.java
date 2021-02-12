package com.mrcruz.controlevacina.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.UniqueElements;

@Entity
public class AplicacaoVacina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	private String NomeVacina;
	
	@OneToOne
	@JoinColumn(name = "cpf_usuario", unique = true)
	private Usuario usuario;
	
	@NotNull
	private Date dataAplicacao;
	
	public AplicacaoVacina() {
		
	}

	public AplicacaoVacina(String nomeVacina, Usuario usuario, Date dataAplicacao) {
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

	public Date getDataAplicacao() {
		return dataAplicacao;
	}

	public void setDataAplicacao(Date dataAplicacao) {
		this.dataAplicacao = dataAplicacao;
	}
	
	

}
