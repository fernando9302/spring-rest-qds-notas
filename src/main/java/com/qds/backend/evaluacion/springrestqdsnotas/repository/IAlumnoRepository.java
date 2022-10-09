package com.qds.backend.evaluacion.springrestqdsnotas.repository;

import com.qds.backend.evaluacion.springrestqdsnotas.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAlumnoRepository extends JpaRepository<Alumno, Integer> {

   Optional<Alumno> findOneByUsuarioNombre(String nombre);
}
