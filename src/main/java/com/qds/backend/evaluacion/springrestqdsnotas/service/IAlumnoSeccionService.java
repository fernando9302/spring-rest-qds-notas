package com.qds.backend.evaluacion.springrestqdsnotas.service;

import com.qds.backend.evaluacion.springrestqdsnotas.dto.AlumnoDto;
import com.qds.backend.evaluacion.springrestqdsnotas.response.GenericoResponse;

import java.util.List;

public interface IAlumnoSeccionService {

    GenericoResponse<List<AlumnoDto>> listarAlumnosPorSeccion(Integer idSeccion);
}
