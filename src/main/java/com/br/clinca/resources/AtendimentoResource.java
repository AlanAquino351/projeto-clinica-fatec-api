package com.br.clinca.resources;

import com.br.clinca.domain.Atendimento;
import com.br.clinca.dtos.AtendimentoDTO;
import com.br.clinca.services.AtendimentoService;
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
@RequestMapping(value = "/atendimento")
public class AtendimentoResource {

    @Autowired
    private AtendimentoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<AtendimentoDTO> findById(@PathVariable Integer id) {
        AtendimentoDTO objDTO = new AtendimentoDTO(service.findAtendimentoById(id));
        return ResponseEntity.ok().body(objDTO);
    }

    @GetMapping()
    public ResponseEntity<List<AtendimentoDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/crm/{crm}")
    public ResponseEntity<List<AtendimentoDTO>> findAllByMedico(@PathVariable String crm) {
        return ResponseEntity.ok().body(service.findAllByMedico(crm));
    }

    @PostMapping
    public ResponseEntity<AtendimentoDTO> create(@Valid @RequestBody AtendimentoDTO objDTO) {
        Atendimento newObj = service.create(objDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AtendimentoDTO> update(@PathVariable Integer id, @Valid @RequestBody AtendimentoDTO objDTO) {
        AtendimentoDTO newObj = new AtendimentoDTO(service.update(id, objDTO));
        return ResponseEntity.ok().body(newObj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
