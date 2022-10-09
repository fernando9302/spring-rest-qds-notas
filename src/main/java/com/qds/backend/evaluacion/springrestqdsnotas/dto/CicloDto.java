package com.qds.backend.evaluacion.springrestqdsnotas.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CicloDto {

    @ApiModelProperty(notes = "Id", example = "1")
    private Integer id;

    @ApiModelProperty(notes = "Año", example = "2022")
    private Integer anio;

    @ApiModelProperty(notes = "Número Ciclo", example = "II")
    private String numeroCiclo;

    @ApiModelProperty(notes = "Fecha Inicio", example = "2022-08-01")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaInicio;

    @ApiModelProperty(notes = "Fecha Fin", example = "2022-12-01")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaFin;
}
