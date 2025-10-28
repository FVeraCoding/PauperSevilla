package com.backendPauper.service;

import java.util.List;

import com.backendPauper.vo.UsuarioVO;

public interface UsuarioService {

	UsuarioVO createUsuario(UsuarioVO usuario);
	UsuarioVO updateUsuario(UsuarioVO usuario);
	UsuarioVO getUsuarioById(Long id);
	UsuarioVO getusuarioByUsername(String username);
	List<UsuarioVO> getAllUsuarios();
	void deleteUsuarioById(Long id);
}
