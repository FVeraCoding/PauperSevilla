package com.backendPauper.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendPauper.entity.UsuarioEntity;
import com.backendPauper.repository.UsuarioRepository;
import com.backendPauper.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepository;

    public AuthController(JwtUtil jwtUtil, UsuarioRepository usuarioRepository) {
		this.jwtUtil = jwtUtil;
		this.usuarioRepository = usuarioRepository;
	}

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> req) {
        String username = req.get("username");
        String password = req.get("password");

        UsuarioEntity usuario = usuarioRepository.findByUsername(username);

        if (usuario != null && usuario.getPassword().equals(password)) {
            String token = jwtUtil.generateToken(username);
            return ResponseEntity.ok(Map.of("token", token));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
    }


}


