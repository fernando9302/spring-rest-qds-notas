package com.qds.backend.evaluacion.springrestqdsnotas.repository;

import com.qds.backend.evaluacion.springrestqdsnotas.entity.Alumno;
import com.qds.backend.evaluacion.springrestqdsnotas.entity.Rol;
import com.qds.backend.evaluacion.springrestqdsnotas.entity.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase
class IAlumnoRepositoryTest {

    @Autowired
    private IAlumnoRepository iAlumnoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    void findOneByUsuarioId() {
        Rol rol  = new Rol("Alumno");
        em.persistAndGetId(rol);
        Usuario usuario = new Usuario("gperez", "12345", rol);
        em.persistAndGetId(usuario);
        Alumno alumno = new Alumno("Gabriel", "Perez", "83726333", LocalDate.of(1992, 1, 1), usuario);
        em.persistAndGetId(alumno);
        Optional<Alumno> alumnoEncontrado = iAlumnoRepository.findOneByUsuarioId(usuario.getId());
        assertTrue(alumnoEncontrado.isPresent());
        assertTrue(alumnoEncontrado.get().getId() > 0);
    }
}