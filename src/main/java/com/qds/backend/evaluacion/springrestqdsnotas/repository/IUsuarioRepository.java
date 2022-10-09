package com.qds.backend.evaluacion.springrestqdsnotas.repository;


import com.qds.backend.evaluacion.springrestqdsnotas.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByNombre(String nombreUsuario);
}
