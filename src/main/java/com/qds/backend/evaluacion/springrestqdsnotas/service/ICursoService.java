package com.qds.backend.evaluacion.springrestqdsnotas.service;

import com.qds.backend.evaluacion.springrestqdsnotas.dto.CursoDto;
import com.qds.backend.evaluacion.springrestqdsnotas.response.GenericoResponse;

import java.util.List;

public interface ICursoService {

    GenericoResponse<List<CursoDto>> listarTodos();
}
