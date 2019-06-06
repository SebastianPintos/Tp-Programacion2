package com.guerraRectangulos.com;

import java.util.*;

public class GR {

	public int turno;
	private String ganador;
	private Tablero tablero;
	private int puntosJ1;
	private int puntosJ2;
	private int turnosPerdidos;

	public GR(int a, int l) {
		tablero = new Tablero(a, l);
		turno = 0;
		turnosPerdidos = 0;
		ganador = "";
		puntosJ1 = 0;
		puntosJ2 = 0;
	}

	public String jugar() {

		if (turnosPerdidos < 2) { // Si todavia el juego no termino

			turno++;
			// se crea un rectangulo de medidas aleatorias(entre 2 y un tercio dele tablero)
			Random a = new Random();
			Random l = new Random();
			int ancho = a.nextInt(tablero.getAncho() / 3) + 2;
			int largo = l.nextInt(tablero.getLargo() / 3) + 2;
			Rectangulo rec = new Rectangulo(ancho, largo);

			// Turnos impares(Jugador 1)
			if (turno % 2 == 1) {
				// Guardo los puntos en una variable auxiliar
				int puntosAux = puntosJ1;
				//Pinto y guardo los puntos
				puntosJ1 += tablero.pintar(rec, turno);
				//Si NO sumo puntos, quiere decir que no se pudo pintar
				if (puntosJ1 == puntosAux)
					turnosPerdidos++;
				else
					turnosPerdidos = 0;

			}
			// Turnos pares(Jugador 2)
			else if (turno % 2 == 0) {
				int puntosAux = puntosJ2;
				puntosJ2 += tablero.pintar(rec, turno);
				if (puntosJ2 == puntosAux)
					turnosPerdidos++;
				else
					turnosPerdidos = 0;

			}
		}
		// Si ya se perdieron 2 turnos, pongo la variable en un valor que no afecte al
		// juego
		if (turnosPerdidos == 2) {
			turnosPerdidos = 3;
			if (puntosJ1 > puntosJ2)
				ganador = "Jugador1";
			else if (puntosJ1 < puntosJ2)
				ganador = "Jugador2";
			else
				ganador = "Empate";
		}
		return ganador;
	}

	public void eliminarRect() {
		turno++;
		if (turno % 2 == 1)
			puntosJ2 -= tablero.eliminarRectangulo(turno);
		if (turno % 2 == 0)
			puntosJ1 -= tablero.eliminarRectangulo(turno);
	}

	public Rectangulo ultimoRectangulo() {
		return tablero.getUltimoRectangulo();
	}

	@Override
	public String toString() {
		StringBuilder tab = new StringBuilder();
		for (int i = 0; i < tablero.getAncho(); i++) {
			for (int j = 0; j < tablero.getLargo(); j++) {
				tab.append('[');
				tab.append(tablero.getValor(i, j));
				tab.append(']');
			}
			tab.append("\n");
		}
		tab.append("\nPuntos jugador 1: ");
		tab.append(puntosJ1);
		tab.append("     Puntos jugador 2: ");
		tab.append(puntosJ2);
		tab.append("\nturno: ");
		tab.append(turno);
		if(turnosPerdidos == 3)
		tab.append("\n\nJUEGO TERMINADO.\nGanador: " + ganador + " con " + String.valueOf(Math.max(puntosJ1, puntosJ2))
				+ " puntos");
		return tab.toString();
	}

	// MISMA FUNCION JUGAR PERO "SIN DADOS"
	public String jugar(int ancho, int largo) {

		if (turnosPerdidos < 2) { // Si todavia el juego no termino

			turno++;
			Rectangulo rec = new Rectangulo(ancho, largo);
			if (turno % 2 == 1) {
				int puntosAux = puntosJ1;
				puntosJ1 += tablero.pintar(rec, turno);
				if (puntosJ1 == puntosAux)
					turnosPerdidos++;
				else
					turnosPerdidos = 0;

			} else if (turno % 2 == 0) {
				int puntosAux = puntosJ2;
				puntosJ2 += tablero.pintar(rec, turno);
				if (puntosJ2 == puntosAux)
					turnosPerdidos++;
				else
					turnosPerdidos = 0;

			}
		}

		// Si ya se perdieron 2 turnos, pongo la variable en un valor que no afecte al
		// juego
		if (turnosPerdidos == 2) {
			turnosPerdidos = 3;
			if (puntosJ1 > puntosJ2)
				ganador = "Jugador1";
			else if (puntosJ1 < puntosJ2)
				ganador = "Jugador2";
			else
				ganador = "Empate";

		}
		return ganador;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tablero.rectangulosJ1() == null) ? 0 : tablero.rectangulosJ1().hashCode());
		result = prime * result + ((tablero.rectangulosJ2() == null) ? 0 : tablero.rectangulosJ2().hashCode());
		result = prime * result + ((tablero == null) ? 0 : tablero.hashCode());
		result = prime * result + turno;
		result = prime * result + turnosPerdidos;
		result = prime * result
				+ ((tablero.getUltimoRectangulo() == null) ? 0 : tablero.getUltimoRectangulo().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GR other = (GR) obj;
		if (tablero == null) {
			if (other.tablero != null)
				return false;
		} else if (!tablero.equals(other.tablero))
			return false;
		if (turno != other.turno)
			return false;
		if (turnosPerdidos != other.turnosPerdidos)
			return false;

		return true;
	}

	public int area(int i) {

		if (i % 2 == 1) {
			if (tablero.rectangulosJ1().size() > i / 2)
				return tablero.rectangulosJ1().get(i / 2).t2.area();
			else
				return 0;
		}
		if (tablero.rectangulosJ2().size() > i / 2 - 1)
			return tablero.rectangulosJ2().get(i / 2 - 1).t2.area();
		else
			return 0;
	}
}
