package com.br.clinca.dtos;

import com.br.clinca.domain.Exame;

import java.io.Serializable;


public class ExameDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nome;

	public ExameDTO() {

	}

	public ExameDTO(Exame obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
