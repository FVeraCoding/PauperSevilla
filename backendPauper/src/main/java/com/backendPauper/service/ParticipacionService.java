package com.backendPauper.service;

import java.util.List;

import com.backendPauper.vo.ParticipacionVO;

public interface ParticipacionService {

	List<ParticipacionVO> findByJugadorNombre(String nombre);
	List<ParticipacionVO> findAllParticipaciones();
	ParticipacionVO createParticipacion(ParticipacionVO participacion);
	ParticipacionVO updateParticipacion(ParticipacionVO participacion);
	void deleteParticipacionById(Long id);
	
}
