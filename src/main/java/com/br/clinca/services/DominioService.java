package com.br.clinca.services;

import com.br.clinca.domain.Exame;
import com.br.clinca.domain.Medicamento;
import com.br.clinca.repositories.ExameRepository;
import com.br.clinca.repositories.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DominioService {

	@Autowired
	private MedicamentoRepository medicamentoRepository;

	@Autowired
	private ExameRepository exameRepository;

	public List<Medicamento> findMedicamentoAll() {
		return medicamentoRepository.findAllOrderedByName();
	}

	public List<Exame> findExameAll() {
		return exameRepository.findAllOrderedByName();
	}

}