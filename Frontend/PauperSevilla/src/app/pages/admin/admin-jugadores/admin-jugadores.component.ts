import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Jugador } from '../../../core/models/jugador.model';
import { JugadorService } from '../../../core/services/jugador.service';

@Component({
  selector: 'app-admin-jugadores',
  imports: [CommonModule, FormsModule],
  templateUrl: './admin-jugadores.component.html',
  styleUrl: './admin-jugadores.component.css'
})
export class AdminJugadoresComponent {

  constructor(private jugadorService: JugadorService) { }

  modalCrearJugadorVisible: boolean = false;

  nombre: string = '';

  jugador: Jugador = {
    id: null,
    nombre: '',
    victorias: 0,
    derrotas: 0,
    empates: 0,
    totalPartidas: 0,
    totalPuntos: 0,
    winRate: 0,
    loseRate: 0,
    drawRate: 0
  };

  abrirModalCrearJugador() {
    this.modalCrearJugadorVisible = true;
  }

  cerrarModalCrearJugador() {
    this.modalCrearJugadorVisible = false;
  }

  crearJugador(nombre: string) {

    if (!nombre || !nombre.trim()) {
      alert("El nombre no puede estar vacío.");
      return;
    }

    this.jugador.nombre = nombre;

    this.jugadorService.createJugador(this.jugador).subscribe({
      next: (res) => {
        console.log("Jugador creado con éxito", res);
        this.cerrarModalCrearJugador();
      },
      error: (error) => {
        console.log("Error al crear el jugador", error);
      }
    })
  }

}
