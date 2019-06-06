package com.guerraRectangulos.com;

public class Rectangulo {

	
	private int ancho;
	private int largo;

	public Rectangulo(int a,int l) {
		this.ancho = a;
		this.largo = l;
	}
	
	public int getAncho() {
		return ancho;
	}
	
	public int getLargo() {
		return largo;
	}
	public String alto() {
		return String.valueOf(ancho);
	}

	public String ancho() {
		return String.valueOf(largo);
	}
	public int area() {
		// TODO Auto-generated method stub
		return ancho*largo;
	}
}
