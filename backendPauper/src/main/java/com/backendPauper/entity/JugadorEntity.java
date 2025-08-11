package com.backendPauper.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="jugador")
public class JugadorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre", length = 60, nullable = false, unique = true)
	private String nombre;
	
	@Column(name = "victorias")
	private int victorias;
	
	@Column(name = "empates")
	private int empates;
	
	@Column(name = "derrotas")
	private int derrotas;
	
	@ManyToMany
	@JoinTable(
			name="participaciones_torneo",
			joinColumns = @JoinColumn(name="jugador_id"),
			inverseJoinColumns = @JoinColumn(name="torneo_id")
	)
	private List<TorneoEntity> torneos = new ArrayList<>();
	
	@OneToMany(mappedBy="jugador")
	private List<ParticipacionEntity> participaciones = new ArrayList<>();

	public JugadorEntity() {
		
	}
	
	public JugadorEntity(String nombre, int victorias, int empates, int derrotas) {
		
		this.nombre = nombre;
		this.victorias = victorias;
		this.empates = empates;
		this.derrotas = derrotas;
	}



	public JugadorEntity(Long id, String nombre, int victorias, int empates, int derrotas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.victorias = victorias;
		this.empates = empates;
		this.derrotas = derrotas;
	}



	public JugadorEntity(String nombre) {
		this.nombre = nombre;
		this.victorias = 0;
		this.empates = 0;
		this.derrotas = 0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getVictorias() {
		return victorias;
	}

	public void setVictorias(int victorias) {
		this.victorias = victorias;
	}

	public int getEmpates() {
		return empates;
	}

	public void setEmpates(int empates) {
		this.empates = empates;
	}

	public int getDerrotas() {
		return derrotas;
	}

	public void setDerrotas(int derrotas) {
		this.derrotas = derrotas;
	}


	public List<TorneoEntity> getTorneos() {
		return torneos;
	}


	public void setTorneos(List<TorneoEntity> torneo) {
		this.torneos = torneo;
	}

	public List<ParticipacionEntity> getParticipaciones() {
		return participaciones;
	}

	public void setParticipaciones(List<ParticipacionEntity> participaciones) {
		this.participaciones = participaciones;
	}
	
	
	
}
