package com.qds.backend.evaluacion.springrestqdsnotas.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NotaDto {

    @ApiModelProperty(notes = "Curso", example = "Programación")
    private String curso;
    @ApiModelProperty(notes = "Alumno", example = "Miguel Torres")
    private String alumno;
    @ApiModelProperty(notes = "Ciclo", example = "2022-II")
    private String ciclo;
    @ApiModelProperty(notes = "Tipo Evaluaci+on", example = "Práctica Calificada 1")
    private String tipoEvaluacion;
    @ApiModelProperty(notes = "Calificación", example = "18")
    private Byte calificacion;
    private LocalDateTime fechaRegistro;
}
