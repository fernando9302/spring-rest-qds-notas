package com.qds.backend.evaluacion.springrestqdsnotas.controller;

import com.qds.backend.evaluacion.springrestqdsnotas.dto.AlumnoDto;
import com.qds.backend.evaluacion.springrestqdsnotas.response.GenericoResponse;
import com.qds.backend.evaluacion.springrestqdsnotas.service.IAlumnoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "Alumnos", description = "Servicio para administrar alumnos")
@RestController
@RequestMapping("/v1/alumnos")
public class AlumnoController {

    @Autowired
    private IAlumnoService iAlumnoService;

    @GetMapping
    @ApiOperation("Listar Alumnos")
    public ResponseEntity<GenericoResponse<List<AlumnoDto>>> listarAlumnos(){
        return new ResponseEntity<>(iAlumnoService.listarTodos(), HttpStatus.OK);
    }


}