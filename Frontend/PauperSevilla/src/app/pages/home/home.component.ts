import { Component } from '@angular/core';
import { Jugador } from '../../core/models/jugador.model';
import { JugadorService } from '../../core/services/jugador.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-home',
  imports: [CommonModule, FormsModule],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class HomeComponent {

  allJugadores: Jugador[] = [];
  seleccionado: boolean = true;
  busqueda = '';

  constructor(
    private jugadorService: JugadorService
  ) { }

  ngOnInit(): void {
    this.jugadorService.getAllJugadores().subscribe((jugadores: Jugador[]) => {
      this.allJugadores = jugadores;
      this.ordenarPorPuntos();
    })
  }


  get jugadoresFiltrados(): Jugador[]{
    const busqueda = this.busqueda.toLowerCase();
    return this.allJugadores.filter(j => j.nombre.toLowerCase().includes(busqueda));
  }


  seleccionar() {
    if (this.seleccionado == false) {
      this.seleccionado = true;
    }
  }

  deseleccionar() {
    if (this.seleccionado == true) {
      this.seleccionado = false;
    }
  }

  ordenarPorPuntos() {
    this.allJugadores.sort((a, b) => b.totalPuntos - a.totalPuntos);
  }



}
