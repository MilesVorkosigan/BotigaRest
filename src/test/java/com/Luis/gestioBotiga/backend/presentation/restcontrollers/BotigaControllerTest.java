package com.Luis.gestioBotiga.backend.presentation.restcontrollers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.Luis.gestioBotiga.backend.presentacio.config.AnswerError;
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

	@Test
void solicitamos_botiga_a_partir_de_un_id_inexistente() throws Exception {
		
		when(botigaServices.read(1005L)).thenReturn(Optional.empty());
		
		MvcResult respuesta = miniPostman.perform(get("/botigues/1005").contentType("application/json"))
									.andExpect(status().isNotFound())
									.andReturn();
		
		String responseBody = respuesta.getResponse().getContentAsString();
		String respuestaEsperada = objectMapper.writeValueAsString(new AnswerError("No se encuentra el producto con id 100"));
		
		assertThat(responseBody).isEqualToIgnoringWhitespace(respuestaEsperada);
	}

	@Test
	void crea_botiga_ok() throws Exception {

		botiga1.setId(null);

		when(botigaServices.create(botiga1)).thenReturn(1033L);

		String requestBody = objectMapper.writeValueAsString(botiga1);

		miniPostman.perform(post("/botigues").content(requestBody).contentType("application/json"))
				.andExpect(status().isCreated())
				.andExpect(header().string("Location", "http://localhost/botigues/1033"));
	}

	@Test
	void crear_botiga_con_id_NO_NULL() throws Exception{
		
		when(botigaServices.create(botiga1)).thenThrow(new IllegalStateException("Problema con el id..."));
		
		String requestBody = objectMapper.writeValueAsString(botiga1);
		
		MvcResult respuesta = miniPostman.perform(post("/botiga").content(requestBody).contentType("application/json"))
						.andExpect(status().isBadRequest())
						.andReturn();
		
		String responseBody = respuesta.getResponse().getContentAsString();
		String respuestaEsperada = objectMapper.writeValueAsString(new AnswerError("Problema con el id..."));
		
		assertThat(responseBody).isEqualToIgnoringWhitespace(respuestaEsperada);
	}
	@Test
void eliminamos_producto_ok() throws Exception{
		
		miniPostman.perform(delete("/botigues/1033")).andExpect(status().isNoContent());
		
		verify(botigaServices, times(1)).delete(1033L);
	}
	@Test
void eliminamos_producto_no_existente() throws Exception{
		
		Mockito.doThrow(new IllegalStateException("xxxx")).when(botigaServices).delete(1033L);
		
		MvcResult respuesta = miniPostman.perform(delete("/botigues/1033"))
								.andExpect(status().isNotFound())
								.andReturn();
		
		String responseBody = respuesta.getResponse().getContentAsString();
		String respuestaEsperada = objectMapper.writeValueAsString(new AnswerError("No se encuentra el producto con id [789]. No se ha podido eliminar."));
		
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
