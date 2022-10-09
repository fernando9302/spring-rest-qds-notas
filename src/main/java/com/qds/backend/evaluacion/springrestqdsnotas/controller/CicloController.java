package com.qds.backend.evaluacion.springrestqdsnotas.controller;

import com.qds.backend.evaluacion.springrestqdsnotas.dto.CicloDto;
import com.qds.backend.evaluacion.springrestqdsnotas.dto.CursoDto;
import com.qds.backend.evaluacion.springrestqdsnotas.response.GenericoResponse;
import com.qds.backend.evaluacion.springrestqdsnotas.service.ICicloService;
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

@Api(tags = "Ciclos", description = "Servicio para administrar ciclos")
@RestController
@RequestMapping("/ciclos")
public class CicloController {

    @Autowired
    private ICicloService iCicloService;

    @Autowired
    private ISeccionService iSeccionService;

    @GetMapping
    @ApiOperation("Listar Ciclos")
    public ResponseEntity<GenericoResponse<List<CicloDto>>> listarCiclos(){
        return new ResponseEntity<>(iCicloService.listarTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}/cursos")
    @ApiOperation("Listar Cursos por Ciclo")
    public ResponseEntity<GenericoResponse<List<CursoDto>>> listarCursosPorCiclo(@PathVariable("id") Integer id){
        return new ResponseEntity<>(iSeccionService.listarCursosPorCiclo(id), HttpStatus.OK);
    }



}