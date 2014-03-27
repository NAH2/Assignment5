import static org.junit.Assert.assertEquals;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import org.junit.Test;
/**
 * \\file Controls.java
 * \author Daniel 709547
 * \date 20/02/2014
 * \brief Main controls class which controls all the game/user controls
 *
 * This class is the main controls class which handles all
 * the users controls and is called by any control
 * element in the application
 */

public class Controls {

	
	/**
	* Called to return a JPanel which contains all the GUI control elements
	* \return A JPanel which contains all the controls created in the class.
	*/
	public JPanel returnPanel() {
		boolean test = false;
        if (test || m_test) {
            System.out.println("Controls :: returnPanel() BEGIN");
        }
        if (test || m_test) {
            System.out.println("Controls :: returnPanel() END");
        }
		return m_controlsPanel;
	}
	
	/**
	 * Method to get the private save game button variable
	 * \return JButton m_saveGameButton
	 */
	public JButton getSaveButton() {
		boolean test = false;
        if (test || m_test) {
            System.out.println("Controls :: getSaveButton() BEGIN");
        }
        if (test || m_test) {
            System.out.println("Controls :: getSaveButton() END");
        }
        
		return m_saveGameButton;
	}
	
	/**
	 * Method to get the private load game button variable
	 *\return JButton m_loadGameButton
	 */
	public JButton getLoadButton() {
		boolean test = false;
        if (test || m_test) {
            System.out.println("Controls :: getLoadButton() BEGIN");
        }
        if (test || m_test) {
            System.out.println("Controls :: getLoadButton() END");
        }
		return m_loadGameButton;
	}
	
	/**
	 * Set m_saveGameButton to JButton parameter
	 * \param button - instance of JButton to set m_saveGambeButton to.
	 */
	public void setSaveButton(JButton button) {
		boolean test = false;
        if (test || m_test) {
            System.out.println("Controls :: setSaveButton() BEGIN");
        }
		m_saveGameButton = button;
		
		if (test || m_test) {
            System.out.println("Controls :: setSaveButton() END");
        }
	}
	
	/**
	 * Set m_loadGameButton to JButton parameter
	 * \param button - instance of JButton to set m_loadGameButton to.
	 */
	public void setLoadButton(JButton button) {
		boolean test = false;
        if (test || m_test) {
            System.out.println("Controls :: setLoadButton() BEGIN");
        }
		m_loadGameButton = button;
		
		if (test || m_test) {
            System.out.println("Controls :: setLoadButton() END");
        }
	}
	
	/**
	 * Set m_controlsPanel to JPanel parameter
	 * \param panel - instance of JPanel to set m_controlsPanel to.
	 */
	public void setControlsPanel(JPanel panel) {
		boolean test = false;
        if (test || m_test) {
            System.out.println("Controls :: setControlsPanel() BEGIN");
        }
		m_controlsPanel = panel;
		if (test || m_test) {
            System.out.println("Controls :: setControlsPanel() END");
        }
	}
	
	/**
	 * Set the m_gameWindow variable
	 * \param gameWindow the gameWindow to instantiate m_gameWindow to
	 */
	public void setGameWindow(GameWindow gameWindow) {
		boolean test = false;
        if (test || m_test) {
            System.out.println("Controls :: setGameWindow() BEGIN");
        }
		m_gameWindow = gameWindow;
		
		if (test || m_test) {
            System.out.println("Controls :: setGameWindow() END");
        }
	}
	
	/**
	 * Get the m_gameWindow variable.
	 * \return the m_gameWindow variable
	 */
	public GameWindow getGameWindow() {
		boolean test = false;
        if (test || m_test) {
            System.out.println("Controls :: getGameWindow() BEGIN");
        }
        if (test || m_test) {
            System.out.println("Controls :: getGameWindow() END");
        }
		return m_gameWindow;
	}
	/**
	 * Constructor method for the Controls Class.
	 * \param gameWindow - an instance of GameWindow
	 */
	
	/**
	* Called when an instance of the class is created to initialise the 
	* GUI elements and add the action listeners
	* \param gameWindow - an object used to hold the data of the gameBoard
	*/
	public Controls(GameWindow gameWindow) {
		boolean test = false;
        if (test || m_test) {
            System.out.println("Controls :: Controls() BEGIN");
        }

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
		
		if (test || m_test) {
            System.out.println("Controls :: Controls() END");
        }
	}
	
	/**
	 * Calls the overloaded method from the GameWindow Class and returns true.
	 * \return true when the method is completed
	 * \param move - coordinate object to be sent to the move made 
	 * method in game window
	 * \throws InterruptedException 
	*/

	public boolean moveMade(Coordinate move) throws InterruptedException {
		boolean test = false;
        if (test || m_test) {
            System.out.println("Controls :: moveMade() BEGIN");
        }
		getGameWindow().moveMade(move);
		
		if (test || m_test) {
            System.out.println("Controls :: moveMade() END");
        }
		return true;
	}
	
	class ControlsEventHandler implements ActionListener{

		/**
		* Deals with actions performed by GUI objects, namely the buttons.
		* \param event - object which holds the data of the GUI event.
		*/

		@Override
		public void actionPerformed(ActionEvent event) {
			boolean test = false;
	        if (test || m_test) {
	            System.out.println("ControlsEventHandler :: actionPerformed() BEGIN");
	        }
				if (event.getSource() == getSaveButton()) {
				//do not need to implement till assignment 5
				} else if (event.getSource() == getLoadButton()) {
				//do not need to implement till assignment 5
				}
			if (test || m_test) {
		        System.out.println("ControlsEventHandler :: actionPerformed() END");
		    }
		}	
	}
	/**
	 * Tests for the panels, windows and buttons, testing the methods within this class
	 */
	public static void main(String[] args) {
			Game game = new ConnectFour();
			Player player1 = new Human(game);
			Player player2 = new Human(game);
			player1.setPlayerName("Gavin");
			player2.setPlayerName("Lucy");
			player1.setPlayerColour(Color.RED);
			player2.setPlayerColour(Color.YELLOW);
			game.setPlayer1(player1);
			game.setPlayer2(player2);
			
			try {
				game.start();
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}
			
			Controls controls = game.getWindow().getControls();
			if(game.getWindow()==controls.getGameWindow()){
				System.out.print("Game Windows Equal");
			}
			
			JButton button = new JButton();
			
			controls.setLoadButton(button);
			if(button==controls.getLoadButton()){
				System.out.print("Buttons Equal");
			}
			
			
			controls.setSaveButton(button);
			if(button==controls.getSaveButton()){
				System.out.print("Buttons Equal");
			}
			
			JPanel panel = new JPanel();
			
			controls.setControlsPanel(panel);
			if(panel==controls.returnPanel()){
				System.out.print("Panels Equal");
			}
			
		}
	
	
	
	//private member variables
	private JButton m_saveGameButton;
	private JButton m_loadGameButton;
	private JPanel m_controlsPanel;
	private GameWindow m_gameWindow;
	 /** test variable */
    private boolean m_test = false;
}