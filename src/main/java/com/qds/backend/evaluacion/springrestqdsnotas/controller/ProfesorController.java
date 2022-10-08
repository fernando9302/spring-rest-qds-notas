package com.qds.backend.evaluacion.springrestqdsnotas.controller;

import com.qds.backend.evaluacion.springrestqdsnotas.dto.ProfesorDto;
import com.qds.backend.evaluacion.springrestqdsnotas.response.GenericoResponse;
import com.qds.backend.evaluacion.springrestqdsnotas.service.IProfesorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "Profesores", description = "Servicio para administrar profesores")
@RestController
@RequestMapping("/profesores")
public class ProfesorController {

    @Autowired
    private IProfesorService iProfesorService;

    @GetMapping
    @ApiOperation("Listar Profesores")
    public ResponseEntity<GenericoResponse<List<ProfesorDto>>> listarProfesores(){
        return new ResponseEntity<>(iProfesorService.listarTodos(), HttpStatus.OK);
    }

}
