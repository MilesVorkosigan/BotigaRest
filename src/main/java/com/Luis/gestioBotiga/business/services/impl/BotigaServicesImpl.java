package com.Luis.gestioBotiga.business.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import com.Luis.gestioBotiga.business.services.BotigaServices;
import com.Luis.gestioBotiga.bussiness.model.Botiga;
import com.Luis.gestioBotiga.bussiness.model.District;
import com.Luis.gestioBotiga.bussiness.model.Sector;
import com.Luis.gestioBotiga.bussiness.model.Subsector;

@Service
public class BotigaServicesImpl implements BotigaServices {
	private final TreeMap<Long, Botiga> BOTIGUES = new TreeMap<>();

	public BotigaServicesImpl() {
		init();
	}

	private void init() {
		Botiga botiga1 = new Botiga();
		botiga1.setId(1L);
		botiga1.setName("Botiga 1");
		botiga1.setAdress("Calle 1");
		botiga1.setOpen(true);
		botiga1.setDistrict(District.MONTCADA_CENTRE);
		botiga1.setSector(Sector.COMERCIAL);
		botiga1.setSubsector(Subsector.ALIMENTACIO);
		botiga1.setFranquicia(false);

		Botiga botiga2 = new Botiga();
		botiga2.setId(2L);
		botiga2.setName("Botiga 2");
		botiga2.setAdress("Calle 2");
		botiga2.setOpen(false);
		botiga2.setDistrict(District.MAS_RAMPINYO);
		botiga2.setSector(Sector.SERVEIS);
		botiga2.setSubsector(Subsector.HOSTELERIA);
		botiga2.setFranquicia(true);

		Botiga botiga3 = new Botiga();
		botiga3.setId(3L);
		botiga3.setName("Botiga 3");
		botiga3.setAdress("Calle 3");
		botiga3.setOpen(true);
		botiga3.setDistrict(District.CAN_SANT_JOAN);
		botiga3.setSector(Sector.ALTRES);
		botiga3.setSubsector(Subsector.SALUT);
		botiga3.setFranquicia(false);

		BOTIGUES.put(botiga1.getId(), botiga1);
		BOTIGUES.put(botiga2.getId(), botiga2);
		BOTIGUES.put(botiga3.getId(), botiga3);

	}

	@Override
	public Long create(Botiga botiga) {
		Long id = BOTIGUES.lastKey() + 1;

		botiga.setId(id);

		BOTIGUES.put(botiga.getId(), botiga);

		return id;
	}

	@Override
	public Optional<Botiga> read(long id) {
		return Optional.ofNullable(BOTIGUES.get(id));
	}

	@Override
	public List<Botiga> getAll() {
		return new ArrayList<>(BOTIGUES.values());
	}

	public TreeMap<Long, Botiga> getBOTIGUES() {
		return BOTIGUES;
	}

}
