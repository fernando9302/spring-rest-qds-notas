package com.qds.backend.evaluacion.springrestqdsnotas.service.impl;

import com.qds.backend.evaluacion.springrestqdsnotas.dto.AlumnoDto;
import com.qds.backend.evaluacion.springrestqdsnotas.repository.IAlumnoRepository;
import com.qds.backend.evaluacion.springrestqdsnotas.response.GenericoResponse;
import com.qds.backend.evaluacion.springrestqdsnotas.service.IAlumnoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlumnoServiceImpl implements IAlumnoService {
    @Autowired
    private IAlumnoRepository iAlumnoRepository;

    @Autowired
    @Qualifier("alumnoMapper")
    private ModelMapper mapper;

    @Override
    public GenericoResponse<List<AlumnoDto>> listarTodos() {
        return new GenericoResponse(iAlumnoRepository.findAll().stream().map(x-> mapper.map(x, AlumnoDto.class)).collect(Collectors.toList()));
    }


}
