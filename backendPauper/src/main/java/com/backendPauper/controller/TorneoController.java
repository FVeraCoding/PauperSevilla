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

import com.backendPauper.service.TorneoService;
import com.backendPauper.vo.TorneoVO;

@RestController
@RequestMapping("/torneo")
public class TorneoController {

	private final TorneoService torneoService;
		
	public TorneoController(TorneoService torneoService) {
		this.torneoService = torneoService;
	}

	@GetMapping
	public ResponseEntity<List<TorneoVO>> findAll(){
		return ResponseEntity.ok(torneoService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TorneoVO> findById(@PathVariable Long id){
		TorneoVO vo = torneoService.findById(id);
		return (vo != null) ? ResponseEntity.ok(vo) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/tienda/{tiendaOrganizadora}")
	public ResponseEntity<List<TorneoVO>> findByTiendaOrganizadora(@PathVariable String tiendaOrganizadora){
		return ResponseEntity.ok(torneoService.findByTiendaOrganizadora(tiendaOrganizadora));
	}
	
	@GetMapping("mes/{mes}/anio/{anio}")
	public ResponseEntity<List<TorneoVO>> findByMesAndAnio(@PathVariable int anio, @PathVariable int mes){
		return ResponseEntity.ok(torneoService.findByMesAndAnio(mes, anio));
	}
	
	@PostMapping
	public ResponseEntity<TorneoVO> createTorneo(@RequestBody TorneoVO torneo){
		return ResponseEntity.status(HttpStatus.CREATED).body(torneoService.createTorneo(torneo));
	}
	
	@PutMapping
	public ResponseEntity<TorneoVO> updateTorneo(@RequestBody TorneoVO torneo){
		return ResponseEntity.ok(torneoService.updateTorneo(torneo));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTorneoById(@PathVariable Long id){
		torneoService.deleteTorneoById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	
}
