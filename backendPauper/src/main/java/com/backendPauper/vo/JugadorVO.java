package com.backendPauper.vo;

public class JugadorVO {

	private Long id;
	private String nombre;
	private int victorias;
	private int empates;
	private int derrotas;
	private int totalPartidas;
	private int totalPuntos;
	private double winRate;
	private double loseRate;
	private double drawRate;

	public JugadorVO(Long id, String nombre, int victorias, int empates, int derrotas) {
		this.id = id;
		this.nombre = nombre;
		this.victorias = victorias;
		this.empates = empates;
		this.derrotas = derrotas;
		this.totalPartidas = victorias + empates + derrotas;
		this.totalPuntos = (victorias * 3) + empates;

		if (totalPartidas > 0) {
			this.winRate = (double) victorias / totalPartidas;
			this.loseRate = (double) derrotas / totalPartidas;
			this.drawRate = (double) empates / totalPartidas;
		} else {
			this.winRate = 0;
			this.loseRate = 0;
			this.drawRate = 0;
		}
	}

	public JugadorVO() {
		
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

	public int getTotalPartidas() {
		return totalPartidas;
	}

	public void setTotalPartidas(int totalPartidas) {
		this.totalPartidas = totalPartidas;
	}

	public int getTotalPuntos() {
		return totalPuntos;
	}

	public void setTotalPuntos(int totalPuntos) {
		this.totalPuntos = totalPuntos;
	}

	public double getWinRate() {
		return winRate;
	}

	public void setWinRate(double winRate) {
		this.winRate = winRate;
	}

	public double getLoseRate() {
		return loseRate;
	}

	public void setLoseRate(double loseRate) {
		this.loseRate = loseRate;
	}

	public double getDrawRate() {
		return drawRate;
	}

	public void setDrawRate(double drawRate) {
		this.drawRate = drawRate;
	}
	

}
