import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/**
 * @file Controls.java
 * @author Daniel 709547
 * @date 20/02/2014
 * @brief Main controls class which controls all the game/user controls
 *
 * This class is the main controls class which handles all
 * the users controls and is called by any control
 * element in the application
 */

public class Controls {

	
	/**
	* Called to return a JPanel which contains all the GUI control elements
	* @return A JPanel which contains all the controls created in the class.
	*/

	public JPanel returnPanel() {		
		return m_controlsPanel;
	}
	
	/**
	 * Method to get the private save game button variable
	 * @return JButton m_saveGameButton
	 */
	public JButton getSaveButton() {
		return m_saveGameButton;
	}
	
	/**
	 * Method to get the private load game button variable
	 * @return JButton m_loadGameButton
	 */
	public JButton getLoadButton() {
		return m_loadGameButton;
	}
	
	/**
	 * Set m_saveGameButton to JButton parameter
	 * @param button - instance of JButton to set m_saveGambeButton to.
	 */
	public void setSaveButton(JButton button) {
		m_saveGameButton = button;
	}
	
	/**
	 * Set m_loadGameButton to JButton parameter
	 * @param button - instance of JButton to set m_loadGameButton to.
	 */
	public void setLoadButton(JButton button) {
		m_loadGameButton = button;
	}
	
	/**
	 * Set m_controlsPanel to JPanel parameter
	 * @param panel - instance of JPanel to set m_controlsPanel to.
	 */
	public void setControlsPanel(JPanel panel) {
		m_controlsPanel = panel;
	}
	
	/**
	 * Set the m_gameWindow variable
	 * @param gameWindow the gameWindow to instantiate m_gameWindow to
	 */
	public void setGameWindow(GameWindow gameWindow) {
		m_gameWindow = gameWindow;
	}
	
	/**
	 * Get the m_gameWindow variable.
	 * @return the m_gameWindow variable
	 */
	public GameWindow getGameWindow() {
		return m_gameWindow;
	}
	/**
	 * Constructor method for the Controls Class.
	 * @param gameWindow - an instance of GameWindow
	 */
	
	/**
	* Called when an instance of the class is created to initialise the 
	* GUI elements and add the action listeners
	* @param gameWindow - an object used to hold the data of the gameBoard
	*/
	public Controls(GameWindow gameWindow) {

		JButton save = new JButton("Save Game");
		JButton load = new JButton("Load Game");
		
		setSaveButton(save);
		setLoadButton(load);
		
		setGameWindow(gameWindow);
		JPanel controlsPanel = new JPanel();
		setControlsPanel(controlsPanel);
		
		returnPanel().add(getSaveButton());
		returnPanel().add(getLoadButton());
		
		ControlsEventHandler handler = new ControlsEventHandler();
		int width = getGameWindow().getDrawing().gameBoardGraphics
				.getSquareWidth();
		GameBoardControls boardControls = new GameBoardControls(width,width,
				this);
		getGameWindow().getDrawing().getGridPanel().addMouseListener(
				boardControls);
		
		getSaveButton().addActionListener(handler);
		getLoadButton().addActionListener(handler);
	}
	
	/**
	 * Calls the overloaded method from the GameWindow Class and returns true.
	 * @return true when the method is completed
	 * @param move - coordinate object to be sent to the move made 
	 * method in game window
	*/

	public boolean moveMade(Coordinate move) {
		getGameWindow().moveMade(move);
		
		return true;
	}
	
	class ControlsEventHandler implements ActionListener{

		/**
		* Deals with actions performed by GUI objects, namely the buttons.
		* @param event - object which holds the data of the GUI event.
		*/

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == getSaveButton()) {
				//do not need to implement till assignment 5
			} else if (event.getSource() == getLoadButton()) {
				//do not need to implement till assignment 5
			}
			
		}	
	}
	
	//private member variables
	private JButton m_saveGameButton;
	private JButton m_loadGameButton;
	private JPanel m_controlsPanel;
	private GameWindow m_gameWindow;
}