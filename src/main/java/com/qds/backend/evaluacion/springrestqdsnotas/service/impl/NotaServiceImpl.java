package com.qds.backend.evaluacion.springrestqdsnotas.service.impl;

import com.qds.backend.evaluacion.springrestqdsnotas.dto.NotaDto;
import com.qds.backend.evaluacion.springrestqdsnotas.entity.*;
import com.qds.backend.evaluacion.springrestqdsnotas.exception.NegocioValidacionException;
import com.qds.backend.evaluacion.springrestqdsnotas.repository.*;
import com.qds.backend.evaluacion.springrestqdsnotas.request.NotaRequest;
import com.qds.backend.evaluacion.springrestqdsnotas.response.GenericoResponse;
import com.qds.backend.evaluacion.springrestqdsnotas.security.util.JwtTokenUtil;
import com.qds.backend.evaluacion.springrestqdsnotas.service.INotaService;
import com.qds.backend.evaluacion.springrestqdsnotas.utilitario.Util;
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
    private ISeccionRepository iSeccionRepository;
    @Autowired
    private IAlumnoSeccionRepository iAlumnoSeccionRepository;

    @Autowired
    private IProfesorRepository iProfesorRepository;

    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    @Qualifier("notaMapper")
    private ModelMapper mapper;

    @Override
    public GenericoResponse<List<NotaDto>> listarTodosPorAlumno(String nombreUsuario) {
        Alumno alumno = iAlumnoRepository.findOneByUsuarioNombre(nombreUsuario).orElse(null);
        if(alumno == null){
            throw new NegocioValidacionException(Util.MENSAJE_ALUMNO_NO_ASOCIADO);
        }
        return new GenericoResponse(iNotaRepository.findAllByAlumnoSeccionAlumnoId(alumno.getId()).stream().map(x-> mapper.map(x, NotaDto.class)).collect(Collectors.toList()));
    }


    @Override
    public GenericoResponse<NotaDto> crearNota(NotaRequest nota) {
        validaciones(nota);
        Nota notaCreada = new Nota();
        notaCreada.setFechaRegistro(LocalDateTime.now());
        notaCreada.setUsuario(obtenerUsuario(nota.getNombreUsuario()));
        notaCreada.setTipoEvaluacion(new TipoEvaluacion(nota.getIdTipoEvaluacion()));
        notaCreada.setAlumnoSeccion(new AlumnoSeccion(obtenerAlumnoSeccion(nota.getIdAlumno(), nota.getIdSeccion())));
        notaCreada.setCalificacion(nota.getCalificacion());
        Nota notaGuardada = iNotaRepository.save(notaCreada);
        return new GenericoResponse(buscarNotaPorId(notaGuardada.getId()));
    }

    private Usuario obtenerUsuario(String nombreUsuario){
        Usuario usuario = iUsuarioRepository.findByNombre(nombreUsuario).orElse(null);
        if(usuario == null){
            throw new NegocioValidacionException(Util.MENSAJE_USUARIO_NO_EXISTE);
        }else {
            return usuario;
        }
    }

    private NotaDto buscarNotaPorId(Integer id) {
        Nota nota = iNotaRepository.findById(id).get();
        nota.setTipoEvaluacion(iTipoEvaluacionRepository.findById(nota.getTipoEvaluacion().getId()).get());
        AlumnoSeccion alumnoSeccion = iAlumnoSeccionRepository.findById(nota.getAlumnoSeccion().getId()).get();
        nota.setAlumnoSeccion(alumnoSeccion);
        NotaDto notaDto = mapper.map(nota, NotaDto.class);
        return notaDto;
    }

    private void validaciones(NotaRequest notaRequest) {
        validacionesNulos(notaRequest);
        validacionesExistencia(notaRequest);
        validacionesNegocio(notaRequest);
    }

    private void validacionesNegocio(NotaRequest notaRequest){
        if(notaRequest.getCalificacion() < 0 || notaRequest.getCalificacion()> 20){
            throw new NegocioValidacionException(Util.MENSAJE_CALIFICACION_0_20);
        }

        Profesor profesor = iProfesorRepository.findOneByUsuarioNombre(notaRequest.getNombreUsuario()).orElse(null);
        if(profesor == null){
            throw new NegocioValidacionException(Util.MENSAJE_SOLO_PROFESORES);
        }

        Nota nota = iNotaRepository.findOneByTipoEvaluacionIdAndAlumnoSeccionAlumnoIdAndAlumnoSeccionSeccionId(notaRequest.getIdTipoEvaluacion(), notaRequest.getIdAlumno(),notaRequest.getIdSeccion()).orElse(null);
        if(nota != null){
            throw new NegocioValidacionException(Util.MENSAJE_NOTA_YA_REGISTRADA);
        }
    }
    private void validacionesExistencia(NotaRequest notaRequest)
    {
        if (!existeTipoEvaluacionPorId(notaRequest.getIdTipoEvaluacion())) {
            throw new NegocioValidacionException(Util.MENSAJE_TIPO_EVALUACION_NO_EXISTE);
        }
        if (!existeAlumnoPorId(notaRequest.getIdAlumno())) {
            throw new NegocioValidacionException(Util.MENSAJE_ALUMNO_NO_EXISTE);
        }
        if (!existeSeccionPorId(notaRequest.getIdSeccion())) {
            throw new NegocioValidacionException(Util.MENSAJE_SECCION_NO_EXISTE);
        }
        if (!existeAlumnoSeccionPorId(notaRequest.getIdSeccion(), notaRequest.getIdAlumno())) {
            throw new NegocioValidacionException(Util.MENSAJE_ALUMNO_NO_INSCRITO_SECCION);
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
            throw new NegocioValidacionException(String.format(Util.MENSAJE_CAMPO_NULO, columna));
        }
    }

    private boolean existeTipoEvaluacionPorId(Integer id){
        return iTipoEvaluacionRepository.findById(id).isPresent();
    }
    private boolean existeAlumnoPorId(Integer id){
        return iAlumnoRepository.findById(id).isPresent();
    }
    private boolean existeSeccionPorId(Integer id){
        return iSeccionRepository.findById(id).isPresent();
    }

    private boolean existeAlumnoSeccionPorId(Integer idSeccion, Integer idAlumno){
        return iAlumnoSeccionRepository.findOneByAlumnoIdAndSeccionId(idAlumno, idSeccion).isPresent();
    }

    private Integer obtenerAlumnoSeccion(Integer idAlumno, Integer idSeccion){
        return iAlumnoSeccionRepository.findOneByAlumnoIdAndSeccionId(idAlumno, idSeccion).get().getId();
    }
}
