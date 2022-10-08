package com.qds.backend.evaluacion.springrestqdsnotas.service.impl;

import com.qds.backend.evaluacion.springrestqdsnotas.dto.NotaDto;
import com.qds.backend.evaluacion.springrestqdsnotas.entity.*;
import com.qds.backend.evaluacion.springrestqdsnotas.exception.NegocioValidacionException;
import com.qds.backend.evaluacion.springrestqdsnotas.repository.*;
import com.qds.backend.evaluacion.springrestqdsnotas.request.NotaRequest;
import com.qds.backend.evaluacion.springrestqdsnotas.response.GenericoResponse;
import com.qds.backend.evaluacion.springrestqdsnotas.service.INotaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotaServiceImpl implements INotaService {

    @Autowired
    private INotaRepository iNotaRepository;
    @Autowired
    private ITipoEvaluacionRepository iTipoEvaluacionRepository;
    @Autowired
    private IAlumnoRepository iAlumnoRepository;
    @Autowired
    private ICursoRepository iCursoRepository;
    @Autowired
    private ICicloRepository iCicloRepository;
    @Autowired
    private ISeccionRepository iSeccionRepository;

    @Autowired
    private IAlumnoSeccionRepository iAlumnoSeccionRepository;
    @Autowired
    @Qualifier("notaMapper")
    private ModelMapper mapper;

    @Override
    public GenericoResponse<List<NotaDto>> listarTodos(Integer idUsuario) {
        Alumno alumno = iAlumnoRepository.findOneByUsuarioId(idUsuario).orElse(null);
        if(alumno == null){
            throw new NegocioValidacionException("No existe un alumno asociado al usuario");
        }
        return new GenericoResponse(iNotaRepository.findAllByAlumnoSeccionAlumnoId(alumno.getId()).stream().map(x-> mapper.map(x, NotaDto.class)).collect(Collectors.toList()));
    }


    @Override
    public GenericoResponse<NotaDto> crearNota(NotaRequest nota) {
        validacionesNegocio(nota);
        Nota notaCreada = new Nota();
        notaCreada.setFechaRegistro(LocalDateTime.now());
        notaCreada.setUsuario(new Usuario(nota.getIdUsuario()));
        notaCreada.setTipoEvaluacion(new TipoEvaluacion(nota.getIdTipoEvaluacion()));
        notaCreada.setAlumnoSeccion(new AlumnoSeccion(obtenerAlumnoSeccion(nota.getIdAlumno(), nota.getIdSeccion())));
        notaCreada.setCalificacion(nota.getCalificacion());
        iNotaRepository.save(notaCreada);
        return new GenericoResponse(buscarNotaPorId(notaCreada.getId()));
    }


    private NotaDto buscarNotaPorId(Integer id) {
        Nota nota = iNotaRepository.findById(id).get();
        nota.setTipoEvaluacion(iTipoEvaluacionRepository.findById(nota.getTipoEvaluacion().getId()).get());
        AlumnoSeccion alumnoSeccion = iAlumnoSeccionRepository.findById(nota.getAlumnoSeccion().getId()).get();
        nota.setAlumnoSeccion(alumnoSeccion);
        return mapper.map(iNotaRepository.findById(id).get(), NotaDto.class);
    }

    private void validacionesNegocio(NotaRequest nota) {
        validacionesNulos(nota);
        if(nota.getCalificacion() < 0 || nota.getCalificacion()> 20){
            throw new NegocioValidacionException("La calificación debe estar entre 0 y 20");
        }
        if (!existeTipoEvaluacionPorId(nota.getIdTipoEvaluacion())) {
            throw new NegocioValidacionException("El tipo de Evaluación no existe");
        }
        if (!existeAlumnoPorId(nota.getIdAlumno())) {
            throw new NegocioValidacionException("El alumno no existe");
        }
        if (!existeCicloPorId(nota.getIdSeccion())) {
            throw new NegocioValidacionException("La sección no existe");
        }

        if (!existeAlumnoSeccionPorId(nota.getIdSeccion(), nota.getIdAlumno())) {
            throw new NegocioValidacionException("El alumno no está inscrito en la sección");
        }
    }

    private void validacionesNulos(NotaRequest notaRequest){
        validarNulo(notaRequest.getIdAlumno(), "idAlumno");
        validarNulo(notaRequest.getIdTipoEvaluacion(), "idTipoEvaluacion");
        validarNulo(notaRequest.getIdSeccion(), "idSeccion");
        validarNulo(notaRequest.getCalificacion(), "calificacion");
    }

    private void validarNulo(Object valor, String columna){
        if(valor == null){
            throw new NegocioValidacionException(String.format("El campo %s debe ser enviado", columna));
        }
    }

    private boolean existeTipoEvaluacionPorId(Integer id){
        return iTipoEvaluacionRepository.findById(id).isPresent();
    }
    private boolean existeCursoPorId(Integer id){
        return iCursoRepository.findById(id).isPresent();
    }
    private boolean existeAlumnoPorId(Integer id){
        return iAlumnoRepository.findById(id).isPresent();
    }
    private boolean existeCicloPorId(Integer id){
        return iCicloRepository.findById(id).isPresent();
    }

    private boolean existeAlumnoSeccionPorId(Integer idSeccion, Integer idAlumno){
        return iAlumnoSeccionRepository.findOneByAlumnoIdAndSeccionId(idAlumno, idSeccion).isPresent();
    }

    private Integer obtenerAlumnoSeccion(Integer idAlumno, Integer idSeccion){
        return iAlumnoSeccionRepository.findOneByAlumnoIdAndSeccionId(idAlumno, idSeccion).get().getId();
    }
}
