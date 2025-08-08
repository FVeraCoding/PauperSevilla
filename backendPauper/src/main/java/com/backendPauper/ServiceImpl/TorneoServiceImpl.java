package com.backendPauper.ServiceImpl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.backendPauper.converter.TorneoConverter;
import com.backendPauper.entity.TorneoEntity;
import com.backendPauper.repository.TorneoRepository;
import com.backendPauper.service.TorneoService;
import com.backendPauper.vo.TorneoVO;

@Service
public class TorneoServiceImpl implements TorneoService {

	final private TorneoRepository torneoRepository;
	final private TorneoConverter torneoConverter;

	public TorneoServiceImpl(TorneoRepository torneoRepository, TorneoConverter torneoConverter) {
		this.torneoRepository = torneoRepository;
		this.torneoConverter = torneoConverter;
	}

	@Override
	public List<TorneoVO> findAll() {
		return torneoRepository.findAll().stream().map(torneoConverter::toVO).toList();
	}

	@Override
	public TorneoVO findById(Long id) {
		return torneoConverter.toVO(torneoRepository.findById(id)
				.orElseThrow( () -> new RuntimeException("No se ha encontrado el torneo especificado"))); 
	}

	@Override
	public List<TorneoVO> findByTiendaOrganizadora(String tiendaOrganizadora) {
		return torneoRepository.findByTiendaOrganizadora(tiendaOrganizadora)
				.stream().map(torneoConverter::toVO).toList();
	}

	@Override
	public List<TorneoVO> findByMesAndAnio(int mes, int anio) {
		return torneoRepository.findByMesAndAnio(mes, anio)
				.stream().map(torneoConverter::toVO).toList();
	}

	@Override
	public TorneoVO createTorneo(TorneoVO torneo) {
		return torneoConverter.toVO(torneoRepository.save(torneoConverter.toEntity(torneo)));
	}

	@Override
	public TorneoVO updateTorneo(TorneoVO torneo) {
		TorneoEntity existente = torneoRepository.findById(torneo.getId())
				.orElseThrow( () -> new RuntimeException ("No se ha encontrado el torneo especificado"));
		
		existente.setFecha(torneo.getFecha());
		existente.setNumeroParticipantes(torneo.getNumeroParticipantes());
		existente.setNumeroRondas(torneo.getNumeroRondas());
		existente.setTiendaOrganizadora(torneo.getTiendaOrganizadora());
		
		return torneoConverter.toVO(torneoRepository.save(existente));
		
	}

	@Override
	public void deleteTorneoById(Long id) {
				
		if(torneoRepository.existsById(id)) {
			torneoRepository.deleteById(id);
		}else {
			throw new RuntimeException("No se ha encontrado el torneo con id "+id);
		}
		
}

}
