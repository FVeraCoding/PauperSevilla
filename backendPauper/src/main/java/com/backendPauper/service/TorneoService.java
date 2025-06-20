package com.backendPauper.service;

import java.util.List;

import com.backendPauper.vo.TorneoVO;

public interface TorneoService {

	List<TorneoVO> findAll();
	TorneoVO findById();
	List<TorneoVO> findByTiendaOrganizadora(String tiendaOrganizadora);
	List<TorneoVO> findByMesAndAnio(int mes, int anio);
	
	TorneoVO createTorneo(TorneoVO torneo);
	TorneoVO updateTorneo(TorneoVO torneo);
	void deleteTorneoById(Long id);
	
}
