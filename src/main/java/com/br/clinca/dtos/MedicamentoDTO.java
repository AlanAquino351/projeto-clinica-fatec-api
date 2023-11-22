package com.br.clinca.dtos;

import com.br.clinca.domain.Medicamento;

import java.io.Serializable;


public class MedicamentoDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nome;

	public MedicamentoDTO() {

	}

	public MedicamentoDTO(Medicamento obj) {
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
