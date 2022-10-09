package com.qds.backend.evaluacion.springrestqdsnotas.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "usuario")
public class Usuario {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(length = 1000, nullable = false)
    private String contrasenia;

    @OneToOne(mappedBy = "usuario")
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;

    public Usuario(Integer id){
        this.id = id;
    }

    public Usuario(String nombre, String contrasenia, Rol rol) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }
}
