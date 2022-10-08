package com.qds.backend.evaluacion.springrestqdsnotas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NegocioValidacionException extends  RuntimeException{

    private String mensaje;
    public NegocioValidacionException(String mensaje){
        super(mensaje);
    }
}
