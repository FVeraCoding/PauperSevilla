package com.backendPauper.converter;

import org.springframework.stereotype.Component;

import com.backendPauper.entity.JugadorEntity;
import com.backendPauper.entity.ParticipacionEntity;
import com.backendPauper.entity.TorneoEntity;
import com.backendPauper.vo.ParticipacionVO;

@Component
public class ParticipacionConverter {

	public ParticipacionVO toVO(ParticipacionEntity entity) {
		return new ParticipacionVO(entity.getId(), entity.getJugador().getId(), entity.getTorneo().getId(),
				entity.getVictorias(), entity.getDerrotas(), entity.getEmpates(), entity.getClasificacion(), entity.getDeck_utilizado());
	}
	
	public ParticipacionEntity toEntity(JugadorEntity jugador, TorneoEntity torneo, ParticipacionVO vo) {
		return new ParticipacionEntity(jugador, torneo, vo.getVictorias(), vo.getDerrotas(),
				vo.getEmpates(), vo.getClasificacion(), vo.getDeck_utilizado());
	}
	
}
