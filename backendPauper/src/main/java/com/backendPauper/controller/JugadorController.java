package com.backendPauper.controller;

import java.util.List;

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

import com.backendPauper.service.JugadorService;
import com.backendPauper.vo.JugadorVO;

@RestController
@RequestMapping("/jugador")
public class JugadorController {

	private JugadorService jugadorService;

	public JugadorController(JugadorService jugadorService) {
		super();
		this.jugadorService = jugadorService;
	}
	
	@GetMapping()
	public ResponseEntity<List<JugadorVO>> findAllJugadores() {
		return ResponseEntity.ok(jugadorService.findAll());
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<JugadorVO> findJugadorById(@PathVariable Long id){
		JugadorVO vo = jugadorService.findById(id);
		return (vo != null) ? ResponseEntity.ok(vo) : ResponseEntity.notFound().build();
	}

	@GetMapping("/nombre/{nombre}")
	public ResponseEntity<JugadorVO> findJugadorByNombre(@PathVariable String nombre) {
		JugadorVO vo = jugadorService.findByNombre(nombre);	
		return (vo != null) ? ResponseEntity.ok(vo) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<JugadorVO> createJugador(@RequestBody JugadorVO jugador){
		return ResponseEntity.status(HttpStatus.CREATED).body(jugadorService.createJugador(jugador));
	}
	
	@PostMapping("/jugador/{idJugador}/torneo/{idTorneo}")
	public ResponseEntity<?> inscribirJugadorTorneo(@PathVariable Long idJugador, @PathVariable Long idTorneo){
		jugadorService.inscribirJugadorEnTorneo(idJugador, idTorneo);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping
	public ResponseEntity<JugadorVO> updateJugador(@RequestBody JugadorVO jugador){
		return ResponseEntity.ok(jugadorService.updateJugador(jugador));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteJugadorById(@PathVariable Long id){
		jugadorService.deleteJugadorById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	

}
