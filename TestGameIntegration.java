// Authors - Ben, Gavin, Geraint

import static org.junit.Assert.*;
import java.awt.Color;

import org.junit.Test;


public class TestGameIntegration {

	
	
	@Test
	public void testOthello() {
		Game game = new Othello();
		game.setPlayer1(new Human(game, "test1", Color.blue));
		game.setPlayer2(new Human(game, "test2", Color.red));
		game.start();
		assertEquals(false, game.isOver());
	}

	@Test
	public void testConnectFour() {
		Game game = new ConnectFour();
		game.setPlayer1(new Human(game, "test1", Color.blue));
		game.setPlayer2(new Human(game, "test2", Color.red));
		game.start();
		assertEquals(false, game.isOver());
	}
	
	@Test
	public void testTakeMove() {
		Game game = new ConnectFour();
		game.setPlayer1(new Human(game, "test1", Color.blue));
		game.setPlayer2(new Human(game, "test2", Color.red));
		game.start();
		game.moveMade(new Coordinate(0,0,Game.PlayerTurn.PLAYER1));
		game.moveMade(new Coordinate(1,0,Game.PlayerTurn.PLAYER1));
		game.moveMade(new Coordinate(2,0,Game.PlayerTurn.PLAYER1));
		game.moveMade(new Coordinate(3,0,Game.PlayerTurn.PLAYER1));
		assertEquals(true,game.isOver());
	}
}
