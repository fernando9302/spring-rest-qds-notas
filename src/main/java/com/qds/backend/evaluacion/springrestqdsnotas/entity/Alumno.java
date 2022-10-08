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
@Table(name = "alumno")
public class Alumno {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(length = 80, nullable = false)
    private String nombre;

    @Transient
    public String getNombreCompleto(){
        return String.format("%s %s", nombre, apellido);
    }

    @Column(length = 100, nullable = false)
    private String apellido;

    @Column(length = 8, nullable = false)
    private String dni;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Column
    private Boolean activo;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;

    @OneToMany(mappedBy = "alumno")
    private List<AlumnoSeccion> alumnoSeccion;

    @OneToMany(mappedBy = "alumnoSeccion")
    private List<Nota> notas;

    public Alumno(Integer id){
        this.id = id;
    }

    public Alumno(String nombre, String apellido, String dni, LocalDate fechaNacimiento, Usuario usuario){
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.activo = true;
        this.usuario = usuario;
    }
}
