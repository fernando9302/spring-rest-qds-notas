package com.qds.backend.evaluacion.springrestqdsnotas.exception;

import com.qds.backend.evaluacion.springrestqdsnotas.response.GenericoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

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


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(" "));

        /*for(FieldError err : ex.getBindingResult().getFieldErrors()){
            message += err.getField() + err.getDefaultMessage();
        }*/

        GenericoResponse res = new GenericoResponse();
        res.getErrores().add(message);
        res.setFecha(LocalDateTime.now());
        res.setProcesado(false);
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
}
