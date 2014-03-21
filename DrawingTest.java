import static org.junit.Assert.*;

import java.awt.Color;

import javax.swing.JLabel;

import org.junit.Test;


public class DrawingTest {

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
		
		Drawing drawing = game.getWindow().getDrawing();
		drawing.setPlayer1Score(10);
		assertEquals(10, Integer.parseInt(drawing.getPlayer1Score().getText()));
		
		drawing.setPlayer2Score(30);
		assertEquals(30, Integer.parseInt(drawing.getPlayer2Score().getText()));
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
		
		Drawing drawing = game.getWindow().getDrawing();
		drawing.setPlayer1Name(new JLabel("Gavin"));
		assertEquals("Gavin", drawing.getPlayer1Name().getText());
		
		drawing.setPlayer2Name(new JLabel("Lucy"));
		assertEquals("Lucy", drawing.getPlayer2Name().getText());
		
		drawing.setPlayer2Name(new JLabel());
		assertEquals("", drawing.getPlayer2Name().getText());
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
		
		Drawing drawing = game.getWindow().getDrawing();
		drawing.setTurnsTaken(new JLabel("" + 5));
		assertEquals("5", drawing.getTurnsTaken().getText());
		
		drawing.setTurnsTaken(new JLabel());
		assertEquals("", drawing.getTurnsTaken().getText());
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
		
		Drawing drawing = game.getWindow().getDrawing();
		Grid grid = new Grid(10, 10);
		drawing.setGrid(grid, game);
		assertEquals(grid, drawing.getGridPanel().getGrid());
	}
	@Test
	public void test5() {
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
		
		Drawing drawing = game.getWindow().getDrawing();
		drawing.setPlayerTurn(Game.PlayerTurn.PLAYER1);
		assertEquals(Color.GRAY, drawing.getBarPlayer1().getBackground());
		assertEquals(Color.WHITE, drawing.getBarPlayer2().getBackground());
		
		drawing.setPlayerTurn(Game.PlayerTurn.PLAYER2);
		assertEquals(Color.WHITE, drawing.getBarPlayer1().getBackground());
		assertEquals(Color.GRAY, drawing.getBarPlayer2().getBackground());
	}
}
