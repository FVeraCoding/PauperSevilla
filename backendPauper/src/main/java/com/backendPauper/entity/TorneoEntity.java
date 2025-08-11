package com.backendPauper.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class TorneoEntity {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "fecha", nullable = false)
	private LocalDate fecha;
	
	@Column(name = "numero_rondas", nullable = false)
	private int numeroRondas;
	
	@Column(name = "numero_participantes", nullable = false)
	private int numeroParticipantes;
	
	@Column(name = "tienda_organizadora", nullable = false)
	private String tiendaOrganizadora;
	
	@ManyToMany(mappedBy="torneos")
	private List<JugadorEntity> jugadores = new ArrayList<>();
	
	@OneToMany(mappedBy="torneo")
	private List<ParticipacionEntity> participaciones = new ArrayList<>();

	public TorneoEntity() {
		
	}
	
	

	public TorneoEntity(LocalDate fecha, int numeroRondas, int numeroParticipantes, String tiendaOrganizadora) {
		
		this.fecha = fecha;
		this.numeroRondas = numeroRondas;
		this.numeroParticipantes = numeroParticipantes;
		this.tiendaOrganizadora = tiendaOrganizadora;
		
	}



	public TorneoEntity(Long id, LocalDate fecha, int numeroRondas, int numeroParticipantes,
			String tiendaOrganizadora) {
		
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

	public List<JugadorEntity> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<JugadorEntity> jugadores) {
		this.jugadores = jugadores;
	}
		
	
	
}
