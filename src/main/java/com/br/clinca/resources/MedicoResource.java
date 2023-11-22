package com.br.clinca.resources;

import com.br.clinca.domain.Medico;
import com.br.clinca.dtos.MedicoDTO;
import com.br.clinca.dtos.MedicoDTO;
import com.br.clinca.services.MedicoService;
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

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/medico")
public class MedicoResource {

	@Autowired
	private MedicoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<MedicoDTO> findById(@PathVariable Integer id) {

		MedicoDTO objDTO = new MedicoDTO(service.findMedicoById(id));

		return ResponseEntity.ok().body(objDTO);
	}

	@GetMapping
	public ResponseEntity<List<MedicoDTO>> findAll() {
		List<Medico> list = service.findAll();
		List<MedicoDTO> listDTO = new ArrayList<>();

		for (Medico obj : list) {
			listDTO.add(new MedicoDTO(obj));
		}
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<MedicoDTO> create(@Valid @RequestBody MedicoDTO objDTO) {
		Medico newObj = service.create(objDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<MedicoDTO> update(@PathVariable Integer id, @Valid @RequestBody MedicoDTO objDTO){
		MedicoDTO newObj = new MedicoDTO(service.update(id, objDTO));
		return ResponseEntity.ok().body(newObj);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/usuario/{crm}")
	public ResponseEntity<MedicoDTO> findUsuarioMedicoByCRM(@PathVariable String crm) {
		MedicoDTO objDTO = new MedicoDTO(service.findUsuarioMedicoByCRM(crm));
		return ResponseEntity.ok().body(objDTO);
	}
}