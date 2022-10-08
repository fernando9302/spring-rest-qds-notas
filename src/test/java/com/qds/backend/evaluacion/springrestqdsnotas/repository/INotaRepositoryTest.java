package com.qds.backend.evaluacion.springrestqdsnotas.repository;

import com.qds.backend.evaluacion.springrestqdsnotas.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase
class INotaRepositoryTest {

    @Autowired
    private INotaRepository iNotaRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    void findAllByAlumnoSeccionAlumnoId() {
        Alumno alumno = new Alumno("Gabriel", "Perez", "83726333", LocalDate.of(1992, 1, 1), null);
        em.persistAndGetId(alumno);
        Seccion seccion = new Seccion();
        em.persistAndGetId(seccion);
        AlumnoSeccion alumnoSeccion = new AlumnoSeccion(alumno, seccion);
        em.persistAndGetId(alumnoSeccion);
        TipoEvaluacion tipoEvaluacion = new TipoEvaluacion("Practica Calificada 1");
        em.persist(tipoEvaluacion);
        Nota nota1 = new Nota(alumnoSeccion, tipoEvaluacion, 15, LocalDateTime.now());
        Nota nota2 = new Nota(alumnoSeccion, tipoEvaluacion, 12, LocalDateTime.now());
        em.persist(nota1);
        em.persist(nota2);
        assertEquals(iNotaRepository.findAllByAlumnoSeccionAlumnoId(alumno.getId()).size(), 2);
    }
}