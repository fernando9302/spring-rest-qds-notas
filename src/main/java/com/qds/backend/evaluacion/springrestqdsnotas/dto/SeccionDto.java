package com.qds.backend.evaluacion.springrestqdsnotas.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeccionDto {
    @ApiModelProperty(notes = "Id", example = "1")
    private Integer id;

    @ApiModelProperty(notes = "Ciclo", example = "2022-II")
    private String ciclo;

    @ApiModelProperty(notes = "Curso", example = "Programación")
    private String curso;
}
