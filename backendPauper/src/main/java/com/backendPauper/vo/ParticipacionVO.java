package com.backendPauper.vo;

public class ParticipacionVO {

	private Long id;
	private Long jugador_id;
	private Long torneo_id;
	private int victorias;
	private int derrotas;
	private int empates;
	private int clasificacion;
	private String deck_utilizado;

	public ParticipacionVO() {
	
	}

	public ParticipacionVO(Long id, Long jugador_id, Long torneo_id, int victorias, int derrotas, int empates,
			int clasificacion, String deck_utilizado) {
		this.id = id;
		this.jugador_id = jugador_id;
		this.torneo_id = torneo_id;
		this.victorias = victorias;
		this.derrotas = derrotas;
		this.empates = empates;
		this.clasificacion = clasificacion;
		this.deck_utilizado = deck_utilizado;
	}




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getJugador_id() {
		return jugador_id;
	}

	public void setJugador_id(Long jugador_id) {
		this.jugador_id = jugador_id;
	}

	public Long getTorneo_id() {
		return torneo_id;
	}

	public void setTorneo_id(Long torneo_id) {
		this.torneo_id = torneo_id;
	}

	public int getVictorias() {
		return victorias;
	}

	public void setVictorias(int victorias) {
		this.victorias = victorias;
	}

	public int getDerrotas() {
		return derrotas;
	}

	public void setDerrotas(int derrotas) {
		this.derrotas = derrotas;
	}

	public int getEmpates() {
		return empates;
	}

	public void setEmpates(int empates) {
		this.empates = empates;
	}

	public int getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(int clasificacion) {
		this.clasificacion = clasificacion;
	}

	public String getDeck_utilizado() {
		return deck_utilizado;
	}

	public void setDeck_utilizado(String deck_utilizado) {
		this.deck_utilizado = deck_utilizado;
	}
	
	
	
	
	
}
