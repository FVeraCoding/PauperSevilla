import { Component, OnInit } from '@angular/core';
import { JugadorService } from '../../core/services/jugador.service';
import { Jugador } from '../../core/models/jugador.model';

@Component({
  selector: 'app-jugador',
  imports: [],
  templateUrl: './jugador.component.html',
  styleUrl: './jugador.component.css'
})
export class JugadorComponent implements OnInit {

  allJugadores: Jugador[] = [];

  constructor(
    private jugadorService: JugadorService
  ){}

  ngOnInit():void{
    this.jugadorService.getAllJugadores().subscribe( (jugadores: Jugador[]) => {
      this.allJugadores = jugadores;
      console.log(this.allJugadores);
    }  )
  }

}
