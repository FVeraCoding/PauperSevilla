package com.backendPauper.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="Participacion", uniqueConstraints = @UniqueConstraint(columnNames = {"jugador_id", "torneo_id"}))
public class ParticipacionEntity {

	public ParticipacionEntity() {

	}
	
	public ParticipacionEntity(JugadorEntity jugador, TorneoEntity torneo, int victorias, int derrotas, int empates,
			int clasificacion, String deck_utilizado) {
		this.jugador = jugador;
		this.torneo = torneo;
		this.victorias = victorias;
		this.derrotas = derrotas;
		this.empates = empates;
		this.clasificacion = clasificacion;
		this.deck_utilizado = deck_utilizado;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "jugador_id", nullable = false)
	private JugadorEntity jugador;
	
	@ManyToOne
	@JoinColumn(name = "torneo_id", nullable = false)
	private TorneoEntity torneo;
	
	
	private int victorias;
	private int derrotas;
	private int empates;
	private int clasificacion;
	private String deck_utilizado;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public JugadorEntity getJugador() {
		return jugador;
	}
	public void setJugador(JugadorEntity jugador) {
		this.jugador = jugador;
	}
	public TorneoEntity getTorneo() {
		return torneo;
	}
	public void setTorneo(TorneoEntity torneo) {
		this.torneo = torneo;
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
