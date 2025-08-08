package com.backendPauper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backendPauper.entity.JugadorEntity;
import com.backendPauper.entity.TorneoEntity;

@Repository
public interface JugadorRepository extends JpaRepository<JugadorEntity, Long> {

	JugadorEntity findByNombre(String nombre);

	@Query("SELECT t FROM TorneoEntity t JOIN t.jugadores j WHERE j.id = :id")
	List<TorneoEntity> findTorneosByJugadorId(@Param("id") Long id);
}
