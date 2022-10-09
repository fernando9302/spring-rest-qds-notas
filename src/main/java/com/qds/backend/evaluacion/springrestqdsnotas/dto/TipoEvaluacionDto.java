package com.qds.backend.evaluacion.springrestqdsnotas.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TipoEvaluacionDto {

    @ApiModelProperty(notes = "Id", example = "1")
    private Integer id;

    @ApiModelProperty(notes = "Descripción", example = "Práctica Calificada 1")
    private String descripcion;
}
