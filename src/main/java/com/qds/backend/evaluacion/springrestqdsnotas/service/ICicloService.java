package com.qds.backend.evaluacion.springrestqdsnotas.service;

import com.qds.backend.evaluacion.springrestqdsnotas.dto.CicloDto;
import com.qds.backend.evaluacion.springrestqdsnotas.response.GenericoResponse;

import java.util.List;

public interface ICicloService {
    GenericoResponse<List<CicloDto>> listarTodos();
}
