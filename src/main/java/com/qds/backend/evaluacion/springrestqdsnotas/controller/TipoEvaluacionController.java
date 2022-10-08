package com.qds.backend.evaluacion.springrestqdsnotas.controller;

import com.qds.backend.evaluacion.springrestqdsnotas.dto.AlumnoDto;
import com.qds.backend.evaluacion.springrestqdsnotas.dto.TipoEvaluacionDto;
import com.qds.backend.evaluacion.springrestqdsnotas.response.GenericoResponse;
import com.qds.backend.evaluacion.springrestqdsnotas.service.IAlumnoService;
import com.qds.backend.evaluacion.springrestqdsnotas.service.ITipoEvaluacionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "Tipo Evaluación", description = "Servicio para administrar tipos de evaludación")
@RestController
@RequestMapping("/tiposEvaluacion")
public class TipoEvaluacionController {

    @Autowired
    private ITipoEvaluacionService iTipoEvaluacionService;

    @GetMapping
    @ApiOperation("Listar Tipos de Evaluación")
    public ResponseEntity<GenericoResponse<List<TipoEvaluacionDto>>> listarTiposEvaluacion(){
        return new ResponseEntity<>(iTipoEvaluacionService.listarTodos(), HttpStatus.OK);
    }

}