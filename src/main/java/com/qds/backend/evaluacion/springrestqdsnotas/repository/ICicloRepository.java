package com.qds.backend.evaluacion.springrestqdsnotas.repository;

import com.qds.backend.evaluacion.springrestqdsnotas.entity.Ciclo;
import com.qds.backend.evaluacion.springrestqdsnotas.entity.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICicloRepository extends JpaRepository<Ciclo, Integer> {
}
