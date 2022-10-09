package com.qds.backend.evaluacion.springrestqdsnotas.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "nota")
public class Nota {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_alumno_seccion")
    private AlumnoSeccion alumnoSeccion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_evaluacion")
    private TipoEvaluacion tipoEvaluacion;

    @Column(nullable = false)
    private Integer calificacion;

    @Column(nullable = false)
    private LocalDateTime fechaRegistro;

    public Nota(AlumnoSeccion alumnoSeccion, TipoEvaluacion tipoEvaluacion, Integer calificacion, LocalDateTime fechaRegistro) {
        this.alumnoSeccion = alumnoSeccion;
        this.tipoEvaluacion = tipoEvaluacion;
        this.calificacion = calificacion;
        this.fechaRegistro = fechaRegistro;
    }

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Nota(Integer id) {
        this.id = id;
    }

    public Nota(Integer id, AlumnoSeccion alumnoSeccion, TipoEvaluacion tipoEvaluacion) {
        this.id = id;
        this.alumnoSeccion = alumnoSeccion;
        this.tipoEvaluacion = tipoEvaluacion;
    }
}
