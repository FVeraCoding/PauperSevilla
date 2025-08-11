package com.backendPauper.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendPauper.service.ParticipacionService;
import com.backendPauper.vo.ParticipacionVO;

@RestController
@RequestMapping("/participacion")
public class ParticipacionController {

	ParticipacionService participacionService;

	public ParticipacionController(ParticipacionService participacionService) {
		this.participacionService = participacionService;
	}

	@GetMapping("/{nombre}")
	public ResponseEntity<List<ParticipacionVO>> getParticipacionesByJugadorNombre(@PathVariable String nombre) {
		List<ParticipacionVO> participaciones = participacionService.findByJugadorNombre(nombre);
		return participaciones != null ? ResponseEntity.ok(participaciones) : ResponseEntity.notFound().build();
	}
	
	@GetMapping
	public ResponseEntity<List<ParticipacionVO>> getAllParticipaciones(){
		return ResponseEntity.ok(participacionService.findAllParticipaciones());
	}

	@PostMapping
	public ResponseEntity<ParticipacionVO> createParticipacion(@RequestBody ParticipacionVO participacion) {
		return ResponseEntity.status(HttpStatus.CREATED).body(participacionService.createParticipacion(participacion));
	}

	@PutMapping
	public ResponseEntity<ParticipacionVO> updateParticipacion(@RequestBody ParticipacionVO participacion) {
		return ResponseEntity.ok(participacionService.updateParticipacion(participacion));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteParticipacion(@PathVariable Long id) {
		participacionService.deleteParticipacionById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
