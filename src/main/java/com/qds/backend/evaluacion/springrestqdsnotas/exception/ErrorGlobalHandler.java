package com.qds.backend.evaluacion.springrestqdsnotas.exception;

import com.qds.backend.evaluacion.springrestqdsnotas.response.GenericoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorGlobalHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NegocioValidacionException.class)
    public ResponseEntity<GenericoResponse> handleNegocioValidacion(NegocioValidacionException ex, WebRequest req){
        GenericoResponse res = new GenericoResponse();
        res.getErrores().add(ex.getMessage());
        res.setFecha(LocalDateTime.now());
        res.setProcesado(false);
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<GenericoResponse> handleUsuarioNoEncontrado(UsernameNotFoundException ex, WebRequest req){
        GenericoResponse res = new GenericoResponse();
        res.getErrores().add(ex.getMessage());
        res.setFecha(LocalDateTime.now());
        res.setProcesado(false);
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
}
