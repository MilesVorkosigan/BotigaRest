package com.Luis.gestioBotiga.business.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.Luis.gestioBotiga.backend.integration.repositores.BotigaRepository;
import com.Luis.gestioBotiga.business.services.BotigaServices;
import com.Luis.gestioBotiga.bussiness.model.Botiga;

@Primary
@Service
public class BotigaServicesImpl implements BotigaServices {

	@Autowired
	private final BotigaRepository botigaRepository;

	BotigaServicesImpl(BotigaRepository botigaRepository) {
		this.botigaRepository = botigaRepository;
	}

	@Override
	@Transactional
	public Long create(Botiga botiga) {
		if (botiga.getId() != null) {
			throw new IllegalStateException("No se puede crear una botiga con código not null");

		}
		Long id = System.currentTimeMillis();
		botiga.setId(id);
		botigaRepository.save(botiga);
		return id;
	}

	@Override
	public Optional<Botiga> read(long id) {
	return botigaRepository.findById(id);
	}

	@Override
	public List<Botiga> getAll() {
		return botigaRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		botigaRepository.deleteById(id);
		
	}

	@Override
	public void update(Botiga botiga) {
		Long id = botiga.getId();
		if(id == null) {
			throw new IllegalStateException("No se puede actualizar uan botiga con código not null");
		}
		
		boolean existe = botigaRepository.existsById(id);
		
		if(!existe) {
			throw new IllegalStateException("La Botiga con código " + id + " no existe. No se puede actualizar.");
		}
		
		botigaRepository.save(botiga);
		
	}

}
