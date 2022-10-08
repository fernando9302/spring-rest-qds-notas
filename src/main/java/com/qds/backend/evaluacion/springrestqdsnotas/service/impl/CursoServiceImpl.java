package com.qds.backend.evaluacion.springrestqdsnotas.service.impl;

import com.qds.backend.evaluacion.springrestqdsnotas.dto.CursoDto;
import com.qds.backend.evaluacion.springrestqdsnotas.dto.NotaDto;
import com.qds.backend.evaluacion.springrestqdsnotas.repository.ICursoRepository;
import com.qds.backend.evaluacion.springrestqdsnotas.response.GenericoResponse;
import com.qds.backend.evaluacion.springrestqdsnotas.service.ICursoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoServiceImpl implements ICursoService {
    @Autowired
    private ICursoRepository iCursoRepository;

    @Autowired
    @Qualifier("cursoMapper")
    private ModelMapper mapper;

    @Override
    public GenericoResponse<List<CursoDto>> listarTodos() {
        return new GenericoResponse(iCursoRepository.findAll().stream().map(x-> mapper.map(x, CursoDto.class)).collect(Collectors.toList()));
    }

}
