package com.qds.backend.evaluacion.springrestqdsnotas.utilitario;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import java.util.Base64;

public final class Util {
    public static final String MENSAJE_CALIFICACION_0_20 = "La calificación debe estar entre 0 y 20";
    public static final String MENSAJE_ALUMNO_NO_EXISTE = "El alumno no existe";
    public static final String MENSAJE_SECCION_NO_EXISTE = "La sección no existe";
    public static final String MENSAJE_TIPO_EVALUACION_NO_EXISTE = "El tipo de Evaluación no existe";
    public static final String MENSAJE_ALUMNO_NO_INSCRITO_SECCION = "El alumno no está inscrito en la sección";
    public static final String MENSAJE_CAMPO_NULO = "El campo %s debe ser enviado";
    public static final String MENSAJE_USUARIO_NO_EXISTE = "El usuario no existe";
    public static final String MENSAJE_ALUMNO_NO_ASOCIADO = "No existe un alumno asociado al usuario";
    public static final String MENSAJE_SOLO_PROFESORES = "Solo los profesores pueden registrar notas";
    public static final String MENSAJE_NOTA_YA_REGISTRADA = "La nota ya fue registrada";

    public static final String MENSAJE_PROFESOR_NO_ENSENIA_SECCION = "El profesor no es el responsable de la sección";


    public static final String obtenerUsuarioActual(Authentication authentication){
        User usuario = (User) authentication.getPrincipal();
        return usuario.getUsername();
    }

    public static String[] obtenerBasicAuth(String auth)  throws Exception {

        if (!auth.startsWith("Basic ")) throw new Exception("El header no contiene el formato correcto");
        byte[] decodedBytes = Base64.getDecoder().decode( auth.substring(6));
        String[] credentials = new String(decodedBytes).split(":");
        if (credentials.length != 2) throw new Exception("El header no contiene el formato correcto");
        return credentials;
    }
}
