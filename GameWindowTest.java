import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;


public class GameWindowTest {
	@Test
	public void test1() {
		ConnectFour game = new ConnectFour();
		Player player1 = new Human(game);
		Player player2 = new Human(game);
		player1.setPlayerName("Gavin");
		player2.setPlayerName("Lucy");
		player1.setPlayerColour(Color.RED);
		player2.setPlayerColour(Color.YELLOW);
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		game.start();
		GameWindow gameWindow = game.getWindow();
		
		assertEquals(true, gameWindow.isVisible());
	}
	@Test
	public void test2() {
		ConnectFour game = new ConnectFour();
		Player player1 = new Human(game);
		Player player2 = new Human(game);
		player1.setPlayerName("Gavin");
		player2.setPlayerName("Lucy");
		player1.setPlayerColour(Color.RED);
		player2.setPlayerColour(Color.YELLOW);
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		game.start();
		GameWindow gameWindow = game.getWindow();
		
		gameWindow.moveMade(new Coordinate(0, 0));
		assertEquals(1, Integer.parseInt(gameWindow.getDrawing().getPlayer1Score().getText()));
	}
	@Test
	public void test3() {
		ConnectFour game = new ConnectFour();
		Player player1 = new Human(game);
		Player player2 = new Human(game);
		player1.setPlayerName("Gavin");
		player2.setPlayerName("Lucy");
		player1.setPlayerColour(Color.RED);
		player2.setPlayerColour(Color.YELLOW);
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		game.start();
		GameWindow gameWindow = game.getWindow();
		
		gameWindow.moveMade(new Coordinate(0, 0));
		gameWindow.moveMade(new Coordinate(0, 0));
		gameWindow.moveMade(new Coordinate(0, 0));
		gameWindow.moveMade(new Coordinate(0, 0));
		gameWindow.moveMade(new Coordinate(0, 0));
		gameWindow.moveMade(new Coordinate(0, 0));
		gameWindow.moveMade(new Coordinate(0, 0));
		gameWindow.moveMade(new Coordinate(0, 0));
		assertEquals(3, Integer.parseInt(gameWindow.getDrawing().getPlayer2Score().getText()));
		assertEquals(4, Integer.parseInt(gameWindow.getDrawing().getPlayer1Score().getText()));
	}
	@Test
	public void test4() {
		ConnectFour game = new ConnectFour();
		Player player1 = new Human(game);
		Player player2 = new Human(game);
		player1.setPlayerName("Gavin");
		player2.setPlayerName("Lucy");
		player1.setPlayerColour(Color.RED);
		player2.setPlayerColour(Color.YELLOW);
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		game.start();
		GameWindow gameWindow = game.getWindow();
		
		gameWindow.moveMade(new Coordinate(0, 0));
		gameWindow.moveMade(new Coordinate(1, 0));
		gameWindow.moveMade(new Coordinate(0, 0));
		gameWindow.moveMade(new Coordinate(1, 0));
		gameWindow.moveMade(new Coordinate(0, 0));
		gameWindow.moveMade(new Coordinate(1, 0));
		gameWindow.moveMade(new Coordinate(0, 0));
		
		assertEquals(Game.PlayerTurn.PLAYER1, game.getWinner());
	}
}
