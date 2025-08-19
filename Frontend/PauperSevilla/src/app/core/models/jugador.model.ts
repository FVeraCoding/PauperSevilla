export interface Jugador{
    id?: number|null,
    nombre:string,
    victorias:number,
    derrotas:number,
    empates:number,
    totalPartidas:number,
    totalPuntos:number,
    winRate:number,
    loseRate:number,
    drawRate:number
}