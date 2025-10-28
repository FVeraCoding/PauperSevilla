package com.backendPauper.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backendPauper.converter.UsuarioConverter;
import com.backendPauper.entity.UsuarioEntity;
import com.backendPauper.repository.UsuarioRepository;
import com.backendPauper.service.UsuarioService;
import com.backendPauper.vo.UsuarioVO;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	UsuarioRepository usuarioRepository;
	UsuarioConverter usuarioConverter;
	
	@Override
	public List<UsuarioVO> getAllUsuarios() {
		return usuarioRepository.findAll()
				.stream()
				.map(usuarioConverter::toVO)
				.toList();
	}
	
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioConverter usuarioConverter) {
		this.usuarioRepository = usuarioRepository;
		this.usuarioConverter = usuarioConverter;
	}

	@Override
	public UsuarioVO getUsuarioById(Long id) {
		return usuarioConverter.toVO(usuarioRepository.findById(id)
				.orElseThrow( () -> new RuntimeException("No se ha encontrado el usuario especificado")));
	}

	@Override
	public UsuarioVO getusuarioByUsername(String username) {
		return usuarioConverter.toVO(usuarioRepository.findByUsername(username));
	}	
	
	@Override
	public UsuarioVO createUsuario(UsuarioVO usuario) {
		return  usuarioConverter.toVO(usuarioRepository.save(usuarioConverter.toEntity(usuario)));
	}

	@Override
	public UsuarioVO updateUsuario(UsuarioVO usuario) {
		UsuarioEntity existente = usuarioRepository.findById(usuario.getId())
				.orElseThrow( () -> new RuntimeException("No se ha encontrado el usuario especificado"));
		
		existente.setUsername(usuario.getUsername());
		existente.setPassword(usuario.getPassword());
		
		return usuarioConverter.toVO(usuarioRepository.save(existente));
	}


	@Override
	public void deleteUsuarioById(Long id) {
		usuarioRepository.deleteById(id);
	}



}
