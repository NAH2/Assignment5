import java.awt.Color;
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
	* Called when an instance of the class is created to initialise the 
	* GUI elements and add the action listeners
	* \param gameWindow - an object used to hold the data of the gameBoard
	*/
	public Controls(GameWindow gameWindow) {
		boolean test = false;
        if (test || m_test) {
            System.out.println("Controls :: Controls() BEGIN");
        }
		
		setGameWindow(gameWindow);
		
		int width = getGameWindow().getDrawing().gameBoardGraphics
				                                              .getSquareWidth();
		GameBoardControls boardControls = new GameBoardControls(width,width,
				                                                          this);
		getGameWindow().getDrawing().getGridPanel().addMouseListener(
				                                                 boardControls);

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
	
	/**
	 * Tests for the panels, windows and buttons, testing the methods within 
	 * this class
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
	}
	
	/** game window reference */
	private GameWindow m_gameWindow;
	 /** test variable */
    private boolean m_test = false;
}