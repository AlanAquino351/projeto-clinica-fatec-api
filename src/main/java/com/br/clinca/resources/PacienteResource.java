package com.br.clinca.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.clinca.domain.Paciente;
import com.br.clinca.dtos.PacienteDTO;
import com.br.clinca.services.PacienteService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/paciente")
public class PacienteResource {

	@Autowired
	private PacienteService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<PacienteDTO> findById(@PathVariable Integer id) {

		PacienteDTO objDTO = new PacienteDTO(service.findById(id));

		return ResponseEntity.ok().body(objDTO);
	}

	@GetMapping
	public ResponseEntity<List<PacienteDTO>> findAll() {
		List<Paciente> list = service.findAll();
		List<PacienteDTO> listDTO = new ArrayList<>();

		for (Paciente obj : list) {
			listDTO.add(new PacienteDTO(obj));
		}
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<PacienteDTO> create(@Valid @RequestBody PacienteDTO objDTO) {
		Paciente newObj = service.create(objDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri(); // tranformar tudo em uri.
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<PacienteDTO> update(@PathVariable Integer id, @Valid @RequestBody PacienteDTO objDTO){
		PacienteDTO newObj = new PacienteDTO(service.update(id, objDTO));
		return ResponseEntity.ok().body(newObj);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}