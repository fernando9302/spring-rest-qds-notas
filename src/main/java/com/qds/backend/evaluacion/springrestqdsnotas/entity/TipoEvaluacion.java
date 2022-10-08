package com.qds.backend.evaluacion.springrestqdsnotas.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tipo_evaluacion")
public class TipoEvaluacion {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(length = 30, nullable = false)
    private String descripcion;

    public TipoEvaluacion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoEvaluacion(Integer id){
        this.id = id;
    }
}
