package com.backendPauper.vo;

import java.time.LocalDate;

public class TorneoVO {

	private Long id;
	private LocalDate fecha;
	private int numeroRondas;
	private int numeroParticipantes;
	private String tiendaOrganizadora;
	
	public TorneoVO() {
		
	}

	public TorneoVO(Long id, LocalDate fecha, int numeroRondas, int numeroParticipantes, String tiendaOrganizadora) {
		
		this.id = id;
		this.fecha = fecha;
		this.numeroRondas = numeroRondas;
		this.numeroParticipantes = numeroParticipantes;
		this.tiendaOrganizadora = tiendaOrganizadora;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getNumeroRondas() {
		return numeroRondas;
	}

	public void setNumeroRondas(int numeroRondas) {
		this.numeroRondas = numeroRondas;
	}

	public int getNumeroParticipantes() {
		return numeroParticipantes;
	}

	public void setNumeroParticipantes(int numeroParticipantes) {
		this.numeroParticipantes = numeroParticipantes;
	}

	public String getTiendaOrganizadora() {
		return tiendaOrganizadora;
	}

	public void setTiendaOrganizadora(String tiendaOrganizadora) {
		this.tiendaOrganizadora = tiendaOrganizadora;
	}
	
	
	
}
