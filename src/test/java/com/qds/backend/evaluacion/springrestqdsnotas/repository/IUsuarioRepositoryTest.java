package com.qds.backend.evaluacion.springrestqdsnotas.repository;

import com.qds.backend.evaluacion.springrestqdsnotas.entity.Alumno;
import com.qds.backend.evaluacion.springrestqdsnotas.entity.Rol;
import com.qds.backend.evaluacion.springrestqdsnotas.entity.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase
class IUsuarioRepositoryTest {

    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    void findByNombre() {
        Rol rol  = new Rol("Alumno");
        em.persistAndGetId(rol);
        Usuario usuario = new Usuario("gperez", "12345", rol);
        em.persist(usuario);
        Optional<Usuario> usuarioEncontrado = iUsuarioRepository.findByNombre(usuario.getNombre());
        assertTrue(usuarioEncontrado.isPresent());
        assertTrue(usuarioEncontrado.get().getId() > 0);
    }
}