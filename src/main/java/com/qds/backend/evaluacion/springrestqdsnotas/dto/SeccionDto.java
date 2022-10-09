package com.qds.backend.evaluacion.springrestqdsnotas.dto;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.qds.backend.evaluacion.springrestqdsnotas.entity.Ciclo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeccionDto {
    @ApiModelProperty(notes = "Id", example = "1")
    private Integer id;

    @ApiModelProperty(notes = "Ciclo", example = "2022-II")
    @JsonIncludeProperties(value= {"id", "anio", "numeroCiclo"})
    private CicloDto ciclo;

    @ApiModelProperty(notes = "Curso", example = "Programación")
    private CursoDto curso;

    @ApiModelProperty(notes = "Profesor", example = "Jose Perez")
    @JsonIncludeProperties(value= {"id", "nombre", "apellido"})
    private ProfesorDto profesor;
}
