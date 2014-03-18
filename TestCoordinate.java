// Coordinate test case
// B. Golightly

import static org.junit.Assert.*;
import org.junit.Test;

public class TestCoordinate {

	@Test
	public void testConstructor() {
		Coordinate c = new Coordinate(123, 456, Game.PlayerTurn.PLAYER2);
		
		assertEquals(123, c.getX());
		assertEquals(456, c.getY());
		assertEquals(Game.PlayerTurn.PLAYER2, c.getValue());
	}
	
	@Test
	public void testMove() {
		Coordinate c = new Coordinate(1, 2);
		
		c.move(3, 7);
		
		assertEquals(4, c.getX());
		assertEquals(9, c.getY());
	}

	@Test
	public void tesWithin() {
		Coordinate c = new Coordinate(5, 5);
		
		assertEquals(true, c.within(0, 0, 10, 10));
		assertEquals(false, c.within(0, 0, 5, 5));
	}

}
