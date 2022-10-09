package com.qds.backend.evaluacion.springrestqdsnotas.service;

import com.qds.backend.evaluacion.springrestqdsnotas.dto.UsuarioDto;
import com.qds.backend.evaluacion.springrestqdsnotas.entity.Usuario;
import com.qds.backend.evaluacion.springrestqdsnotas.response.GenericoResponse;

import java.util.List;

public interface IUsuarioService{

    GenericoResponse<List<UsuarioDto>> listarTodos();

    GenericoResponse<UsuarioDto> actualizarUsuario(Usuario usuario);


}
