package com.qds.backend.evaluacion.springrestqdsnotas.controller;

import com.qds.backend.evaluacion.springrestqdsnotas.dto.AlumnoDto;
import com.qds.backend.evaluacion.springrestqdsnotas.dto.SeccionDto;
import com.qds.backend.evaluacion.springrestqdsnotas.response.GenericoResponse;
import com.qds.backend.evaluacion.springrestqdsnotas.service.IAlumnoSeccionService;
import com.qds.backend.evaluacion.springrestqdsnotas.service.ISeccionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "Secciones", description = "Servicio para administrar secciones")
@RestController
@RequestMapping("/v1/secciones")
public class SeccionController {

    @Autowired
    private ISeccionService iSeccionService;

    @Autowired
    private IAlumnoSeccionService iAlumnoSeccionService;



    @GetMapping
    @ApiOperation("Listar Secciones")
    public ResponseEntity<GenericoResponse<List<SeccionDto>>> listarSecciones(){
        return new ResponseEntity<>(iSeccionService.listarTodos(), HttpStatus.OK);
    }

    @GetMapping("{id}/alumnos")
    @ApiOperation("Listar Alumnos por Curso")
    public ResponseEntity<GenericoResponse<List<AlumnoDto>>> listarAlumnosPorSeccion(@PathVariable("id") Integer id){
        return new ResponseEntity<>(iAlumnoSeccionService.listarAlumnosPorSeccion(id), HttpStatus.OK);
    }

}