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
@Table(name = "seccion")
public class Seccion {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ciclo")
    private Ciclo ciclo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_curso")
    private Curso curso;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_profesor")
    private Profesor profesor;

    @OneToMany(mappedBy = "seccion")
    private List<AlumnoSeccion> alumnosSeccion;

}
