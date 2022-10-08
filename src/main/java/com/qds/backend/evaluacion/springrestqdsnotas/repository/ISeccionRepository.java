package com.qds.backend.evaluacion.springrestqdsnotas.repository;

import com.qds.backend.evaluacion.springrestqdsnotas.entity.Seccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ISeccionRepository extends JpaRepository<Seccion, Integer> {

    List<Seccion> findAllByCicloId(Integer idCiclo);

    List<Seccion> findAllByCursoId(Integer idCurso);
}
