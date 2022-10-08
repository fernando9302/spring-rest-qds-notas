package com.qds.backend.evaluacion.springrestqdsnotas.service.impl;

import com.qds.backend.evaluacion.springrestqdsnotas.dto.AlumnoDto;
import com.qds.backend.evaluacion.springrestqdsnotas.dto.CicloDto;
import com.qds.backend.evaluacion.springrestqdsnotas.dto.NotaDto;
import com.qds.backend.evaluacion.springrestqdsnotas.dto.SeccionDto;
import com.qds.backend.evaluacion.springrestqdsnotas.repository.IAlumnoSeccionRepository;
import com.qds.backend.evaluacion.springrestqdsnotas.repository.ISeccionRepository;
import com.qds.backend.evaluacion.springrestqdsnotas.response.GenericoResponse;
import com.qds.backend.evaluacion.springrestqdsnotas.service.IAlumnoSeccionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlumnoSeccionServiceImpl implements IAlumnoSeccionService {
    @Autowired
    private IAlumnoSeccionRepository iAlumnoSeccionRepository;

    @Autowired
    @Qualifier("alumnoSeccionToSeccionMapper")
    private ModelMapper mapperSeccion;

    @Autowired
    @Qualifier("alumnoSeccionToAlumnoMapper")
    private ModelMapper mapperAlumno;
    @Override
    public GenericoResponse<List<AlumnoDto>> listarAlumnosPorSeccion(Integer idSeccion) {
        return new GenericoResponse(iAlumnoSeccionRepository.findAllBySeccionId(idSeccion).stream().map(x-> mapperAlumno.map(x, AlumnoDto.class)).collect(Collectors.toList()));
    }

    @Override
    public GenericoResponse<List<SeccionDto>>listarSeccionesPorAlumno(Integer idAlumno) {

        return new GenericoResponse(iAlumnoSeccionRepository.findAllBySeccionId(idAlumno).stream().map(x-> mapperAlumno.map(x, SeccionDto.class)).collect(Collectors.toList()));
    }
}
