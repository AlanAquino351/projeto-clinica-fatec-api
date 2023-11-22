package com.br.clinca.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.br.clinca.domain.Paciente;


public class PacienteDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "O campo nome é requerido")
	private String nome;
	
	@CPF
	@NotEmpty(message = "O campo cpf é requerido")
	private String cpf;
	
	@NotEmpty(message = "O campo telefone é requerido")
	private String telefone;
	
	@NotEmpty(message = "O campo data de nascimento é requerido")
	private String dataNascimento;

	private String cns;
	
	
	public PacienteDTO() {
		
	}
	
	public PacienteDTO(Paciente obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.telefone = obj.getTelefone();
		this.dataNascimento = obj.getDataNascimento();
		this.cns = obj.getCns();
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCns() {
		return cns;
	}

	public void setCns(String cns) {
		this.cns = cns;
	}
}
