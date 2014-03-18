import static org.junit.Assert.*;
import javax.swing.*;
import org.junit.Test;


public class ChooseGameTest {

	@Test
	public void test() {
		ChooseGame choose = new ChooseGame();
		
		JButton button = new JButton();
		JFrame frame = new JFrame();
		
		choose.setChooseFrame(frame);
		assertEquals(frame, choose.getChooseFrame());
		
		choose.setConnect4Button(button);
		assertEquals(button, choose.getConnect4Button());
		
		choose.setOthelloButton(button);
		assertEquals(button, choose.getOthelloButton());
	}

}
