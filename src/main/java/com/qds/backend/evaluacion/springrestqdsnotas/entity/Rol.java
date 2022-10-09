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
@Table(name = "rol")
@ToString(onlyExplicitlyIncluded = true)
public class Rol {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(length = 30, nullable = false)
    private String descripcion;

    @ToString.Exclude
    @OneToMany(mappedBy = "rol")
    private List<Usuario> usuarios;

    public Rol(String descripcion) {
        this.descripcion = descripcion;
    }
}
