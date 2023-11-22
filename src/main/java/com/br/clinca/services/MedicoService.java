package com.br.clinca.services;

import com.br.clinca.domain.Medico;
import com.br.clinca.domain.Pessoa;
import com.br.clinca.dtos.MedicoDTO;
import com.br.clinca.repositories.MedicoRepository;
import com.br.clinca.repositories.PessoaRepository;
import com.br.clinca.services.exceptions.DataIntegratyViolationException;
import com.br.clinca.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

	@Autowired
	private MedicoRepository repository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public Medico findMedicoById(Integer id) {
		Optional<Medico> medico = repository.findById(id);
		return medico.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrato! Id: " + id + ", Tipo: " + Medico.class.getName()));
	}

	public List<Medico> findAll() {
		return repository.findAll();
	}
	
	public Medico create(MedicoDTO medico) {
		if (repository.findByCrm(medico.getCrm()) != null) {
			throw new DataIntegratyViolationException("CRM já cadastrado na base de dados!");
		}

		if (pessoaRepository.findByCPF(medico.getCpf()) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		
		return repository.save(new Medico(null, medico.getNome(), medico.getCpf(), medico.getTelefone(), medico.getDataNascimento(), medico.getCrm()));
	}
	
	public Medico update(Integer id, @Valid MedicoDTO medico) {

		Medico oldObj = findMedicoById(id);

		Pessoa pessoaCpf = pessoaRepository.findByCPF(medico.getCpf());

		if(pessoaCpf != null && pessoaCpf.getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		Medico medicoCrm = repository.findByCrm(medico.getCrm());

		if(medicoCrm != null && medicoCrm.getId() != id) {
			throw new DataIntegratyViolationException("Crm já cadastrado na base de dados!");
		}

		
		oldObj.setNome(medico.getNome());
		oldObj.setCpf(medico.getCpf());
		oldObj.setTelefone(medico.getTelefone());
		oldObj.setDataNascimento(medico.getDataNascimento());
		oldObj.setCrm(medico.getCrm());
		
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		findMedicoById(id);
		repository.deleteById(id);
	}

	public Medico findUsuarioMedicoByCRM(String crm) {
		Optional<Medico> medico = Optional.ofNullable(repository.findByCrm(crm));
		return medico.orElseThrow(() -> new ObjectNotFoundException("Usuário não existe!"));
	}
}