package com.qds.backend.evaluacion.springrestqdsnotas.service;

import com.qds.backend.evaluacion.springrestqdsnotas.dto.TipoEvaluacionDto;
import com.qds.backend.evaluacion.springrestqdsnotas.response.GenericoResponse;

import java.util.List;

public interface ITipoEvaluacionService {
    GenericoResponse<List<TipoEvaluacionDto>> listarTodos();
}
