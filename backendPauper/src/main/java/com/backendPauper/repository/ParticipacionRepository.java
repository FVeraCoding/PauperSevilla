package com.backendPauper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backendPauper.entity.ParticipacionEntity;

@Repository
public interface ParticipacionRepository extends JpaRepository<ParticipacionEntity, Long>{

	List<ParticipacionEntity> findByJugadorNombre(String nombre);
	boolean existsByJugadorIdAndTorneoId(Long jugadorId, Long torneoId);
	
	
}
