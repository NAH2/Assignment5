import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * @file GameWindow.java
 * @author Gavin Tsang 658679
 * @date 27/02/2014
 * 
 * @brief This class is used to initialise the main GUI window.
 * 
 * The main GUI window contains all the graphical elements and user interfaces.
 * Also provides methods to manipulate the elements within the window such as
 * displaying error messages or updating the grid and labels.
 */
public class GameWindow extends JFrame {
	
	/**
	 * Returns the main game class which this window is taking commands from.
	 * 
	 * @return Returns the pointer towards the game class that is currently
	 * being used.
	 */
	public Game getGame() {
		return m_gameControl;
	}
	
	/**
	 * Sets the game class with which this window is to take and send commands
	 * from.
	 * 
	 * @param game The game class which should currently be used.
	 * @return Returns TRUE if successful.
	 */
	private boolean setGame(Game game) {
		m_gameControl = game;
		return true;
	}
	
	/**
	 * Returns the drawing class which controls all the graphical systems in the
	 * window.
	 * 
	 * @return Returns a pointer towards the drawing class.
	 */
	public Drawing getDrawing() {
		return m_drawingControl;
	}
	
	/**
	 * Sets the drawing class which controls all the graphical systems in the
	 * window.
	 * 
	 * @param drawing The instance of the drawing class which is to be used.
	 * 
	 * @return Returns TRUE if successful.
	 */
	private boolean setDrawing(Drawing drawing) {
		m_drawingControl = drawing;
		return true;
	}
	
	/**
	 * Returns the control class which processes the users inputs commands.
	 * 
	 * @return Returns a pointer towards the control class being used.
	 */
	public Controls getControls() {
		return m_controlsControl;
	}
	
	/**
	 * Sets the control class which is used to take in and process the user's
	 * comands on the main GameWindow.
	 * 
	 * @param controls The instance of the controls class which is to be used.
	 * 
	 * @return Returns TRUE if successful.
	 */
	private boolean setControls(Controls controls) {
		m_controlsControl = controls;
		return true;
	}
	
	/**
	 * Constructor creates the window, initialises subControl classes Controls
	 * and Drawing and draws on the component panels provided by the
	 * aforementioned classes.
	 * 
	 * @param game Pointer to indicate which game process is currently using
	 * this Gamewindow.
	 */
	public GameWindow(Game game) {
		boolean m_Trace = false;
		
		if(m_Trace) System.out.println("GameWindow::GameWindow() - window initializing");
		if(m_Trace) System.out.println("GameWindow::GameWindow() - Linked game is " + game.getClass());
		GridBagConstraints c = new GridBagConstraints();
		GridBagLayout layout = new GridBagLayout();
		
		setGame(game);
		setDrawing(new Drawing(m_gameControl));
		setControls(new Controls(this));
		
		setLayout(layout);
		
		JComponent grid = getDrawing().getGridPanel();
		
		c.gridx = 0;
		c.gridy = 0;
		layout.setConstraints(grid, c);
		add(grid);
		
		c.gridx = 1;
		layout.setConstraints(getDrawing().getSideBarPanel(), c);
		add(getDrawing().getSideBarPanel());

		setTitle("Boardgame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
		setVisible(true);
		pack();
		if(m_Trace) { System.out.println("GameWindow::GameWindow() - window initialized");}
		
	}
	
	/**
	 * Called whenever the player's turn changes.
	 * 
	 * @param player indicates which player's turn it is as the Enumerator
	 * "PlayerTurn".
	 * 
	 * @return Returns TRUE if successful.
	 */
	public boolean displayPlayerTurn(Game.PlayerTurn player) {
		getDrawing().setPlayerTurn(player);
		return true;
	}

	/**
	 * Called whenever the displayed grid needs to be updated.
	 * 
	 * @param grid The grid class which is to be used to update the GUI.
	 * 
	 * @return Returns TRUE if successful.
	 */
	public boolean displayGrid(Grid grid) {
		getDrawing().setGrid(grid);
		return true;
	}
	
	/**
	 * Called to display an invalid move error message on screen.
	 * 
	 * @return Returns TRUE if successful.
	 */
	public boolean displayInvalidMove() {
		getDrawing().invalidMove();
		return true;
	}
	
	/**
	 * Called to update the player's scores displayed on screen.
	 * 
	 * @param player1Score The new score to be displayed for player 1.
	 * 
	 * @param player2Score The new score to be displayed for player 2.
	 * 
	 * @return Returns TRUE if successful.
	 */
	public boolean updateScore(int player1Score, int player2Score) {
		getDrawing().setPlayer1Score(player1Score);
		getDrawing().setPlayer2Score(player2Score);
		return true;
	}
	
	/**
	 * Called whenever the user has made a move on the grid UI and passes
	 * it onto the correct Player class who's turn it currently is.
	 * 
	 * @param move The move that the user has made as a Coordinate.
	 * 
	 * @return Returns TRUE if successful.
	 */
	public boolean moveMade(Coordinate move) {
		if(getGame().getPlayerTurn() == Game.PlayerTurn.PLAYER1) {
			move.setValue(Game.PlayerTurn.PLAYER1);
			getGame().getPlayer1().sendMove(move);
		} else {
			move.setValue(Game.PlayerTurn.PLAYER2);
			getGame().getPlayer2().sendMove(move);
		}
		return true;
	}
	
	/**
	 * Test method.
	 * 
	 * @param No arguments.
	 */
	public static void main(String args[]) {
		ConnectFour game = new ConnectFour();
		Player player1 = new Human(game);
		Player player2 = new Human(game);
		player1.setPlayerName("Gavin");
		player2.setPlayerName("Lucy");
		player1.setPlayerColour(Color.RED);
		player2.setPlayerColour(Color.YELLOW);
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		new GameWindow(game);
	}
	
	private Game m_gameControl;
	private Drawing m_drawingControl;
	private Controls m_controlsControl;
}
