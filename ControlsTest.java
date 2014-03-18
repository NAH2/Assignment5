import static org.junit.Assert.*;

import java.awt.Color;

import javax.swing.*;

import org.junit.Test;


public class ControlsTest {

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
		Controls controls = game.getWindow().getControls();
		
		assertEquals(game.getWindow(), controls.getGameWindow());
		
		JButton button = new JButton();
		
		controls.setLoadButton(button);
		assertEquals(button, controls.getLoadButton());
		
		controls.setSaveButton(button);
		assertEquals(button, controls.getSaveButton());
		
		JPanel panel = new JPanel();
		
		controls.setControlsPanel(panel);
		assertEquals(panel, controls.returnPanel());
	}
}
