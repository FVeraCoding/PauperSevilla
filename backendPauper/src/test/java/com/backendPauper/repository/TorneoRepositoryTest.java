package com.backendPauper.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.backendPauper.entity.JugadorEntity;
import com.backendPauper.entity.TorneoEntity;

@DataJpaTest
public class TorneoRepositoryTest {

	@Autowired
	TorneoRepository torneoRepository;
	
	@Autowired
	JugadorRepository jugadorRepository;
	
	@Test
	void findByTiendaOrganizadora() {
		
		TorneoEntity torneo = new TorneoEntity(LocalDate.of(2025, 2, 15), 5, 16, "Empire Games");
		torneoRepository.save(torneo);
		
		TorneoEntity torneo2 = new TorneoEntity(LocalDate.of(2025, 3, 7), 4, 13, "Empire Games");
		torneoRepository.save(torneo2);
		
		List<TorneoEntity> buscados = torneoRepository.findByTiendaOrganizadora("Empire Games");
		
		// Not null
		assertNotNull(buscados);
		
		// Tamanio
		assertEquals(2, buscados.size());
		
		// Torneo 1
		assertEquals(buscados.get(0).getId(), torneo.getId());
		assertEquals(buscados.get(0).getNumeroRondas(), torneo.getNumeroRondas());
		assertEquals(buscados.get(0).getNumeroParticipantes(), torneo.getNumeroParticipantes());
		assertEquals(buscados.get(0).getTiendaOrganizadora(), torneo.getTiendaOrganizadora());
		
		// Torneo 2
		assertEquals(buscados.get(1).getId(), torneo2.getId());
		assertEquals(buscados.get(1).getNumeroRondas(), torneo2.getNumeroRondas());
		assertEquals(buscados.get(1).getNumeroParticipantes(), torneo2.getNumeroParticipantes());
		assertEquals(buscados.get(1).getTiendaOrganizadora(), torneo2.getTiendaOrganizadora());
		
	}
	
	
	@Test
	void findByMesAndAnio() {
		TorneoEntity torneo = new TorneoEntity(LocalDate.of(2025, 2, 15), 5, 16, "Empire Games");
		torneoRepository.save(torneo);
		
		TorneoEntity torneo2 = new TorneoEntity(LocalDate.of(2025, 2, 25), 5, 16, "Empire Games");
		torneoRepository.save(torneo2);
		
		List<TorneoEntity> buscados = torneoRepository.findByMesAndAnio(2, 2025);
		
		// Not null
		assertNotNull(buscados);
		
		// Tamanio
		assertEquals(2, buscados.size());
		
		// Torneo 1
		assertEquals(buscados.get(0).getId(), torneo.getId());
		assertEquals(buscados.get(0).getNumeroRondas(), torneo.getNumeroRondas());
		assertEquals(buscados.get(0).getNumeroParticipantes(), torneo.getNumeroParticipantes());
		assertEquals(buscados.get(0).getTiendaOrganizadora(), torneo.getTiendaOrganizadora());

		// Torneo 2
		assertEquals(buscados.get(1).getId(), torneo2.getId());
		assertEquals(buscados.get(1).getNumeroRondas(), torneo2.getNumeroRondas());
		assertEquals(buscados.get(1).getNumeroParticipantes(), torneo2.getNumeroParticipantes());
		assertEquals(buscados.get(1).getTiendaOrganizadora(), torneo2.getTiendaOrganizadora());
	}
	
	@Test
	void findJugadoresByTorneoId() {
		
		TorneoEntity torneo = new TorneoEntity(LocalDate.of(2025, 2, 15), 5, 16, "Empire Games");
		
		JugadorEntity jugador1 = new JugadorEntity("FVera", 5, 2, 1);
		JugadorEntity jugador2 = new JugadorEntity("Azalea", 5, 2, 1);
		
		torneo.getJugadores().add(jugador1);
		torneo.getJugadores().add(jugador2);
		jugador1.getTorneos().add(torneo);
		jugador2.getTorneos().add(torneo);
		
		torneoRepository.save(torneo);
		jugadorRepository.save(jugador1);
		jugadorRepository.save(jugador2);
		
		List<JugadorEntity> jugadores = torneoRepository.findJugadoresByTorneoId(torneo.getId());
		
		assertNotNull(jugadores);
		assertEquals(2, jugadores.size());
		assertEquals("FVera", jugadores.get(0).getNombre());
		assertEquals("Azalea", jugadores.get(1).getNombre());
		
	}
	
}
