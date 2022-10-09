package com.qds.backend.evaluacion.springrestqdsnotas.service.impl;

import com.qds.backend.evaluacion.springrestqdsnotas.dto.RolDto;
import com.qds.backend.evaluacion.springrestqdsnotas.repository.IRolRepository;
import com.qds.backend.evaluacion.springrestqdsnotas.response.GenericoResponse;
import com.qds.backend.evaluacion.springrestqdsnotas.service.IRolService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolServiceImpl implements IRolService {
    @Autowired
    private IRolRepository iRolRepository;

    @Autowired
    @Qualifier("rolMapper")
    private ModelMapper mapper;

    @Override
    public GenericoResponse<List<RolDto>> listarTodos() {
        return new GenericoResponse(iRolRepository.findAll().stream().map(x-> mapper.map(x, RolDto.class)).collect(Collectors.toList()));
    }

}