package com.backendPauper.converter;

import org.springframework.stereotype.Component;

import com.backendPauper.entity.UsuarioEntity;
import com.backendPauper.vo.UsuarioVO;

@Component
public class UsuarioConverter {

	public UsuarioEntity toEntity(UsuarioVO vo) {
		return new UsuarioEntity(vo.getId(), vo.getUsername(), vo.getPassword());
	}
	
	public UsuarioVO toVO(UsuarioEntity entity) {
		return new UsuarioVO(entity.getId(), entity.getUsername(), entity.getPassword());
	}
	
}
