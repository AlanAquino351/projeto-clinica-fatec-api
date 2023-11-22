package com.br.clinca.resources;

import com.br.clinca.domain.Exame;
import com.br.clinca.domain.Medicamento;
import com.br.clinca.dtos.ExameDTO;
import com.br.clinca.dtos.MedicamentoDTO;
import com.br.clinca.services.DominioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/dominio")
public class DominioResource {

	@Autowired
	private DominioService service;

	@GetMapping("/exames")
	public ResponseEntity<List<ExameDTO>> findAllExames() {
		List<Exame> exames = service.findExameAll();
		List<ExameDTO> examesDTO = new ArrayList<>();

		for (Exame exame : exames) {
			examesDTO.add(new ExameDTO(exame));
		}

		return ResponseEntity.ok().body(examesDTO);
	}

	@GetMapping("/medicamentos")
	public ResponseEntity<List<MedicamentoDTO>> findAllMedicamentos() {
		List<Medicamento> medicamentos = service.findMedicamentoAll();
		List<MedicamentoDTO> medicamentosDTO = new ArrayList<>();

		for (Medicamento medicamento : medicamentos) {
			medicamentosDTO.add(new MedicamentoDTO(medicamento));
		}

		return ResponseEntity.ok().body(medicamentosDTO);
	}
}