package com.br.clinca.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Paciente extends Pessoa {

	private String cns;

	@OneToMany(mappedBy = "paciente", cascade = CascadeType.PERSIST)
	private List<Atendimento> atendimentos;

	public Paciente() {}

	public Paciente(Integer id, String nome, String cpf, String telefone, String dataNascimento, String cns) {
		super(id, nome, cpf, telefone, dataNascimento);
		this.cns = cns;
	}

	public String getCns() {
		return cns;
	}

	public void setCns(String cns) {
		this.cns = cns;
	}
}