package com.qds.backend.evaluacion.springrestqdsnotas.repository;

import com.qds.backend.evaluacion.springrestqdsnotas.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase
class ISeccionRepositoryTest {

    @Autowired
    private ISeccionRepository iSeccionRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    void findAllByCicloId() {
        Curso curso1 = new Curso("Programación");
        Curso curso2 = new Curso("Matemática");
        em.persistAndGetId(curso1);
        em.persistAndGetId(curso2);
        Ciclo ciclo = new Ciclo(2022, "I", LocalDate.of(2022,1,1), LocalDate.of(2022,4,1));
        em.persistAndGetId(ciclo);
        Seccion seccion1 = new Seccion(ciclo, curso1);
        Seccion seccion2 = new Seccion(ciclo, curso2);
        em.persist(seccion1);
        em.persist(seccion2);
        assertEquals(iSeccionRepository.findAllByCicloId(ciclo.getId()).size(), 2);
    }

    @Test
    void findAllByCursoId() {
        Curso curso = new Curso("Programación");
        em.persistAndGetId(curso);
        Ciclo ciclo1 = new Ciclo(2022, "I", LocalDate.of(2022,1,1), LocalDate.of(2022,4,1));
        Ciclo ciclo2 = new Ciclo(2022, "II", LocalDate.of(2022,5,3), LocalDate.of(2022,8,1));
        em.persistAndGetId(ciclo1);
        em.persistAndGetId(ciclo2);
        Seccion seccion1 = new Seccion(ciclo1, curso);
        Seccion seccion2 = new Seccion(ciclo2, curso);
        em.persist(seccion1);
        em.persist(seccion2);
        assertEquals(iSeccionRepository.findAllByCursoId(curso.getId()).size(), 2);
    }

    @Test
    void findOneByIdAndProfesorUsuarioNombre(){
        Curso curso = new Curso("Programación");
        em.persistAndGetId(curso);
        Ciclo ciclo = new Ciclo(2022, "I", LocalDate.of(2022,1,1), LocalDate.of(2022,4,1));
        em.persistAndGetId(ciclo);
        Rol rol = new Rol("Profesor");
        em.persistAndGetId(rol);
        Usuario usuario = new Usuario("rmarquez", "1212", rol);
        em.persistAndGetId(usuario);
        Profesor profesor = new Profesor("Roberto", "Marquez", "11111111", usuario);
        em.persistAndGetId(profesor);
        Seccion seccion = new Seccion(ciclo, curso, profesor);
        em.persist(seccion);
        Optional<Seccion> profesorEnSeccion = iSeccionRepository.findOneByIdAndProfesorUsuarioNombre(seccion.getId(), usuario.getNombre());
        assertTrue(profesorEnSeccion.isPresent());
        assertTrue(profesorEnSeccion.get().getId() > 0);
    }
}