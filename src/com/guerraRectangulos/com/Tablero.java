package com.guerraRectangulos.com;

import java.util.*;

public class Tablero {

	private int ancho;
	private int largo;
	public char[][] matriz;
	
	private int[] cursor;
	private ArrayList<Tupla<Coordenada,Rectangulo>> rectangulosJ1;
	private ArrayList<Tupla<Coordenada,Rectangulo>> rectangulosJ2;
	private Rectangulo ultimoRectangulo;

	public Tablero(int a, int l) {
		rectangulosJ1 = new ArrayList<Tupla<Coordenada,Rectangulo>>();
		rectangulosJ2 = new ArrayList<Tupla<Coordenada,Rectangulo>>();
		cursor = new int[2];
		matriz = new char[a][l];
		this.ancho = a;
		this.largo = l;
		// PONGO TODAS LAS POSICIONES EN "VACIAS"
		for (int i = 0; i < a; i++) {
			for (int j = 0; j < l; j++)
				matriz[i][j] = ' ';
		}
	}

	public int pintar(Rectangulo rec, int turno) {

		
		
		
		if (turno % 2 == 1) {
			if (verificacion1(rec.getAncho(), rec.getLargo())) {
				Coordenada coordenada = new Coordenada(cursor[0], cursor[1]);
				Tupla<Coordenada, Rectangulo> tupla = new Tupla<Coordenada,Rectangulo>(coordenada,rec);
			rectangulosJ1.add(tupla);
			for (int i = coordenada.getx1(); i < coordenada.getx1() + rec.getAncho(); i++) {
				for (int j = coordenada.gety1(); j < coordenada.gety1() + rec.getLargo(); j++) {
					matriz[i][j] = 'X';
					if (coordenada.getx1() == i && coordenada.gety1() == j)
						matriz[i][j] = (char) (turno + 64);

				}
			}
			ultimoRectangulo = rec;
			return rec.getAncho()*rec.getLargo();
			}
		}
		if (turno % 2 == 0) {
			if (verificacion2(rec.getAncho(), rec.getLargo())) {
				Coordenada coordenada = new Coordenada(cursor[0], cursor[1]);
				Tupla<Coordenada, Rectangulo> tupla = new Tupla<Coordenada,Rectangulo>(coordenada,rec);
			rectangulosJ2.add(tupla);
			for (int i = coordenada.getx1(); i > coordenada.getx1() - rec.getAncho(); i--) {
				for (int j = coordenada.gety1(); j > coordenada.gety1() - rec.getLargo(); j--) {
					matriz[i][j] = 'O';
					if (coordenada.getx1() - rec.getAncho() == i - 1 && coordenada.gety1() - rec.getLargo() == j - 1)
						matriz[i][j] = (char) (turno + 64);

				}
			}
			ultimoRectangulo = rec;
			return rec.getAncho()*rec.getLargo();
		}
		
		}
		return 0;
	}
	
	//Este metodo retorna el area del cuadrado que se elimino
	public int eliminarRectangulo(int turno) {
		Random r = new Random();
		
		if (turno%2==0 && rectangulosJ1.size()>0) {
			int random = r.nextInt(rectangulosJ1.size());
			//Recorro en el tablero, las posiciones donde estaba ese rectangulo y las borro.
			for(int i = rectangulosJ1.get(random).t1.getx1(); i< rectangulosJ1.get(random).t1.getx1() + rectangulosJ1.get(random).t2.getAncho();i++) {
				for(int j = rectangulosJ1.get(random).t1.gety1(); j< rectangulosJ1.get(random).t1.gety1() + rectangulosJ1.get(random).t2.getLargo();j++) {
					matriz[i][j] = ' ';
				}
			}
			//Guardo el area
			int area = rectangulosJ1.get(random).t2.area();
			//Elimino el rectangulo de la lista
			rectangulosJ1.remove(random);
			return area;
		}
		if (turno%2==1 && rectangulosJ2.size()>0) {
			int random = r.nextInt(rectangulosJ2.size());
			//Recorro en el tablero, las posiciones donde estaba ese rectangulo y las borro.
			for(int i = rectangulosJ2.get(random).t1.getx1(); i> rectangulosJ2.get(random).t1.getx1() - rectangulosJ1.get(random).t2.getAncho();i--) {
				for(int j = rectangulosJ2.get(random).t1.gety1(); j> rectangulosJ2.get(random).t1.gety1() - rectangulosJ1.get(random).t2.getLargo();j--) {
					matriz[i][j] = ' ';
					//Descuento los puntos
				}
			}
			
			//Guardo el area
			int area = rectangulosJ2.get(random).t2.area();
			//Elimino el rectangulo de la lista
			rectangulosJ2.remove(random);
			return area;
		}
		return 0;
	}
	
	private boolean verificacion1(int a, int l) {
		//Este algoritmo para buscar un lugar libre para colocar el rectangulo es un poco rebuscado, espero lo puedan entender

		//Con un acumulador booleano recorro posiciones en el tablero para ver si hay posiciones libres
		boolean acu1 = true;
		//Me paro en las posiciones del tablero hasta que encuentre una libre
		for (int i = 0; i < ancho; i++) {
			for (int j = 0; j < largo; j++) {
				if (matriz[i][j] == ' ') {
					//Esto es un pre checkeo para ver si el rectangulo quedaria dentro de las dimensiones del tablero
					if (j + l < largo && i + a < ancho) {
						//Recorro desde la posicion vacia que encontre en el tablero hasta la misma mas las dimensiones del rectangulo
						for (int x = i; x < i + a; x++) {
							for (int y = j; y < j + l; y++) {
								//Acumulo trues hasta que encuentro un valor ocupado
									acu1 = acu1 && matriz[x][y] == ' ';
									//Si encontre un valor ocupado, corto los ciclos para reducir ciclos de operacion
								if (!acu1)
									break;
							}
							if (!acu1)
								break;
						}
						//Si al final, el acumulador es verdadero, guardo las coordenadas donde puedo colocar el rectangulo
						if (acu1) {
							cursor[0] = i;
							cursor[1] = j;
							return true;
						}
						// si dio false, reseteo el acumulador y vuelvo a probar con otra posicion
						acu1 = true;
					}
				}
			}
		}
		return false;
	}

	private boolean verificacion2(int a, int l) {
		//IDEM VERIFICACION 1;
		boolean acu1 = true;
		for (int i = ancho - 1; i > 0; i--) {
			for (int j = largo - 1; j > 0; j--) {
				if (matriz[i][j] == ' ') {
					if (j - l >= 0 && i - a > 0 ) {
						for (int x = i; x > i - a; x--) {
							for (int y = j; y > j - l; y--) {
									acu1 = acu1 && matriz[x][y] == ' ';
								if (!acu1)
									break;
							}
							if (!acu1)
								break;
						}

						if (acu1) {
							cursor[0] = i;
							cursor[1] = j;
							return true;
						}
						acu1 = true;

					}
				}
			}
		}
		return false;
	}

	public ArrayList<Tupla<Coordenada,Rectangulo>> rectangulosJ1() {
		return rectangulosJ1;
	}
	public ArrayList<Tupla<Coordenada,Rectangulo>> rectangulosJ2() {
		return rectangulosJ2;
	}
	public int getAncho() {
		return ancho;
	}

	public int getLargo() {
		return largo;
	}

	public void setValor(int x, int y, char caracter) {
		matriz[x][y] = caracter;
	}

	public char getValor(int x, int y) {
		return matriz[x][y];
	}
	public Rectangulo getUltimoRectangulo() {
		return ultimoRectangulo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(matriz);
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
		Tablero other = (Tablero) obj;
		if (this.ancho != other.ancho || this.largo != other.largo)
			return false;
		for (int f = 0; f < ancho; f++) {
			for (int c = 0; c < largo; c++) {
				if (this.getValor(f, c) != other.getValor(f, c))
					return false;
			}
		}
		return true;
	}
	
}
