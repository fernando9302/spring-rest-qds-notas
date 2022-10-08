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
@Table(name = "alumno_seccion")
public class AlumnoSeccion {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_alumno")
    private Alumno alumno;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_seccion")
    private Seccion seccion;

    @OneToMany(mappedBy = "alumnoSeccion")
    private List<Nota> notas;

    public AlumnoSeccion(Integer id){
        this.id = id;
    }

    public AlumnoSeccion(Alumno alumno, Seccion seccion) {
        this.alumno = alumno;
        this.seccion = seccion;
    }
}
