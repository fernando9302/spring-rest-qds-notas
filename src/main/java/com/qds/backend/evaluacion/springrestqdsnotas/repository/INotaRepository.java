package com.qds.backend.evaluacion.springrestqdsnotas.repository;

import com.qds.backend.evaluacion.springrestqdsnotas.entity.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface INotaRepository extends JpaRepository<Nota, Integer> {

    List<Nota> findAllByAlumnoSeccionAlumnoId(Integer idAlumno);

    Optional<Nota>  findOneByTipoEvaluacionIdAndAlumnoSeccionAlumnoIdAndAlumnoSeccionSeccionId(Integer idTipoEvaluacion, Integer idAlumno, Integer idSeccion);

}
