package com.Luis.gestioBotiga.backend.presentacio.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.Luis.gestioBotiga.backend.presentacio.config.AnswerError;
import com.Luis.gestioBotiga.business.services.BotigaServices;
import com.Luis.gestioBotiga.bussiness.model.Botiga;

@RestController
public class BotigaController {
	@Autowired
	private BotigaServices botigaServices;

	@GetMapping("/Botiga")
	public List<Botiga> getAll() {
		return botigaServices.getAll();
	}

	@GetMapping("/botigues/{id}")
	public ResponseEntity<?> read(@PathVariable Long id) {

		if (id > 500) {
			throw new RuntimeException("El número " + id + " no es válido.");
		}

		Optional<Botiga> optional = botigaServices.read(id);

		if (optional.isEmpty()) {
			AnswerError respuestaError = new AnswerError("No se encuentra el producto con id " + id);
			return new ResponseEntity<>(respuestaError, HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(optional.get());
	}
}
