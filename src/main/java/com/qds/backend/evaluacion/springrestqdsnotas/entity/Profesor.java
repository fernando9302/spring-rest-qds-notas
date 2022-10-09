package com.qds.backend.evaluacion.springrestqdsnotas.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "profesor")
public class Profesor {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(length = 80, nullable = false)
    private String nombre;

    @Column(length = 100, nullable = false)
    private String apellido;

    @Column(length = 8, nullable = false)
    private String dni;

    @Column
    private Boolean activo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "profesor")
    private List<ProfesorCurso> profesorCurso;

    @OneToMany(mappedBy = "profesor")
    private List<Seccion> seccion;

    public Profesor(String nombre, String apellido, String dni, Usuario usuario){
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.activo = true;
        this.usuario = usuario;
    }

    public Profesor(Integer id) {
        this.id = id;
    }
}
