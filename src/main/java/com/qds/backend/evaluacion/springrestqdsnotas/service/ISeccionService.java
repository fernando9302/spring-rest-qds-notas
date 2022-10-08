package com.qds.backend.evaluacion.springrestqdsnotas.service;

import com.qds.backend.evaluacion.springrestqdsnotas.dto.CicloDto;
import com.qds.backend.evaluacion.springrestqdsnotas.dto.CursoDto;
import com.qds.backend.evaluacion.springrestqdsnotas.dto.SeccionDto;
import com.qds.backend.evaluacion.springrestqdsnotas.response.GenericoResponse;

import java.util.List;

public interface ISeccionService {

    GenericoResponse<List<CursoDto>> listarCursosPorCiclo(Integer idCiclo);
    GenericoResponse<List<CicloDto>> listarCiclosPorCurso(Integer idCurso);
    GenericoResponse<List<SeccionDto>> listarTodos();
}
