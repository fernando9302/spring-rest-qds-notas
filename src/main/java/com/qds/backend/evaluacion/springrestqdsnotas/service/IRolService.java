package com.qds.backend.evaluacion.springrestqdsnotas.service;

import com.qds.backend.evaluacion.springrestqdsnotas.dto.RolDto;
import com.qds.backend.evaluacion.springrestqdsnotas.response.GenericoResponse;

import java.util.List;

public interface IRolService {
    GenericoResponse<List<RolDto>> listarTodos();
}
