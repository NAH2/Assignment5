import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;


public class GameBoardGraphicsTest {

	@Test
	public void test() {
		Game game = new ConnectFour();
		Player player1 = new Human(game);
		Player player2 = new Human(game);
		player1.setPlayerName("Gavin");
		player2.setPlayerName("Lucy");
		player1.setPlayerColour(Color.RED);
		player2.setPlayerColour(Color.YELLOW);
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		game.start();
		
		GameBoardGraphics board = game.getWindow().getDrawing().getGridPanel();
		
		assertEquals(game.getGrid(), board.getGrid());
		
		assertEquals(30, board.getSquareHeight());
		assertEquals(30, board.getSquareWidth());
		assertEquals(game.getGrid().getGridHeight(), board.getYSquares());
		assertEquals(game.getGrid().getGridWidth(), board.getXSquares());
	}

}
