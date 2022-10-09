package com.qds.backend.evaluacion.springrestqdsnotas.controller;

import com.qds.backend.evaluacion.springrestqdsnotas.dto.NotaDto;
import com.qds.backend.evaluacion.springrestqdsnotas.dto.UsuarioDto;
import com.qds.backend.evaluacion.springrestqdsnotas.request.NotaRequest;
import com.qds.backend.evaluacion.springrestqdsnotas.response.GenericoResponse;
import com.qds.backend.evaluacion.springrestqdsnotas.service.INotaService;
import com.qds.backend.evaluacion.springrestqdsnotas.service.IUsuarioService;
import com.qds.backend.evaluacion.springrestqdsnotas.utilitario.Util;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;



@RestController
@RequestMapping("/notas")
@Api(tags = "Notas", description = "Servicio para administrar notas")
@Validated
public class NotaController {

    @Autowired
    private INotaService iNotaService;

    @GetMapping
    @ApiOperation("Listar Notas")
    public ResponseEntity<GenericoResponse<List<NotaDto>>> listarNotas(@ApiIgnore Authentication authentication){
        return new ResponseEntity<>(iNotaService.listarTodosPorAlumno(Util.obtenerUsuarioActual(authentication)), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Crear Notas")
    public ResponseEntity<GenericoResponse<NotaDto>> crearNota(@ApiIgnore Authentication authentication, @ApiParam(name = "nota", value = "Nota") @Valid @RequestBody NotaRequest nota){
        nota.setNombreUsuario(Util.obtenerUsuarioActual(authentication));
        return new ResponseEntity<>(iNotaService.crearNota(nota), HttpStatus.CREATED);
    }
}
