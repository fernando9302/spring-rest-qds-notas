package com.qds.backend.evaluacion.springrestqdsnotas.service.impl;

import com.qds.backend.evaluacion.springrestqdsnotas.dto.NotaDto;
import com.qds.backend.evaluacion.springrestqdsnotas.entity.*;
import com.qds.backend.evaluacion.springrestqdsnotas.exception.NegocioValidacionException;
import com.qds.backend.evaluacion.springrestqdsnotas.repository.*;
import com.qds.backend.evaluacion.springrestqdsnotas.request.NotaRequest;
import com.qds.backend.evaluacion.springrestqdsnotas.response.GenericoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
class NotaServiceImplTest {

    @MockBean
    private INotaRepository iNotaRepository;

    @MockBean
    private ITipoEvaluacionRepository iTipoEvaluacionRepository;

    @MockBean
    private IAlumnoRepository iAlumnoRepository;

    @MockBean
    private ISeccionRepository iSeccionRepository;

    @MockBean
    private IAlumnoSeccionRepository iAlumnoSeccionRepository;

    @MockBean
    private IProfesorRepository iProfesorRepository;

    @MockBean
    private IUsuarioRepository iUsuarioRepository;

    @MockBean(name = "notaMapper")
    private ModelMapper mapper;

    @InjectMocks
    private NotaServiceImpl notaService;

    @BeforeEach
    private void inicio(){
        MockitoAnnotations.openMocks(this);
        Mockito.when(iAlumnoRepository.findOneByUsuarioNombre("fhuaman")).thenReturn(Optional.of(new Alumno(1)));
        Mockito.when(iNotaRepository.findAllByAlumnoSeccionAlumnoId(1)).thenReturn(Arrays.asList(new Nota(), new Nota()));
        Mockito.when(iTipoEvaluacionRepository.findById(3)).thenReturn(Optional.of(new TipoEvaluacion(3)));
        Mockito.when(iTipoEvaluacionRepository.findById(4)).thenReturn(Optional.of(new TipoEvaluacion(4)));
        Mockito.when(iAlumnoRepository.findById(2)).thenReturn(Optional.of(new Alumno(2)));
        Mockito.when(iAlumnoRepository.findById(3)).thenReturn(Optional.of(new Alumno(3)));
        Mockito.when(iSeccionRepository.findById(1)).thenReturn(Optional.of(new Seccion(1)));
        Mockito.when(iAlumnoSeccionRepository.findOneByAlumnoIdAndSeccionId(2, 1)).thenReturn(Optional.of(new AlumnoSeccion(1)));
        Mockito.when(iAlumnoSeccionRepository.findById(1)).thenReturn(Optional.of(new AlumnoSeccion(1)));
        Mockito.when(iNotaRepository.save(any())).thenReturn(new Nota(1, new AlumnoSeccion(1), new TipoEvaluacion(3) ));
        Mockito.when(iNotaRepository.findById(any())).thenReturn(Optional.of(new Nota(1, new AlumnoSeccion(1), new TipoEvaluacion(3) )));
        Mockito.when(iUsuarioRepository.findByNombre("jlopez")).thenReturn(Optional.of(new Usuario(6)));
        Mockito.when(iUsuarioRepository.findByNombre("rperez")).thenReturn(Optional.of(new Usuario(7)));
        Mockito.when(iProfesorRepository.findOneByUsuarioNombre("jlopez")).thenReturn(Optional.of(new Profesor(1)));
        Mockito.when(iProfesorRepository.findOneByUsuarioNombre("rperez")).thenReturn(Optional.of(new Profesor(2)));
        Mockito.when(iNotaRepository.findOneByTipoEvaluacionIdAndAlumnoSeccionAlumnoIdAndAlumnoSeccionSeccionId(4,2,1)).thenReturn(Optional.of(new Nota(1)));
        Mockito.when(iSeccionRepository.findOneByIdAndProfesorUsuarioNombre(1, "jlopez")).thenReturn(Optional.of(new Seccion(1)));
        Mockito.when(iSeccionRepository.findOneByIdAndProfesorUsuarioNombre(2, "rperez")).thenReturn(Optional.of(new Seccion(2)));
    }

    @Test
    void listarTodosPorAlumnoConUsuario() {
        GenericoResponse<List<NotaDto>> notas = notaService.listarTodosPorAlumno("fhuaman");
        assertNotNull(notas);
        assertNotNull(notas.getRespuesta());
        assertEquals(notas.getRespuesta().size(), 2);
    }

    @Test
    void listarTodosPorAlumnoSinUsuarioError() {
        assertThrows(NegocioValidacionException.class, () -> {notaService.listarTodosPorAlumno("jlopez");});
    }

    @Test
    void crearNota(){
        GenericoResponse<NotaDto> nota = notaService.crearNota(new NotaRequest(1,2,3, 18, "jlopez"));
        assertNotNull(nota);
    }

    @Test
    void crearNotaNulos() {
        //Con Tipo Evaluacion Nulo
        NegocioValidacionException thrownTipoEvaluacionNulo = assertThrows(NegocioValidacionException.class, () -> {notaService.crearNota(new NotaRequest(1,2,null, 15, "jlopez"));});
        assertEquals(thrownTipoEvaluacionNulo.getMessage(), "El campo idTipoEvaluacion debe ser enviado");
        //Con Alumno Nulo
        NegocioValidacionException thrownAlumnoNulo =assertThrows(NegocioValidacionException.class, () -> {notaService.crearNota(new NotaRequest(1,null,3, 15, "jlopez"));});
        assertEquals(thrownAlumnoNulo.getMessage(), "El campo idAlumno debe ser enviado");
        //Con Seccion Nulo
        NegocioValidacionException thrownSeccionNulo =assertThrows(NegocioValidacionException.class, () -> {notaService.crearNota(new NotaRequest(null,2,3, 15, "jlopez"));});
        assertEquals(thrownSeccionNulo.getMessage(), "El campo idSeccion debe ser enviado");
        //Con Calificacion Nulo
        NegocioValidacionException thrownCalificacionNulo =assertThrows(NegocioValidacionException.class, () -> {notaService.crearNota(new NotaRequest(1,2,3, null, "jlopez"));});
        assertEquals(thrownCalificacionNulo.getMessage(), "El campo calificacion debe ser enviado");
    }

    @Test
    void crearNotasSinExistencias(){
        NegocioValidacionException thrownTipoEvaluacionNoExiste = assertThrows(NegocioValidacionException.class, () -> {notaService.crearNota(new NotaRequest(1,2,100, 15, "jlopez"));});
        assertEquals(thrownTipoEvaluacionNoExiste.getMessage(), "El tipo de Evaluación no existe");

        NegocioValidacionException thrownAlumnoNoExiste = assertThrows(NegocioValidacionException.class, () -> {notaService.crearNota(new NotaRequest(1,200,3, 15, "jlopez"));});
        assertEquals(thrownAlumnoNoExiste.getMessage(), "El alumno no existe");

        NegocioValidacionException thrownSeccionNoExiste = assertThrows(NegocioValidacionException.class, () -> {notaService.crearNota(new NotaRequest(600,2,3, 15, "jlopez"));});
        assertEquals(thrownSeccionNoExiste.getMessage(), "La sección no existe");

        NegocioValidacionException thrownCalificacionNoExiste = assertThrows(NegocioValidacionException.class, () -> {notaService.crearNota(new NotaRequest(1,3,3, 15, "jlopez"));});
        assertEquals(thrownCalificacionNoExiste.getMessage(), "El alumno no está inscrito en la sección");

        NegocioValidacionException thrownProfesorEnSeccion = assertThrows(NegocioValidacionException.class, () -> {notaService.crearNota(new NotaRequest(1,2,3, 18, "rperez"));});
        assertEquals(thrownProfesorEnSeccion.getMessage(), "El profesor no es el responsable de la sección");
    }

    @Test
    void crearNotasValidacionNegocio(){
        NegocioValidacionException thrownCalificacionMenorCero = assertThrows(NegocioValidacionException.class, () -> {notaService.crearNota(new NotaRequest(1,2,3, -1, "jlopez"));});
        assertEquals(thrownCalificacionMenorCero.getMessage(), "La calificación debe estar entre 0 y 20");

        NegocioValidacionException thrownCalificacionMayorVeinte = assertThrows(NegocioValidacionException.class, () -> {notaService.crearNota(new NotaRequest(1,2,3, 21, "jlopez"));});
        assertEquals(thrownCalificacionMayorVeinte.getMessage(), "La calificación debe estar entre 0 y 20");

        NegocioValidacionException thrownNoEsProfesor = assertThrows(NegocioValidacionException.class, () -> {notaService.crearNota(new NotaRequest(1,2,3, 15, "fhuaman"));});
        assertEquals(thrownNoEsProfesor.getMessage(), "Solo los profesores pueden registrar notas");

        NegocioValidacionException thrownNotaRegistrada = assertThrows(NegocioValidacionException.class, () -> {notaService.crearNota(new NotaRequest(1,2,4, 15, "jlopez"));});
        assertEquals(thrownNotaRegistrada.getMessage(), "La nota ya fue registrada");
    }

}