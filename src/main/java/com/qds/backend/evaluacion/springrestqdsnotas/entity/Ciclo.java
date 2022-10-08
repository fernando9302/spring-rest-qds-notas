package com.qds.backend.evaluacion.springrestqdsnotas.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "ciclo")
public class Ciclo {

    public Ciclo(Integer anio, String numeroCiclo, LocalDate fechaInicio, LocalDate fechaFin) {
        this.anio = anio;
        this.numeroCiclo = numeroCiclo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column
    private Integer anio;

    @Column(name = "numero_ciclo", length = 3, nullable = false)
    private String numeroCiclo;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @OneToMany(mappedBy = "ciclo")
    private List<Seccion> seccion;

    @Transient
    public String getDescripcionCompleta(){
        return String.format("%s-%s", anio, numeroCiclo);
    }

    public Ciclo(Integer id){
        this.id = id;
    }
}
