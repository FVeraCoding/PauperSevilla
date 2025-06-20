package com.backendPauper.service;

import java.util.List;

import com.backendPauper.vo.JugadorVO;

public interface JugadorService {

	JugadorVO findByNombre(String nombre);
	List<JugadorVO> findAll();
	JugadorVO findById(Long id);
	JugadorVO updateJugador(JugadorVO jugador);
	void deleteJugadorById(Long id);
	JugadorVO createJugador(JugadorVO jugador);
	void inscribirJugadorEnTorneo(Long jugadorId, Long torneoId);
	
}
