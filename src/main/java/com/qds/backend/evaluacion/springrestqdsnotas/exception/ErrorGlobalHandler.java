package com.qds.backend.evaluacion.springrestqdsnotas.exception;

import com.qds.backend.evaluacion.springrestqdsnotas.response.GenericoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

}
