import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.event.MouseListener;

import org.junit.Test;


public class GameBoardControlsTest {

	@Test
	public void test1() {
		Game connect4 = new ConnectFour();
		Player player1 = new Human(connect4);
		Player player2 = new Human(connect4);
		player1.setPlayerName("Gavin");
		player2.setPlayerName("Lucy");
		player1.setPlayerColour(Color.RED);
		player2.setPlayerColour(Color.YELLOW);
		connect4.setPlayer1(player1);
		connect4.setPlayer2(player2);
		connect4.start();
		GameBoardControls controls = new GameBoardControls(30, 30, connect4.getWindow().getControls());
		assertEquals(30, controls.getCellHeight());
		assertEquals(30, controls.getCellWidth());
	}
	@Test
	public void test2() {
		Game connect4 = new ConnectFour();
		Player player1 = new Human(connect4);
		Player player2 = new Human(connect4);
		player1.setPlayerName("Gavin");
		player2.setPlayerName("Lucy");
		player1.setPlayerColour(Color.RED);
		player2.setPlayerColour(Color.YELLOW);
		connect4.setPlayer1(player1);
		connect4.setPlayer2(player2);
		connect4.start();
		GameBoardControls controls = new GameBoardControls(30, 30, connect4.getWindow().getControls());
		controls.setCellHeight(40);
		controls.setCellWidth(40);
		assertEquals(40, controls.getCellHeight());
		assertEquals(40, controls.getCellWidth());
	}
	@Test
	public void test3() {
		Game connect4 = new ConnectFour();
		Player player1 = new Human(connect4);
		Player player2 = new Human(connect4);
		player1.setPlayerName("Gavin");
		player2.setPlayerName("Lucy");
		player1.setPlayerColour(Color.RED);
		player2.setPlayerColour(Color.YELLOW);
		connect4.setPlayer1(player1);
		connect4.setPlayer2(player2);
		connect4.start();
		GameBoardControls controls = new GameBoardControls(30, 30, connect4.getWindow().getControls());
		controls.setControls(connect4.getWindow().getControls());
		assertEquals(connect4.getWindow().getControls(), controls.getControls());
	}
}
