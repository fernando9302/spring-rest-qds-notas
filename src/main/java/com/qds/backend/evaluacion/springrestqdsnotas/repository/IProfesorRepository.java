package com.qds.backend.evaluacion.springrestqdsnotas.repository;

import com.qds.backend.evaluacion.springrestqdsnotas.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProfesorRepository  extends JpaRepository<Profesor, Integer> {
}
