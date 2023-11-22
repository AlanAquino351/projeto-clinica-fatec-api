package com.br.clinca.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Medico extends Pessoa {

	private String crm;

	@OneToMany(mappedBy = "medico", cascade = CascadeType.PERSIST)
	private List<Atendimento> atendimentos;

	public Medico() {}

	public Medico(Integer id, String nome, String cpf, String telefone, String dataNascimento, String crm) {
		super(id, nome, cpf, telefone, dataNascimento);
		this.crm = crm;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}
}