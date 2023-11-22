package com.br.clinca.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.br.clinca.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.clinca.domain.Paciente;
import com.br.clinca.domain.Pessoa;
import com.br.clinca.dtos.PacienteDTO;
import com.br.clinca.repositories.PacienteRepository;
import com.br.clinca.services.exceptions.DataIntegratyViolationException;
import com.br.clinca.services.exceptions.ObjectNotFoundException;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public Paciente findById(Integer id) {
		Optional<Paciente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrato! Id: " + id + ", Tipo: " + Paciente.class.getName()));
	}

	public List<Paciente> findAll() {
		return repository.findAll();
	}
	
	public Paciente create(PacienteDTO pacienteDTO) {

		if (repository.findByCPF(pacienteDTO.getCpf()) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		if (repository.findByCNS(pacienteDTO.getCns()) != null) {
			throw new DataIntegratyViolationException("CNS já cadastrado na base de dados!");
		}

		return repository.save(new Paciente(null, pacienteDTO.getNome(), pacienteDTO.getCpf(), pacienteDTO.getTelefone(), pacienteDTO.getDataNascimento(), pacienteDTO.getCns()));
	}
	
	public Paciente update(Integer id, @Valid PacienteDTO pacienteDTO) {
			
		Paciente oldObj = findById(id);

		Paciente paciente;

		paciente = repository.findByCPF(pacienteDTO.getCpf());

		if(paciente != null && paciente.getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		paciente = repository.findByCNS(pacienteDTO.getCns());

		if (paciente != null && paciente.getId() != id) {
			throw new DataIntegratyViolationException("CNS já cadastrado na base de dados!");
		}
		
		oldObj.setNome(pacienteDTO.getNome());
		oldObj.setCpf(pacienteDTO.getCpf());
		oldObj.setTelefone(pacienteDTO.getTelefone());
		oldObj.setDataNascimento(pacienteDTO.getDataNascimento());
		oldObj.setCns(pacienteDTO.getCns());
		
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);
	}
}