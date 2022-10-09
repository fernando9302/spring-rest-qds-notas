package com.qds.backend.evaluacion.springrestqdsnotas.service.impl;


import com.qds.backend.evaluacion.springrestqdsnotas.dto.UsuarioDto;
import com.qds.backend.evaluacion.springrestqdsnotas.entity.Usuario;
import com.qds.backend.evaluacion.springrestqdsnotas.repository.IUsuarioRepository;
import com.qds.backend.evaluacion.springrestqdsnotas.response.GenericoResponse;
import com.qds.backend.evaluacion.springrestqdsnotas.service.IUsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Autowired
    @Qualifier("usuarioMapper")
    private ModelMapper mapper;

    @Override
    public GenericoResponse<List<UsuarioDto>> listarTodos() {
        return new GenericoResponse(iUsuarioRepository.findAll().stream().map(x-> mapper.map(x, UsuarioDto.class)).collect(Collectors.toList()));
    }

    @Override
    public GenericoResponse<UsuarioDto> actualizarUsuario(Usuario usuario) {
        Usuario usuarioCreado = iUsuarioRepository.save(usuario);
        return new GenericoResponse<>(mapper.map(usuarioCreado, UsuarioDto.class));
    }

}
