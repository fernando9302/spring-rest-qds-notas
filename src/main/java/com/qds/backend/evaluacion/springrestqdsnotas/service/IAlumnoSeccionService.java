package com.qds.backend.evaluacion.springrestqdsnotas.service;

import com.qds.backend.evaluacion.springrestqdsnotas.dto.AlumnoDto;
import com.qds.backend.evaluacion.springrestqdsnotas.dto.CicloDto;
import com.qds.backend.evaluacion.springrestqdsnotas.dto.CursoDto;
import com.qds.backend.evaluacion.springrestqdsnotas.dto.SeccionDto;
import com.qds.backend.evaluacion.springrestqdsnotas.response.GenericoResponse;

import java.util.List;

public interface IAlumnoSeccionService {

    GenericoResponse<List<AlumnoDto>> listarAlumnosPorSeccion(Integer idSeccion);
    GenericoResponse<List<SeccionDto>> listarSeccionesPorAlumno(Integer idAlumno);
}
