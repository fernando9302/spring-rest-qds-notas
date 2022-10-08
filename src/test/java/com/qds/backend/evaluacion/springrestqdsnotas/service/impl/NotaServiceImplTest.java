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

    @MockBean(name = "notaMapper")
    private ModelMapper mapper;

    @InjectMocks
    private NotaServiceImpl notaService;

    @BeforeEach
    private void inicio(){
        MockitoAnnotations.openMocks(this);
        Mockito.when(iAlumnoRepository.findOneByUsuarioId(1)).thenReturn(Optional.of(new Alumno(1)));
        Mockito.when(iNotaRepository.findAllByAlumnoSeccionAlumnoId(1)).thenReturn(Arrays.asList(new Nota(), new Nota()));
        Mockito.when(iTipoEvaluacionRepository.findById(3)).thenReturn(Optional.of(new TipoEvaluacion(3)));
        Mockito.when(iAlumnoRepository.findById(2)).thenReturn(Optional.of(new Alumno(2)));
        Mockito.when(iAlumnoRepository.findById(3)).thenReturn(Optional.of(new Alumno(3)));
        Mockito.when(iSeccionRepository.findById(1)).thenReturn(Optional.of(new Seccion(1)));
        Mockito.when(iAlumnoSeccionRepository.findOneByAlumnoIdAndSeccionId(2, 1)).thenReturn(Optional.of(new AlumnoSeccion(1)));
        Mockito.when(iAlumnoSeccionRepository.findById(1)).thenReturn(Optional.of(new AlumnoSeccion(1)));
        Mockito.when(iNotaRepository.save(any())).thenReturn(new Nota(1, new AlumnoSeccion(1), new TipoEvaluacion(3) ));
        Mockito.when(iNotaRepository.findById(any())).thenReturn(Optional.of(new Nota(1, new AlumnoSeccion(1), new TipoEvaluacion(3) )));

    }

    @Test
    void listarTodosPorAlumnoConUsuario() {
        GenericoResponse<List<NotaDto>> notas = notaService.listarTodosPorAlumno(1);
        assertNotNull(notas);
        assertNotNull(notas.getRespuesta());
        assertEquals(notas.getRespuesta().size(), 2);
    }

    @Test
    void listarTodosPorAlumnoSinUsuarioError() {
        assertThrows(NegocioValidacionException.class, () -> {notaService.listarTodosPorAlumno(2);});
    }

    @Test
    void crearNota(){
        GenericoResponse<NotaDto> nota = notaService.crearNota(new NotaRequest(1,2,3, 18, 6));
        assertNotNull(nota);
    }

    @Test
    void crearNotaNulos() {
        //Con Tipo Evaluacion Nulo
        NegocioValidacionException thrownTipoEvaluacionNulo = assertThrows(NegocioValidacionException.class, () -> {notaService.crearNota(new NotaRequest(1,2,null, 15, 6));});
        assertEquals(thrownTipoEvaluacionNulo.getMessage(), "El campo idTipoEvaluacion debe ser enviado");
        //Con Alumno Nulo
        NegocioValidacionException thrownAlumnoNulo =assertThrows(NegocioValidacionException.class, () -> {notaService.crearNota(new NotaRequest(1,null,3, 15, 6));});
        assertEquals(thrownAlumnoNulo.getMessage(), "El campo idAlumno debe ser enviado");
        //Con Seccion Nulo
        NegocioValidacionException thrownSeccionNulo =assertThrows(NegocioValidacionException.class, () -> {notaService.crearNota(new NotaRequest(null,2,3, 15, 6));});
        assertEquals(thrownSeccionNulo.getMessage(), "El campo idSeccion debe ser enviado");
        //Con Calificacion Nulo
        NegocioValidacionException thrownCalificacionNulo =assertThrows(NegocioValidacionException.class, () -> {notaService.crearNota(new NotaRequest(1,2,3, null, 6));});
        assertEquals(thrownCalificacionNulo.getMessage(), "El campo calificacion debe ser enviado");
    }

    @Test
    void crearNotasSinExistencias(){
        NegocioValidacionException thrownTipoEvaluacionNoExiste = assertThrows(NegocioValidacionException.class, () -> {notaService.crearNota(new NotaRequest(1,2,100, 15, 6));});
        assertEquals(thrownTipoEvaluacionNoExiste.getMessage(), "El tipo de Evaluación no existe");

        NegocioValidacionException thrownAlumnoNoExiste = assertThrows(NegocioValidacionException.class, () -> {notaService.crearNota(new NotaRequest(1,200,3, 15, 6));});
        assertEquals(thrownAlumnoNoExiste.getMessage(), "El alumno no existe");

        NegocioValidacionException thrownSeccionNoExiste = assertThrows(NegocioValidacionException.class, () -> {notaService.crearNota(new NotaRequest(600,2,3, 15, 6));});
        assertEquals(thrownSeccionNoExiste.getMessage(), "La sección no existe");

        NegocioValidacionException thrownCalificacionNoExiste = assertThrows(NegocioValidacionException.class, () -> {notaService.crearNota(new NotaRequest(1,3,3, 15, 6));});
        assertEquals(thrownCalificacionNoExiste.getMessage(), "El alumno no está inscrito en la sección");
    }

    @Test
    void crearNotasValidacionCalificacion(){
        NegocioValidacionException thrownCalificacionMenorCero = assertThrows(NegocioValidacionException.class, () -> {notaService.crearNota(new NotaRequest(1,2,3, -1, 6));});
        assertEquals(thrownCalificacionMenorCero.getMessage(), "La calificación debe estar entre 0 y 20");

        NegocioValidacionException thrownCalificacionMayorVeinte = assertThrows(NegocioValidacionException.class, () -> {notaService.crearNota(new NotaRequest(1,2,3, 21, 6));});
        assertEquals(thrownCalificacionMayorVeinte.getMessage(), "La calificación debe estar entre 0 y 20");
    }

}