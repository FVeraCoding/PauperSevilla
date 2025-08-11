package com.backendPauper.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // usa esta, de Spring

import com.backendPauper.converter.ParticipacionConverter;
import com.backendPauper.entity.JugadorEntity;
import com.backendPauper.entity.ParticipacionEntity;
import com.backendPauper.entity.TorneoEntity;
import com.backendPauper.repository.JugadorRepository;
import com.backendPauper.repository.ParticipacionRepository;
import com.backendPauper.repository.TorneoRepository;
import com.backendPauper.service.ParticipacionService;
import com.backendPauper.vo.ParticipacionVO;

@Service
public class ParticipacionServiceImpl implements ParticipacionService {

    ParticipacionRepository participacionRepository;
    ParticipacionConverter participacionConverter;
    JugadorRepository jugadorRepository;
    TorneoRepository torneoRepository;

    public ParticipacionServiceImpl(ParticipacionRepository participacionRepository,
            ParticipacionConverter participacionConverter, JugadorRepository jugadorRepository,
            TorneoRepository torneoRepository) {
        this.participacionRepository = participacionRepository;
        this.participacionConverter = participacionConverter;
        this.jugadorRepository = jugadorRepository;
        this.torneoRepository = torneoRepository;
    }

    @Override
    public List<ParticipacionVO> findByJugadorNombre(String nombre) {
        return participacionRepository.findByJugadorNombre(nombre).stream()
                .map(participacionConverter::toVO).toList();
    }

    @Override
    public List<ParticipacionVO> findAllParticipaciones() {
        return participacionRepository.findAll().stream().map(participacionConverter::toVO).toList();
    }

    @Override
    @Transactional
    public ParticipacionVO createParticipacion(ParticipacionVO participacion) {

        JugadorEntity jugadorEntity = jugadorRepository.findById(participacion.getJugador_id())
                .orElseThrow(() -> new RuntimeException("No se ha encontrado al jugador especificado"));
        TorneoEntity torneoEntity = torneoRepository.findById(participacion.getTorneo_id())
                .orElseThrow(() -> new RuntimeException("No se ha encontrado el torneo especificado"));

        ParticipacionEntity participacionEntity =
                participacionConverter.toEntity(jugadorEntity, torneoEntity, participacion);

        ParticipacionEntity saved = participacionRepository.save(participacionEntity);

        // Actualizacion de estadisticas del jugador (suma lo de esta participación)
        jugadorEntity.setVictorias(jugadorEntity.getVictorias() + saved.getVictorias());
        jugadorEntity.setEmpates(jugadorEntity.getEmpates() + saved.getEmpates());
        jugadorEntity.setDerrotas(jugadorEntity.getDerrotas() + saved.getDerrotas());
        jugadorRepository.save(jugadorEntity);

        return participacionConverter.toVO(saved);
    }

    @Override
    @Transactional
    public ParticipacionVO updateParticipacion(ParticipacionVO participacion) {

        ParticipacionEntity existente = participacionRepository.findById(participacion.getId())
                .orElseThrow(() -> new RuntimeException("No se ha encontrado la participación especificada"));

        // Guarda referencias previas
        JugadorEntity jugadorAnterior = existente.getJugador();
        int vOld = existente.getVictorias();
        int eOld = existente.getEmpates();
        int dOld = existente.getDerrotas();

        // Actualiza campos simples
        existente.setClasificacion(participacion.getClasificacion());
        existente.setDeck_utilizado(participacion.getDeck_utilizado());
        existente.setVictorias(participacion.getVictorias());
        existente.setEmpates(participacion.getEmpates());
        existente.setDerrotas(participacion.getDerrotas());

        
        JugadorEntity jugadorNuevo = jugadorRepository.findById(participacion.getJugador_id())
                .orElseThrow(() -> new RuntimeException("No se ha encontrado al jugador especificado"));
        TorneoEntity torneo = torneoRepository.findById(participacion.getTorneo_id())
                .orElseThrow(() -> new RuntimeException("No se ha encontrado el torneo especificado"));

        existente.setJugador(jugadorNuevo);
        existente.setTorneo(torneo);

        ParticipacionEntity saved = participacionRepository.save(existente);

        
        int vNew = saved.getVictorias();
        int eNew = saved.getEmpates();
        int dNew = saved.getDerrotas();

        if (!jugadorAnterior.getId().equals(jugadorNuevo.getId())) {
            // Si cambiaste el jugador de la participación:
            // Resta al anterior lo viejo
            jugadorAnterior.setVictorias(jugadorAnterior.getVictorias() - vOld);
            jugadorAnterior.setEmpates(jugadorAnterior.getEmpates() - eOld);
            jugadorAnterior.setDerrotas(jugadorAnterior.getDerrotas() - dOld);
            jugadorRepository.save(jugadorAnterior);

            // Suma al nuevo lo nuevo
            jugadorNuevo.setVictorias(jugadorNuevo.getVictorias() + vNew);
            jugadorNuevo.setEmpates(jugadorNuevo.getEmpates() + eNew);
            jugadorNuevo.setDerrotas(jugadorNuevo.getDerrotas() + dNew);
            jugadorRepository.save(jugadorNuevo);
        } else {
            // Mismo jugador: aplica deltas
            int dv = vNew - vOld;
            int de = eNew - eOld;
            int dd = dNew - dOld;

            if (dv != 0 || de != 0 || dd != 0) {
                jugadorNuevo.setVictorias(jugadorNuevo.getVictorias() + dv);
                jugadorNuevo.setEmpates(jugadorNuevo.getEmpates() + de);
                jugadorNuevo.setDerrotas(jugadorNuevo.getDerrotas() + dd);
                jugadorRepository.save(jugadorNuevo);
            }
        }

        return participacionConverter.toVO(saved);
    }

    @Override
    @Transactional
    public void deleteParticipacionById(Long id) {
        ParticipacionEntity existente = participacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado la participación especificada"));

        JugadorEntity jugador = existente.getJugador();

        // Resta las stats aportadas por esta participación
        jugador.setVictorias(Math.max(0, jugador.getVictorias() - existente.getVictorias()));
        jugador.setEmpates(Math.max(0, jugador.getEmpates() - existente.getEmpates()));
        jugador.setDerrotas(Math.max(0, jugador.getDerrotas() - existente.getDerrotas()));
        jugadorRepository.save(jugador);

        participacionRepository.deleteById(id);
    }
}
