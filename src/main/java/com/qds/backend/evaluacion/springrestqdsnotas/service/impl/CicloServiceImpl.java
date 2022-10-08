package com.qds.backend.evaluacion.springrestqdsnotas.service.impl;

import com.qds.backend.evaluacion.springrestqdsnotas.dto.CicloDto;
import com.qds.backend.evaluacion.springrestqdsnotas.dto.NotaDto;
import com.qds.backend.evaluacion.springrestqdsnotas.repository.ICicloRepository;
import com.qds.backend.evaluacion.springrestqdsnotas.response.GenericoResponse;
import com.qds.backend.evaluacion.springrestqdsnotas.service.ICicloService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CicloServiceImpl implements ICicloService {
    @Autowired
    private ICicloRepository iCicloRepository;

    @Autowired
    @Qualifier("cicloMapper")
    private ModelMapper mapper;

    @Override
    public GenericoResponse<List<CicloDto>> listarTodos() {
        return new GenericoResponse(iCicloRepository.findAll().stream().map(x-> mapper.map(x, CicloDto.class)).collect(Collectors.toList()));
    }

}
