package com.qds.backend.evaluacion.springrestqdsnotas.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class GenericoResponse<T> {

    @ApiModelProperty(notes = "Respuesta")
    private T respuesta;
    @ApiModelProperty(notes = "Fecha")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fecha;
    @ApiModelProperty(notes = "Procesado")
    private Boolean procesado;
    @ApiModelProperty(notes = "Lista Errores")
    private List<String> errores;

    public GenericoResponse(){
        fecha = LocalDateTime.now();
        procesado = true;
        errores = new ArrayList<>();
    }

    public GenericoResponse(T respuesta){
        this();
        this.respuesta = respuesta;
    }
}
