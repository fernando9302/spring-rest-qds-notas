package com.qds.backend.evaluacion.springrestqdsnotas.service;

import com.qds.backend.evaluacion.springrestqdsnotas.dto.NotaDto;
import com.qds.backend.evaluacion.springrestqdsnotas.dto.UsuarioDto;
import com.qds.backend.evaluacion.springrestqdsnotas.request.NotaRequest;
import com.qds.backend.evaluacion.springrestqdsnotas.response.GenericoResponse;

import java.util.List;

public interface INotaService {

    GenericoResponse<List<NotaDto>> listarTodosPorAlumno(String nombreUsuario);
    GenericoResponse<NotaDto> crearNota(NotaRequest notaRequest);

}
