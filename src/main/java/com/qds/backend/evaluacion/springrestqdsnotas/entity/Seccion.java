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
@ToString(onlyExplicitlyIncluded = true)
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

    @ToString.Exclude
    @OneToMany(mappedBy = "seccion")
    private List<AlumnoSeccion> alumnosSeccion;

    public Seccion(Ciclo ciclo, Curso curso) {
        this.ciclo = ciclo;
        this.curso = curso;
    }

    public Seccion(Ciclo ciclo, Curso curso, Profesor profesor) {
        this.ciclo = ciclo;
        this.curso = curso;
        this.profesor = profesor;
    }

    public Seccion(Integer id) {
        this.id = id;
    }
}
