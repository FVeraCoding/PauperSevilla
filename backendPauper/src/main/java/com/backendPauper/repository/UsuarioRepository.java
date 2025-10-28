package com.backendPauper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backendPauper.entity.UsuarioEntity;
import com.backendPauper.vo.UsuarioVO;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>{

	UsuarioEntity findByUsername(String Username);
	
}
