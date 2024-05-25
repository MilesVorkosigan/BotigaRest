package com.Luis.gestioBotiga.backend.presentacio.restcontrollers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.Luis.gestioBotiga.backend.presentacio.config.PresentationException;
import com.Luis.gestioBotiga.business.services.BotigaServices;
import com.Luis.gestioBotiga.bussiness.model.Botiga;




@RestController
@RequestMapping("/botigues")
public class BotigaController {
	@Autowired
	private BotigaServices botigaServices;

	@GetMapping
	public List<Botiga> getAll() {

		List<Botiga> botigues = null;
		botigues=botigaServices.getAll();

		return botigues;
	}
	@GetMapping("/{id}")
	public Botiga read(@PathVariable Long id) {
		
		Optional<Botiga> optional = botigaServices.read(id);
		
		if(!optional.isPresent()) {
			throw new PresentationException("No se encuentra el producto con id " + id, HttpStatus.NOT_FOUND);
		}
		
		return optional.get();
	}
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Botiga botiga, UriComponentsBuilder ucb) {
		
		Long codigo = null;
		
		try {
			codigo = botigaServices.create(botiga);
		} catch(IllegalStateException e) {
			throw new PresentationException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		URI uri = ucb.path("/productos/{codigo}").build(codigo);
		
		return ResponseEntity.created(uri).build();
	}
		
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		
		try {
			botigaServices.delete(id);
		} catch(IllegalStateException e) {
			throw new PresentationException("No se encuentra la tienda con id [" + id + "]. No se ha podido eliminar.", HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@RequestBody Botiga botiga) {
		
		try {
			botigaServices.update(botiga);
		} catch(IllegalStateException e) {
			throw new PresentationException(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}
}
