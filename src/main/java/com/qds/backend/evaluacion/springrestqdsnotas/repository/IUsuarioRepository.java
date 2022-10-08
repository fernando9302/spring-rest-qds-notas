package com.qds.backend.evaluacion.springrestqdsnotas.repository;


import com.qds.backend.evaluacion.springrestqdsnotas.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
}
