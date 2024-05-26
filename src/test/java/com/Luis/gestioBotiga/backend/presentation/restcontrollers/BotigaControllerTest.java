package com.Luis.gestioBotiga.backend.presentation.restcontrollers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.Luis.gestioBotiga.backend.presentacio.restcontrollers.BotigaController;
import com.Luis.gestioBotiga.business.services.BotigaServices;
import com.Luis.gestioBotiga.bussiness.model.Botiga;
import com.Luis.gestioBotiga.bussiness.model.District;
import com.Luis.gestioBotiga.bussiness.model.Sector;
import com.Luis.gestioBotiga.bussiness.model.Subsector;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = BotigaController.class)
public class BotigaControllerTest {
	@Autowired
	private MockMvc miniPostman;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private BotigaServices botigaServices;

	private Botiga botiga1;
	private Botiga botiga2;

	@BeforeEach
	void init() {
		initObjects();
	}

	@Test
	void demanar_totes_botigues() throws Exception {
		// Arrange
		List<Botiga> botigues = Arrays.asList(botiga1, botiga2);
		when(botigaServices.getAll()).thenReturn(botigues);
		MvcResult respuesta = miniPostman.perform(get("/botigues").contentType("application/json"))
				.andExpect(status().isOk()).andReturn();

		String responseBody = respuesta.getResponse().getContentAsString();
		String respuestaEsperada = objectMapper.writeValueAsString(botigues);

		// Assert

		assertThat(responseBody).isEqualToIgnoringWhitespace(respuestaEsperada);
	}

	@Test
void obtenir_objecte_per_id() throws Exception{
		
		when(botigaServices.read(100L)).thenReturn(Optional.of(botiga1));
		
		MvcResult respuesta = miniPostman.perform(get("/botigues/100").contentType("application/json"))
									.andExpect(status().isOk())
									.andReturn();
		
		String responseBody = respuesta.getResponse().getContentAsString();
		String respuestaEsperada = objectMapper.writeValueAsString(botiga1);
		
		assertThat(responseBody).isEqualToIgnoringWhitespace(respuestaEsperada);
	
	}
	
	

	private void initObjects() {
		botiga1 = new Botiga();
		botiga1.setId(100L);
		botiga1.setName("Botiga 1");
		botiga1.setOpen(false);
		botiga1.setDistrict(District.BIFURCA);
		botiga1.setSector(Sector.COMERCIAL);
		botiga1.setSubsector(Subsector.ALTRES);
		botiga1.setFranquicia(false);

		botiga2 = new Botiga();
		botiga2.setId(101L);
		botiga2.setName("Botiga 2");
		botiga2.setOpen(true);
		botiga2.setDistrict(District.CAN_SANT_JOAN);
		botiga2.setSector(Sector.SERVEIS);
		botiga2.setSubsector(Subsector.SALUT);
		botiga2.setFranquicia(true);
	}

}
