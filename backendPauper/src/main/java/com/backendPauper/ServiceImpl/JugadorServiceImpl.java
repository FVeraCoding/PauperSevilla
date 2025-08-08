package com.backendPauper.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backendPauper.converter.JugadorConverter;
import com.backendPauper.entity.JugadorEntity;
import com.backendPauper.entity.TorneoEntity;
import com.backendPauper.repository.JugadorRepository;
import com.backendPauper.repository.TorneoRepository;
import com.backendPauper.service.JugadorService;
import com.backendPauper.vo.JugadorVO;

import jakarta.transaction.Transactional;

@Service
public class JugadorServiceImpl implements JugadorService {

	private final JugadorRepository jugadorRepository;
	private final JugadorConverter jugadorConverter;
	private final TorneoRepository torneoRepository;

	public JugadorServiceImpl(JugadorRepository jugadorRepository, JugadorConverter jugadorConverter,
			TorneoRepository torneoRepository) {
		this.jugadorRepository = jugadorRepository;
		this.jugadorConverter = jugadorConverter;
		this.torneoRepository = torneoRepository;
	}

	@Override
	public JugadorVO findByNombre(String nombre) {

		return jugadorConverter.toVO(jugadorRepository.findByNombre(nombre));

	}

	@Override
	public List<JugadorVO> findAll() {

		return jugadorRepository.findAll().stream().map(jugadorConverter::toVO).toList();

	}

	@Override
	public JugadorVO findById(Long id) {

		return jugadorConverter.toVO(jugadorRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("No se ha encontrado el jugador con el ID especificado")));
	}

	@Override
	public JugadorVO updateJugador(JugadorVO jugador) {
		JugadorEntity existente = jugadorRepository.findById(jugador.getId())
				.orElseThrow(() -> new RuntimeException("No se ha encontrado el jugador para actualizar"));

		existente.setNombre(jugador.getNombre());
		existente.setVictorias(jugador.getVictorias());
		existente.setEmpates(jugador.getEmpates());
		existente.setDerrotas(jugador.getDerrotas());

		return jugadorConverter.toVO(jugadorRepository.save(existente));
	}

	@Override
	public void deleteJugadorById(Long id) {
		jugadorRepository.deleteById(id);
	}

	@Override
	public JugadorVO createJugador(JugadorVO jugador) {
		JugadorEntity saved = jugadorRepository.save(jugadorConverter.toEntity(jugador));

		return jugadorConverter.toVO(saved);
	}

	@Override
	@Transactional //Es recomendable cuando se accede a múltiples repositorios en una misma operación
	public void inscribirJugadorEnTorneo(Long jugadorId, Long torneoId) {

		JugadorEntity jugador = jugadorRepository.findById(jugadorId)
				.orElseThrow(() -> new RuntimeException("No se ha encontrado el jugador especificado"));

		TorneoEntity torneo = torneoRepository.findById(torneoId)
				.orElseThrow(() -> new RuntimeException("No se ha encontrado el torneo especificado"));
		
		if(!jugador.getTorneos().contains(torneo)) {
			jugador.getTorneos().add(torneo);
			torneo.getJugadores().add(jugador);
			jugadorRepository.save(jugador); // basta guardar uno, JPA gestiona la tabla intermedia
		}

	}

}
