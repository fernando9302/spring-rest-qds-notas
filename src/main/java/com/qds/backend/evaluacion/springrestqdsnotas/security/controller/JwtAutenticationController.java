package com.qds.backend.evaluacion.springrestqdsnotas.security.controller;

import com.qds.backend.evaluacion.springrestqdsnotas.security.request.JwtRequest;
import com.qds.backend.evaluacion.springrestqdsnotas.security.response.JwtResponse;
import com.qds.backend.evaluacion.springrestqdsnotas.security.service.JwtUserDetailsService;
import com.qds.backend.evaluacion.springrestqdsnotas.security.util.JwtTokenUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Autenticación", description = "Servicio para autenticar")
@RestController
@CrossOrigin
public class JwtAutenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @ApiOperation(value = "Autenticar", authorizations = {@Authorization(value = "")})
    @PostMapping(value = "/autenticar")
    @ApiImplicitParams({
            //Overriding global behavior.
            @ApiImplicitParam(
                    name = "Authorization", value = "")
    })
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest autenticacionRequest) throws Exception {

        authenticate(autenticacionRequest.getUsuario(), autenticacionRequest.getContrasenia());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(autenticacionRequest.getUsuario());

        final String token = jwtTokenUtil.generarToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
