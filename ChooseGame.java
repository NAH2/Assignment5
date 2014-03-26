import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
/**
 * @file ChooseGame.java
 * @author Daniel 709547
 * @date 20/02/2014
 * @brief Class which handles the choice of starting a new game
 * 
 * This class deals with handling giving the user
 * the option of starting either a new othello or
 * connect 4 game at the start of the application
 * and if they choose to start a new game.
 */

public class ChooseGame {	
	/**
	 * Setter method to set the JButton m_connect4
	 * @param newConnect4
	 */
	public void setConnect4Button(JButton newConnect4) {
		m_connect4 = newConnect4;
	}
	
	/**
	 * Setter method to set the JButton m_othello
	 * @param newOthello
	 */
	public void setOthelloButton(JButton newOthello) {
		m_othello = newOthello;
	}
	
	/**
	 * Setter method to set the data for the JFrame m_choose
	 * @param newChoose
	 */
	public void setChooseFrame(JFrame newChoose) {
		m_choose = newChoose;
	}
	
	/**
	 * Getter method to return the pointer for the JButton object m_connect4
	 * @return JButton m_connect4
	 */
	public JButton getConnect4Button() {
		return m_connect4;
	}
	
	/**
	 * Getter method to return the JButton m_othello
	 * @return JButton m_othello
	 */
	public JButton getOthelloButton() {
		return m_othello;
	}
	
	/**
	 * Getter method to return the object, the JFrame m_choose.
	 * @return JFrame m_choose
	 */
	public JFrame getChooseFrame() {
		return m_choose;
	}
	
	/**
	* Constructor for the class ChooseGame 
	*/
	public ChooseGame() {		
		JFrame newChoose = new JFrame();
		setChooseFrame(newChoose);
		getChooseFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newChoose.setTitle("Select Game");
		
		Container panel = getChooseFrame().getContentPane();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));	
		
		ImageIcon connect4Image = null;
		ImageIcon othelloImage = null;
		try {
			connect4Image = new ImageIcon(getClass().getResource("/resource/connect4.jpg"));
			othelloImage = new ImageIcon(getClass().getResource("/resource/othello.jpg"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		ImageIcon connect4ImageIcon = connect4Image;
		ImageIcon othelloImageIcon = othelloImage;
		
		JButton connect4 = new JButton("Connect 4");
		JButton othello = new JButton("Othello");
		
		setConnect4Button(connect4);
		setOthelloButton(othello);
		
		ChooseEventHandler handler = new ChooseEventHandler();
		
		getConnect4Button().addActionListener(handler);
		getOthelloButton().addActionListener(handler);
		
		panel.add(getConnect4Button());
		panel.add(getOthelloButton());
		
		getConnect4Button().setIcon(connect4ImageIcon);
		getOthelloButton().setIcon(othelloImageIcon);
		
		getConnect4Button().setHorizontalTextPosition(SwingConstants.LEADING);
		getConnect4Button().setVerticalAlignment(SwingConstants.BOTTOM);
		
		getOthelloButton().setHorizontalTextPosition(SwingConstants.LEADING);
		getOthelloButton().setVerticalAlignment(SwingConstants.BOTTOM);

	    getChooseFrame().pack();
		getChooseFrame().setLocationRelativeTo(null);
        getChooseFrame().setVisible(true);
	}
	
	class ChooseEventHandler implements ActionListener {

		/**
		*	Deals with actions performed by GUI objects, namley the buttons.
		*	@param event - object which holds the data of the GUI event.
		*/

		@Override
		public void actionPerformed(ActionEvent event) {
			Game game;
			if (event.getSource() == getOthelloButton()) {
				game = new Othello();
				PlayerSettings playerSettings = new PlayerSettings(game, true);
			} else {
				game = new ConnectFour();
				PlayerSettings playerSettings = new PlayerSettings(game, false);
			}
			
			getChooseFrame().dispose();
		}
	}
	
	public static void main(String[] args) {
		new ChooseGame();
	}
	
	//private variables
	private JButton m_connect4;
	private JButton m_othello;
	private JFrame m_choose;
}
