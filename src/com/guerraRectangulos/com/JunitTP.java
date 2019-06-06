package com.guerraRectangulos.com;

import static org.junit.Assert.*;

import org.junit.*;

public class JunitTP {

	private GR gr1, gr2, gr3;

	@Before
	public void setUp() {
		gr1 = new GR(11, 10);
		gr2 = new GR(11, 11);
		gr3 = new GR(11, 10);
	}

	@Test
	public void testEquals1() {
		// gr1 tiene que ser igual que gr3 y distinto que gr2}

		assertTrue(gr1.equals(gr3));
		assertTrue(!gr1.equals(gr2));

	}

	@Test
	public void testEquals2() {
		gr1.jugar(2, 3);
		gr3.jugar(2, 3);
		gr2.jugar(3, 2);
		// gr1 tiene que ser igual que gr3 y distinto que gr2
		assertTrue(gr1.equals(gr3));
		assertTrue(!gr1.equals(gr2));
	}

	
	  @Test public void testArea() {
		  gr1.jugar();
		  Rectangulo h1 = gr1.ultimoRectangulo();
		  gr1.jugar();
		  Rectangulo h2 = gr1.ultimoRectangulo();
		  assertEquals(gr1.area(1)+gr1.area(2),h1.area()+h2.area());
	  }
	  
	  @Test 
	  public void testEliminar() {
		  int areaini = gr1.area(1) + gr1.area(2);
		  assertEquals(0,areaini); //�rea inicial 0
		  gr1.jugar(); //turno Jug.1 agrega un rect�ngulo
		  int area1 = gr1.area(1);
		  int area = gr1.area(1) + gr1.area(2);
		  
		  gr1.jugar(); //turno Jug.2 agrega un rect�ngulo
		  
		  gr1.eliminarRect(); //turno Jug.1 � elimina el �nico rect�ngulo del Jug.2
		  int area2 = gr1.area(2);
		  
		  assertTrue(area2<=area1);
		// queda el area total anterior al turno del jugador 2
		// se elimina uno al azar pero hay uno solo para eliminar.
		  assertEquals(gr1.area(1) + gr1.area(2), area);

	  }
	  
	  
	 
}
