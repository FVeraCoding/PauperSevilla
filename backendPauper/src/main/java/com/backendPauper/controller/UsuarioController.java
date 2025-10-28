package com.backendPauper.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendPauper.service.UsuarioService;
import com.backendPauper.vo.UsuarioVO;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}

	@GetMapping()
	public ResponseEntity getAllUsuarios() {
		return ResponseEntity.ok(usuarioService.getAllUsuarios());
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity getUsuarioById(@PathVariable Long id) {
		
		UsuarioVO usuario = usuarioService.getUsuarioById(id);
		return (usuario != null) ? ResponseEntity.ok(usuario) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
	}
	
	@GetMapping("/username/{username}")
		public ResponseEntity getUsuarioByUsername(@PathVariable String username) {
			UsuarioVO usuario = usuarioService.getusuarioByUsername(username);
			return (usuario != null) ? ResponseEntity.ok(usuario) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@PostMapping()
	public ResponseEntity createUsuario(@RequestBody UsuarioVO usuario) {
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.createUsuario(usuario));
	}
	
	@PutMapping()
	public ResponseEntity updateUsuario(@RequestBody UsuarioVO usuario) {
		return ResponseEntity.ok().body(usuarioService.updateUsuario(usuario));
	}
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity updateUsuario(@PathVariable Long id) {
		usuarioService.deleteUsuarioById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
