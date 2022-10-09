package com.qds.backend.evaluacion.springrestqdsnotas.service.impl;

import com.qds.backend.evaluacion.springrestqdsnotas.dto.ProfesorDto;
import com.qds.backend.evaluacion.springrestqdsnotas.repository.IProfesorRepository;
import com.qds.backend.evaluacion.springrestqdsnotas.response.GenericoResponse;
import com.qds.backend.evaluacion.springrestqdsnotas.service.IProfesorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfesorServiceImpl implements IProfesorService {
    @Autowired
    private IProfesorRepository iProfesorRepository;

    @Autowired
    @Qualifier("profesorMapper")
    private ModelMapper mapper;

    @Override
    public GenericoResponse<List<ProfesorDto>> listarTodos() {
        return new GenericoResponse(iProfesorRepository.findAll().stream().map(x-> mapper.map(x, ProfesorDto.class)).collect(Collectors.toList()));
    }

}
