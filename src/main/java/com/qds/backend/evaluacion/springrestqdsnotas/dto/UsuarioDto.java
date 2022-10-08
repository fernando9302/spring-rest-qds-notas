package com.qds.backend.evaluacion.springrestqdsnotas.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDto {

    @ApiModelProperty(notes = "Id", example = "1")
    private Integer id;
    @ApiModelProperty(notes = "Usuario", example = "mtorres")
    private String usuario;

}
