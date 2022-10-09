package com.qds.backend.evaluacion.springrestqdsnotas.configuration;

import com.qds.backend.evaluacion.springrestqdsnotas.dto.*;
import com.qds.backend.evaluacion.springrestqdsnotas.entity.AlumnoSeccion;
import com.qds.backend.evaluacion.springrestqdsnotas.entity.Seccion;
import com.qds.backend.evaluacion.springrestqdsnotas.entity.Nota;
import com.qds.backend.evaluacion.springrestqdsnotas.entity.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuracion {

    @Bean(name = "usuarioMapper")
    public ModelMapper usuarioMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(Usuario.class, UsuarioDto.class).addMappings(mapper ->
            mapper.map(origen -> origen.getNombre(),
                    UsuarioDto::setUsuario)
        );
        return modelMapper;
    }

    @Bean(name = "alumnoMapper")
    public ModelMapper alumnoMapper(){
        return new ModelMapper();
    }

    @Bean(name = "cicloMapper")
    public ModelMapper cicloMapper(){

        return new ModelMapper();
    }

    @Bean(name = "cursoMapper")
    public ModelMapper cursoMapper(){
        return new ModelMapper();
    }

    @Bean(name = "rolMapper")
    public ModelMapper rolMapper(){
        return new ModelMapper();
    }

    @Bean(name = "tipoEvaluacionMapper")
    public ModelMapper tipoEvaluacionMapper(){
        return new ModelMapper();
    }

    @Bean(name = "profesorMapper")
    public ModelMapper profesorMapper(){
        return new ModelMapper();
    }

    @Bean(name = "notaMapper")
    public ModelMapper notaMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(Nota.class, NotaDto.class).addMappings(mapper -> {
            mapper.map(origen -> origen.getAlumnoSeccion().getAlumno().getNombreCompleto(),
                    NotaDto::setAlumno);
            mapper.map(origen -> origen.getAlumnoSeccion().getSeccion().getCiclo().getDescripcionCompleta(),
                    NotaDto::setCiclo);
            mapper.map(origen -> origen.getAlumnoSeccion().getSeccion().getCurso().getDescripcion(),
                    NotaDto::setCurso);
            mapper.map(origen -> origen.getTipoEvaluacion().getDescripcion(),
                    NotaDto::setTipoEvaluacion);
        });
        return modelMapper;
    }
    @Bean(name = "seccionMapper")
    public ModelMapper seccionMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(Seccion.class, SeccionDto.class).addMappings(mapper -> {
            mapper.map(origen -> origen.getCiclo(),
                    SeccionDto::setCiclo);
            mapper.map(origen -> origen.getCurso(),
                    SeccionDto::setCurso);
            mapper.map(origen -> origen.getProfesor(),
                    SeccionDto::setProfesor);

        });
        return modelMapper;
    }

    @Bean(name = "seccionToCicloMapper")
    public ModelMapper seccionToCicloMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(Seccion.class, CicloDto.class).addMappings(mapper -> {
            mapper.map(origen -> origen.getCiclo().getNumeroCiclo(),
                    CicloDto::setNumeroCiclo);
            mapper.map(origen -> origen.getCiclo().getAnio(),
                    CicloDto::setAnio);
            mapper.map(origen -> origen.getCiclo().getId(),
                    CicloDto::setId);
            mapper.map(origen -> origen.getCiclo().getFechaInicio(),
                    CicloDto::setFechaInicio);
            mapper.map(origen -> origen.getCiclo().getFechaFin(),
                    CicloDto::setFechaFin);
        });
        return modelMapper;
    }

    @Bean(name = "seccionToCursoMapper")
    public ModelMapper seccionToCursoMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(Seccion.class, CursoDto.class).addMappings(mapper -> {
            mapper.map(origen -> origen.getCurso().getDescripcion(),
                    CursoDto::setDescripcion);
            mapper.map(origen -> origen.getCurso().getId(),
                    CursoDto::setId);
        });
        return modelMapper;
    }

    @Bean(name = "alumnoSeccionToSeccionMapper")
    public ModelMapper alumnoSeccionToSeccionMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(AlumnoSeccion.class, SeccionDto.class).addMappings(mapper -> {
            mapper.map(origen -> origen.getSeccion().getCiclo(),
                    SeccionDto::setCiclo);
            mapper.map(origen -> origen.getSeccion().getCurso(),
                    SeccionDto::setCurso);
            mapper.map(origen -> origen.getSeccion().getId(),
                    SeccionDto::setId);
        });
        return modelMapper;
    }

    @Bean(name = "alumnoSeccionToAlumnoMapper")
    public ModelMapper alumnoSeccionToAlumnoMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(AlumnoSeccion.class, AlumnoDto.class).addMappings(mapper -> {
            mapper.map(origen -> origen.getAlumno().getActivo(),
                    AlumnoDto::setActivo);
            mapper.map(origen -> origen.getAlumno().getNombre(),
                    AlumnoDto::setNombre);
            mapper.map(origen -> origen.getAlumno().getApellido(),
                    AlumnoDto::setApellido);
            mapper.map(origen -> origen.getAlumno().getFechaNacimiento(),
                    AlumnoDto::setFechaNacimiento);
            mapper.map(origen -> origen.getAlumno().getDni(),
                    AlumnoDto::setDni);
            mapper.map(origen -> origen.getAlumno().getId(),
                    AlumnoDto::setId);
        });
        return modelMapper;
    }

}
