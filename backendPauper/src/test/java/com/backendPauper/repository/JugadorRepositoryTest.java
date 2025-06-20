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
public class JugadorRepositoryTest {

	@Autowired
	private JugadorRepository jugadorRepository;
	
	@Autowired
	private TorneoRepository torneoRepository;
	
	
	@Test
	void testFindJugadorByNombre() {
		
		JugadorEntity jugador = new JugadorEntity("FVera", 5, 2, 1);
		jugadorRepository.save(jugador);
		
		JugadorEntity buscado = jugadorRepository.findByNombre(jugador.getNombre());
		
		assertNotNull(buscado);
		assertEquals(jugador.getId(), buscado.getId());
		assertEquals(jugador.getNombre(), buscado.getNombre());
		assertEquals(jugador.getVictorias(), buscado.getVictorias());
		assertEquals(jugador.getEmpates(), buscado.getEmpates());
		assertEquals(jugador.getDerrotas(), buscado.getDerrotas());
	}
	
	
	@Test
	void findTorneoByJugadorId() {
		
		JugadorEntity jugador = new JugadorEntity("FVera", 5, 2, 1);
		TorneoEntity torneo = new TorneoEntity(LocalDate.of(2025, 2, 15), 5, 16, "Empire Games");
		TorneoEntity torneo2 = new TorneoEntity(LocalDate.of(2025, 2, 15), 5, 16, "Midgar");

		
		jugador.getTorneos().add(torneo);
		jugador.getTorneos().add(torneo2);
		torneo.getJugadores().add(jugador);
		torneo2.getJugadores().add(jugador);
		
		torneoRepository.save(torneo);
		torneoRepository.save(torneo2);
		jugadorRepository.save(jugador);
		
		List<TorneoEntity> torneos = jugadorRepository.findTorneosByJugadorId(jugador.getId());
		
		assertNotNull(torneos);
		assertEquals(2, torneos.size());
		assertEquals("Empire Games", torneos.get(0).getTiendaOrganizadora());
		assertEquals("Midgar", torneos.get(1).getTiendaOrganizadora());
		
	}
}
