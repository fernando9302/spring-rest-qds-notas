package com.qds.backend.evaluacion.springrestqdsnotas.repository;

import com.qds.backend.evaluacion.springrestqdsnotas.entity.AlumnoSeccion;
import com.qds.backend.evaluacion.springrestqdsnotas.entity.Seccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IAlumnoSeccionRepository extends JpaRepository<AlumnoSeccion, Integer> {

    Optional<AlumnoSeccion> findOneByAlumnoIdAndSeccionId(Integer idAlumno, Integer idCursoCiclo);

    List<AlumnoSeccion> findAllByAlumnoId(Integer idAlumno);

    List<AlumnoSeccion> findAllBySeccionId(Integer idSeccion);
}
