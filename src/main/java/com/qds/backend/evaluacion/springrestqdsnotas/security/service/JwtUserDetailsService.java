package com.qds.backend.evaluacion.springrestqdsnotas.security.service;

import com.qds.backend.evaluacion.springrestqdsnotas.entity.Usuario;
import com.qds.backend.evaluacion.springrestqdsnotas.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        //return new User("fernando", "{noop}12345", new ArrayList<>());
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByNombre(nombreUsuario);
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(usuarioEncontrado.get().getRol().getDescripcion()));
        if(usuarioEncontrado.isPresent()){
                return new User(nombreUsuario, usuarioEncontrado.get().getContrasenia(), roles );
        }else{
            throw new UsernameNotFoundException(nombreUsuario);
        }
    }
}
