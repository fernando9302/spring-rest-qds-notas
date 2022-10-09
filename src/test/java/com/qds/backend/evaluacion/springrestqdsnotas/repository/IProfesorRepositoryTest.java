package com.qds.backend.evaluacion.springrestqdsnotas.repository;

import com.qds.backend.evaluacion.springrestqdsnotas.entity.Profesor;
import com.qds.backend.evaluacion.springrestqdsnotas.entity.Rol;
import com.qds.backend.evaluacion.springrestqdsnotas.entity.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase
class IProfesorRepositoryTest {

    @Autowired
    private IProfesorRepository iProfesorRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    void findOneByUsuarioNombre() {
        Rol rol  = new Rol("Profesor");
        em.persistAndGetId(rol);
        Usuario usuario = new Usuario("gperez", "12345", rol);
        em.persistAndGetId(usuario);
        Profesor profesor = new Profesor("Gabriel", "Perez", "83726333", usuario);
        em.persistAndGetId(profesor);
        Optional<Profesor> profesorEncontrado = iProfesorRepository.findOneByUsuarioNombre(usuario.getNombre());
        assertTrue(profesorEncontrado.isPresent());
        assertTrue(profesorEncontrado.get().getId() > 0);
    }
}