package com.Luis.gestioBotiga.business.services;

import java.util.List;
import java.util.Optional;

import com.Luis.gestioBotiga.bussiness.model.Botiga;

public interface BotigaServices {
	Long create(Botiga botiga);
	Optional<Botiga> read (long id);
	
	List<Botiga>getAll();
	void delete(Long id);
	void update(Botiga botiga);
	
}
