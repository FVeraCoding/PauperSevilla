package com.backendPauper.converter;

import org.springframework.stereotype.Component;

import com.backendPauper.entity.JugadorEntity;
import com.backendPauper.vo.JugadorVO;

@Component
public class JugadorConverter {

	public JugadorVO toVO(JugadorEntity entity) {

		if (entity != null) {
			return new JugadorVO(entity.getId(), entity.getNombre(), entity.getVictorias(), entity.getEmpates(),
					entity.getDerrotas());
		}

		return null;
	}

	public JugadorEntity toEntity(JugadorVO vo) {

		if (vo != null) {
			return new JugadorEntity(vo.getId(), vo.getNombre(), vo.getVictorias(), vo.getEmpates(), vo.getDerrotas());
		}

		return null;
	}

}
