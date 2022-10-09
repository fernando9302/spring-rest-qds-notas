package com.qds.backend.evaluacion.springrestqdsnotas.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProfesorDto {

    @ApiModelProperty(notes = "Id", example = "1")
    private Integer id;

    @ApiModelProperty(notes = "Nombre", example = "Jose")
    private String nombre;

    @ApiModelProperty(notes = "Apellido", example = "Lopez")
    private String apellido;

    @ApiModelProperty(notes = "DNI", example = "48574635")
    private String dni;

    @ApiModelProperty(notes = "Activo", example = "true")
    private Boolean activo;

}
