package com.br.clinca.services;

import com.br.clinca.domain.Atendimento;
import com.br.clinca.domain.Exame;
import com.br.clinca.domain.Medicamento;
import com.br.clinca.domain.Medico;
import com.br.clinca.domain.Paciente;
import com.br.clinca.dtos.AtendimentoDTO;
import com.br.clinca.dtos.ExameDTO;
import com.br.clinca.dtos.MedicamentoDTO;
import com.br.clinca.repositories.AtendimentoRepository;
import com.br.clinca.repositories.ExameRepository;
import com.br.clinca.repositories.MedicamentoRepository;
import com.br.clinca.repositories.MedicoRepository;
import com.br.clinca.repositories.PacienteRepository;
import com.br.clinca.services.exceptions.DataIntegratyViolationException;
import com.br.clinca.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AtendimentoService {

	@Autowired
	private AtendimentoRepository repository;

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private ExameRepository exameRepository;

	@Autowired
	private MedicamentoRepository medicamentoRepository;

	public Atendimento findAtendimentoById(Integer id) {
		Optional<Atendimento> atendimento = repository.findById(id);
		return atendimento.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Atendimento.class.getName()));
	}

	public List<AtendimentoDTO> findAllByMedico(String crm) {

		List<AtendimentoDTO> listDTO = new ArrayList<>();

		List<Atendimento> list = repository.findAllByMedico(crm);

		list.stream().forEach(a -> {
			listDTO.add(new AtendimentoDTO(a));
		});

		return listDTO;
	}

	public Atendimento create(AtendimentoDTO atendimentoDTO) {
		Medico medico = findMedicoByCrm(atendimentoDTO.getMedico().getCrm());
		Paciente paciente = findPacienteByCPF(atendimentoDTO.getPaciente().getCpf());

		Atendimento atendimento = new Atendimento();
		atendimento.setMedico(medico);
		atendimento.setPaciente(paciente);
		atendimento.setReceituario(atendimentoDTO.getReceituario());

		List<Exame> exames = findExames(atendimentoDTO.getExames());
		List<Medicamento> medicamentos = findMedicamentos(atendimentoDTO.getMedicamentos());

		atendimento.setExames(exames);
		atendimento.setMedicamentos(medicamentos);

		return repository.save(atendimento);
	}

	public Atendimento update(Integer id, AtendimentoDTO atendimentoDTO) {
		Atendimento existingAtendimento = findAtendimentoById(id);
		Medico medico = findMedicoByCrm(atendimentoDTO.getMedico().getCrm());
		Paciente paciente = findPacienteByCPF(atendimentoDTO.getPaciente().getCpf());

		existingAtendimento.setMedico(medico);
		existingAtendimento.setPaciente(paciente);
		existingAtendimento.setReceituario(atendimentoDTO.getReceituario());

		List<Exame> exames = findExames(atendimentoDTO.getExames());
		List<Medicamento> medicamentos = findMedicamentos(atendimentoDTO.getMedicamentos());

		existingAtendimento.setExames(exames);
		existingAtendimento.setMedicamentos(medicamentos);

		return repository.save(existingAtendimento);
	}

	public void delete(Integer id) {
		Atendimento existingAtendimento = findAtendimentoById(id);
		repository.delete(existingAtendimento);
	}

	private Medico findMedicoByCrm(String crm) {
		Medico medico = medicoRepository.findByCrm(crm);
		if (medico == null) {
			throw new DataIntegratyViolationException("Médico não existe na base de dados!");
		}
		return medico;
	}

	private Paciente findPacienteByCPF(String cpf) {
		Paciente paciente = pacienteRepository.findByCPF(cpf);
		if (paciente == null) {
			throw new DataIntegratyViolationException("Paciente não existe na base de dados!");
		}
		return paciente;
	}

	private List<Exame> findExames(List<ExameDTO> examesDTO) {

		List<Exame> exames = new ArrayList<>();

		examesDTO.forEach(exameDTO -> {
			Exame exame = exameRepository.findById(exameDTO.getId())
					.orElseThrow(() -> new DataIntegratyViolationException("Exame não encontrado na base de dados: " + exameDTO.getId()));

			exames.add(exame);
		});

		return exames;
	}

	private List<Medicamento> findMedicamentos(List<MedicamentoDTO> medicamentosDTO) {
		List<Medicamento> medicamentos = new ArrayList<>();
		medicamentosDTO.forEach(medicamentoDTO -> {
			Medicamento medicamento = medicamentoRepository.findById(medicamentoDTO.getId())
					.orElseThrow(() -> new DataIntegratyViolationException("Medicamento não encontrado na base de dados: " + medicamentoDTO.getId()));

			medicamentos.add(medicamento);
		});

		return medicamentos;
	}
}
