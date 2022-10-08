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
@Table(name = "curso")
public class Curso {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(length = 50, nullable = false)
    private String descripcion;

    @OneToMany(mappedBy = "curso")
    private List<ProfesorCurso> profesorCurso;

    @OneToMany(mappedBy = "curso")
    private List<Seccion> seccion;

    public Curso(Integer id){
        this.id = id;
    }

    public Curso(String descripcion) {
        this.descripcion = descripcion;
    }
}
