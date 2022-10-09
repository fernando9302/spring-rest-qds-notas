package com.qds.backend.evaluacion.springrestqdsnotas.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Required;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@Builder
public class NotaRequest {

    @ApiModelProperty(notes = "Sección Id", example = "1", required = true)
    //@NotNull(message = "Debe ingresar el id de la Sección")
    private Integer idSeccion;

    @ApiModelProperty(notes = "Alumno Id", example = "1", required = true)
    //@NotNull(message = "Debe ingresar el id del alumno")
    private Integer idAlumno;

    @ApiModelProperty(notes = "Tipo Evaluación Id", example = "1", required = true)
    //@NotNull(message = "Debe ingresar el id del tipo evaluación")
    private Integer idTipoEvaluacion;

    @ApiModelProperty(notes = "Calificación", example = "1", required = true)
    //@NotNull
    //@Range(min = 0, max = 20, message = "La calificación debe ser mínimo 0 y máximo 20")
    private Integer calificacion;

    @JsonIgnore
    private String nombreUsuario;
}
