package com.guerraRectangulos.com;


public class Principal {

	public static void main(String[] args) {
		
		
		
		//     EJEMPLO AUTOMATICO  //
		
		GR gr = new GR(15,15);
		String ganador = "";
		while (ganador == "") {
		ganador = gr.jugar();
		System.out.println(gr.turno+"..." + gr.ultimoRectangulo().ancho() +","
		+ gr.ultimoRectangulo().alto());
		}
		System.out.println(gr);
		
		
		
		//    EJEMPLO SEMI-AUTOMATICO  //
		/*
		GR gr2 = new GR(15,15);
		String ganador;
		ganador = gr2.jugar();
		ganador = gr2.jugar();
		ganador = gr2.jugar();
		gr2.eliminarRect();
		System.out.println(gr2);
		*/
		
	}
}
