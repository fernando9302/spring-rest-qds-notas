package com.qds.backend.evaluacion.springrestqdsnotas.repository;

import com.qds.backend.evaluacion.springrestqdsnotas.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase
class IAlumnoSeccionRepositoryTest {

    @Autowired
    private IAlumnoSeccionRepository iAlumnoSeccionRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    void findOneByAlumnoIdAndSeccionId(){
        Alumno alumno = new Alumno("Gabriel", "Perez", "83726333", LocalDate.of(1992, 1, 1), null);
        em.persistAndGetId(alumno);
        Seccion seccion = new Seccion();
        em.persistAndGetId(seccion);
        AlumnoSeccion alumnoSeccion = new AlumnoSeccion(alumno, seccion);
        em.persistAndGetId(alumnoSeccion);
        Optional<AlumnoSeccion> alumnoSeccionEncontrado = iAlumnoSeccionRepository.findOneByAlumnoIdAndSeccionId(alumno.getId(), seccion.getId());
        assertTrue(alumnoSeccionEncontrado.isPresent());
        assertTrue(alumnoSeccionEncontrado.get().getId() > 0);
    }

    @Test
    void findAllBySeccionId(){
        Alumno alumno1 = new Alumno("Gabriel", "Perez", "83726333", LocalDate.of(1992, 1, 1), null);
        em.persistAndGetId(alumno1);
        Alumno alumno2 = new Alumno("Roberto", "Gomez", "11111124", LocalDate.of(1990, 1, 1), null);
        em.persistAndGetId(alumno2);
        Seccion seccion = new Seccion();
        em.persistAndGetId(seccion);
        AlumnoSeccion alumnoSeccion1 = new AlumnoSeccion(alumno1, seccion);
        em.persist(alumnoSeccion1);
        AlumnoSeccion alumnoSeccion2 = new AlumnoSeccion(alumno2, seccion);
        em.persist(alumnoSeccion2);
        assertEquals(iAlumnoSeccionRepository.findAllBySeccionId(seccion.getId()).size(), 2);
    }

}