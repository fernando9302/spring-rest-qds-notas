package com.qds.backend.evaluacion.springrestqdsnotas.service.impl;

import com.qds.backend.evaluacion.springrestqdsnotas.dto.CicloDto;
import com.qds.backend.evaluacion.springrestqdsnotas.dto.CursoDto;
import com.qds.backend.evaluacion.springrestqdsnotas.dto.SeccionDto;
import com.qds.backend.evaluacion.springrestqdsnotas.repository.ISeccionRepository;
import com.qds.backend.evaluacion.springrestqdsnotas.response.GenericoResponse;
import com.qds.backend.evaluacion.springrestqdsnotas.service.ISeccionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeccionServiceImpl implements ISeccionService {

    @Autowired
    @Qualifier("seccionToCursoMapper")
    private ModelMapper mapperCurso;

    @Autowired
    @Qualifier("seccionToCicloMapper")
    private ModelMapper mapperCiclo;

    @Autowired
    @Qualifier("seccionMapper")
    private ModelMapper mapperSeccion;

    @Autowired
    private ISeccionRepository iSeccionRepository;

    @Override
    public GenericoResponse<List<CursoDto>> listarCursosPorCiclo(Integer idCiclo) {
        return new GenericoResponse(iSeccionRepository.findAllByCicloId(idCiclo).stream().map(x-> mapperCurso.map(x, CursoDto.class)).collect(Collectors.toList()));
    }

    @Override
    public GenericoResponse<List<CicloDto>> listarCiclosPorCurso(Integer idCurso) {
        return new GenericoResponse(iSeccionRepository.findAllByCursoId(idCurso).stream().map(x-> mapperCiclo.map(x, CicloDto.class)).collect(Collectors.toList()));
    }

    @Override
    public GenericoResponse<List<SeccionDto>> listarTodos() {
        return new GenericoResponse(iSeccionRepository.findAll().stream().map(x->mapperSeccion.map(x,SeccionDto.class)).collect(Collectors.toList()));
    }
}
