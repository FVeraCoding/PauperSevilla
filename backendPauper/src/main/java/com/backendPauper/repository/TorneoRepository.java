package com.backendPauper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backendPauper.entity.JugadorEntity;
import com.backendPauper.entity.TorneoEntity;

@Repository
public interface TorneoRepository extends JpaRepository<TorneoEntity, Long> {

	List<TorneoEntity> findByTiendaOrganizadora(String tiendaOrganizadora);

	@Query("SELECT t FROM TorneoEntity t WHERE MONTH(t.fecha) = :mes AND YEAR(t.fecha) = :anio")
	List<TorneoEntity> findByMesAndAnio(int mes, int anio);
	
	@Query("Select j FROM JugadorEntity j JOIN j.torneos t WHERE t.id = :torneoId")
	List<JugadorEntity> findJugadoresByTorneoId(@Param("torneoId") Long torneoId);
}
