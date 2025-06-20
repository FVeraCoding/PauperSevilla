package com.backendPauper.converter;

import org.springframework.stereotype.Component;

import com.backendPauper.entity.TorneoEntity;
import com.backendPauper.vo.TorneoVO;

@Component
public class TorneoConverter {

	public TorneoVO toVO(TorneoEntity entity) {

		if (entity != null) {
			return new TorneoVO(entity.getId(), entity.getFecha(), entity.getNumeroRondas(),
					entity.getNumeroParticipantes(), entity.getTiendaOrganizadora());
		}

		return null;
	}

	public TorneoEntity toEntity(TorneoVO vo) {

		if (vo != null) {
			return new TorneoEntity(vo.getId(), vo.getFecha(), vo.getNumeroRondas(), vo.getNumeroParticipantes(),
					vo.getTiendaOrganizadora());
		}

		return null;
	}

}
