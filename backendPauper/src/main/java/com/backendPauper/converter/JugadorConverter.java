package com.backendPauper.converter;

import org.springframework.stereotype.Component;

import com.backendPauper.entity.JugadorEntity;
import com.backendPauper.repository.UsuarioRepository;
import com.backendPauper.vo.JugadorVO;

@Component
public class JugadorConverter {
	
	
	UsuarioRepository usuarioRepository;

	public JugadorConverter(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public JugadorVO toVO(JugadorEntity entity) {

		if (entity != null) {
			return new JugadorVO(entity.getId(), entity.getNombre(), entity.getVictorias(), entity.getEmpates(),
					entity.getDerrotas(), entity.getUsuario().getId());
		}

		return null;
	}

	public JugadorEntity toEntity(JugadorVO vo) {

		if (vo != null) {
			JugadorEntity entity = new JugadorEntity(vo.getId(), vo.getNombre(), vo.getVictorias(), vo.getEmpates(), vo.getDerrotas());
			entity.setUsuario(usuarioRepository.findById(vo.getUsuarioId())
					.orElseThrow( () -> new RuntimeException("Usuario no encontrado") ));
			return entity;
		}

		return null;
	}

}
