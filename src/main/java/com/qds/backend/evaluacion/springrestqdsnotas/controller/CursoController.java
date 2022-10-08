package com.qds.backend.evaluacion.springrestqdsnotas.controller;

import com.qds.backend.evaluacion.springrestqdsnotas.dto.CicloDto;
import com.qds.backend.evaluacion.springrestqdsnotas.dto.CursoDto;
import com.qds.backend.evaluacion.springrestqdsnotas.response.GenericoResponse;
import com.qds.backend.evaluacion.springrestqdsnotas.service.ISeccionService;
import com.qds.backend.evaluacion.springrestqdsnotas.service.ICursoService;
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

@Api(tags = "Cursos", description = "Servicio para administrar cursos")
@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private ISeccionService iSeccionService;
    @Autowired
    private ICursoService iCursoService;

    @GetMapping
    @ApiOperation("Listar Cursos")
    public ResponseEntity<GenericoResponse<List<CursoDto>>> listarCursos(){
        return new ResponseEntity<>(iCursoService.listarTodos(), HttpStatus.OK);
    }

    @GetMapping("/ciclos/{id}")
    @ApiOperation("Listar Ciclos por Curso")
    public ResponseEntity<GenericoResponse<List<CicloDto>>> listarCursosPorCiclo(@PathVariable("id") Integer id){
        return new ResponseEntity<>(iSeccionService.listarCiclosPorCurso(id), HttpStatus.OK);
    }

}