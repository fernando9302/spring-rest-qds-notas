package com.qds.backend.evaluacion.springrestqdsnotas.repository;

import com.qds.backend.evaluacion.springrestqdsnotas.entity.TipoEvaluacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITipoEvaluacionRepository extends JpaRepository<TipoEvaluacion,Integer> {
}
