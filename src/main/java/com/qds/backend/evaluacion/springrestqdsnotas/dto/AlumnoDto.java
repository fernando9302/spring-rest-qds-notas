package com.qds.backend.evaluacion.springrestqdsnotas.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class AlumnoDto {

    @ApiModelProperty(notes = "Id", example = "1")
    private Integer id;

    @ApiModelProperty(notes = "Nombre", example = "Juan")
    private String nombre;

    @ApiModelProperty(notes = "Apellido", example = "Fernandez")
    private String apellido;

    @ApiModelProperty(notes = "DNI", example = "56746374")
    private String dni;

    @ApiModelProperty(notes = "Fecha Nacimiento", example = "2022-08-01")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    @ApiModelProperty(notes = "Activo", example = "true")
    private Boolean activo;
}