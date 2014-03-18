// Grid testing
// Jiaming Dong

import static org.junit.Assert.*;

import org.junit.Test;


public class GridTest {

	@Test
	public void testConstructor() {
		Grid g = new Grid(111, 5);
		
		assertEquals(111,g.getGridWidth());	
		assertEquals(5,g.getGridHeight());
	}
	
	@Test
	public void testCoordinate(){
		Grid g = new Grid(111,222);
		
		g.setCoordinate(new Coordinate(1, 2, Game.PlayerTurn.PLAYER1));
		assertEquals(g.getCoordinate(1, 2).getValue(),Game.PlayerTurn.PLAYER1);
	}
}