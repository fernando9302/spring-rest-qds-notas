package com.qds.backend.evaluacion.springrestqdsnotas.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qds.backend.evaluacion.springrestqdsnotas.dto.NotaDto;
import com.qds.backend.evaluacion.springrestqdsnotas.request.NotaRequest;
import com.qds.backend.evaluacion.springrestqdsnotas.response.GenericoResponse;
import com.qds.backend.evaluacion.springrestqdsnotas.service.INotaService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;


import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NotaController.class)
class NotaControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private INotaService notaService;

    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    private void inicio()
    {
        List<NotaDto> notaDtoList = Arrays.asList(new NotaDto(), new NotaDto());
        GenericoResponse respuestaListado = new GenericoResponse(notaDtoList);

        Mockito.when(notaService.listarTodosPorAlumno(any())).thenReturn(respuestaListado);

        Mockito.when(notaService.crearNota(any())).thenReturn(respuestaListado);
    }

    @Test
    void listarNotas() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/notas")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.notNullValue()))
                .andExpect(jsonPath("$.respuesta", hasSize(2)));
    }

    @Test
    void crearNota() throws Exception {
        NotaRequest notaRequest = NotaRequest.builder().idAlumno(1).idSeccion(2).idTipoEvaluacion(3).calificacion(15).build();
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/notas")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(notaRequest));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.respuesta", Matchers.notNullValue()));
    }
}