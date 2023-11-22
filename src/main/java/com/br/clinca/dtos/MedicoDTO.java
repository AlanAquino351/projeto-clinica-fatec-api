package com.br.clinca.dtos;

import com.br.clinca.domain.Medico;
import com.br.clinca.domain.Paciente;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


public class MedicoDTO implements Serializable{
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

	@NotEmpty(message = "O campo crm é requerido")
	private String crm;

	public MedicoDTO() {

	}

	public MedicoDTO(Medico obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.telefone = obj.getTelefone();
		this.dataNascimento = obj.getDataNascimento();
		this.crm = obj.getCrm();
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

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}
}
