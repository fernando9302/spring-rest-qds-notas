package com.qds.backend.evaluacion.springrestqdsnotas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qds.backend.evaluacion.springrestqdsnotas.dto.NotaDto;
import com.qds.backend.evaluacion.springrestqdsnotas.request.NotaRequest;
import com.qds.backend.evaluacion.springrestqdsnotas.response.GenericoResponse;
import com.qds.backend.evaluacion.springrestqdsnotas.security.entryPoint.JwtAuthenticationEntryPoint;
import com.qds.backend.evaluacion.springrestqdsnotas.security.filter.JwtRequestFilter;
import com.qds.backend.evaluacion.springrestqdsnotas.security.service.JwtUserDetailsService;
import com.qds.backend.evaluacion.springrestqdsnotas.security.util.JwtTokenUtil;
import com.qds.backend.evaluacion.springrestqdsnotas.service.INotaService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
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
                        .get("/v1/notas")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjcmFtaXJleiIsImV4cCI6MTY2NTcyMjQxOSwiaWF0IjoxNjY1MzYyNDE5fQ.aGq4VVR8fZPdWoSyr9nMVjnoL5bHRM9xjF0aVOEmvyAxEi0kNINr2Z00TAtQKihp_ysCSrQc7Tn7C9inyUIGIA")
                        .content(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.notNullValue()))
                .andExpect(jsonPath("$.respuesta", hasSize(2)));
    }

    @Test
    void crearNota() throws Exception {
        NotaRequest notaRequest = NotaRequest.builder().idAlumno(1).idSeccion(2).idTipoEvaluacion(3).calificacion(15).build();
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/v1/notas")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(notaRequest))
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjcmFtaXJleiIsImV4cCI6MTY2NTcyMjQxOSwiaWF0IjoxNjY1MzYyNDE5fQ.aGq4VVR8fZPdWoSyr9nMVjnoL5bHRM9xjF0aVOEmvyAxEi0kNINr2Z00TAtQKihp_ysCSrQc7Tn7C9inyUIGIA");

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.respuesta", Matchers.notNullValue()));
    }
}