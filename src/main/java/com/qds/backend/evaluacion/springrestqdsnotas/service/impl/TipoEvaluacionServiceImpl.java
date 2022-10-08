package com.qds.backend.evaluacion.springrestqdsnotas.service.impl;

import com.qds.backend.evaluacion.springrestqdsnotas.dto.NotaDto;
import com.qds.backend.evaluacion.springrestqdsnotas.dto.RolDto;
import com.qds.backend.evaluacion.springrestqdsnotas.dto.TipoEvaluacionDto;
import com.qds.backend.evaluacion.springrestqdsnotas.repository.IRolRepository;
import com.qds.backend.evaluacion.springrestqdsnotas.repository.ITipoEvaluacionRepository;
import com.qds.backend.evaluacion.springrestqdsnotas.response.GenericoResponse;
import com.qds.backend.evaluacion.springrestqdsnotas.service.ITipoEvaluacionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoEvaluacionServiceImpl implements ITipoEvaluacionService {
    @Autowired
    private ITipoEvaluacionRepository iTipoEvaluacionRepository;

    @Autowired
    @Qualifier("tipoEvaluacionMapper")
    private ModelMapper mapper;

    @Override
    public GenericoResponse<List<TipoEvaluacionDto>> listarTodos() {
        return new GenericoResponse(iTipoEvaluacionRepository.findAll().stream().map(x-> mapper.map(x, TipoEvaluacionDto.class)).collect(Collectors.toList()));
    }
}
