import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * \\file GameBoardControls.java
 * \author Gavin Tsang 658679
 * \date 20/02/2014
 * 
 * \brief This class processes user interactions on the Grid Board.
 *  
 * This class is used to retrieve the square on the grid GUI in which
 * the player has clicked. The grid clicked is then passed to the moveMade
 * method within the controls Class which is then called.
 */
public class GameBoardControls extends MouseAdapter {
	
	/**
	 * Returns the width of each individual square.
	 * \return Returns an integer detailing the width of a single square.
	 */
	public int getCellWidth() {
		return m_cellWidth;
	}
	
	/**
	 * Sets the width of a single square within the grid.
	 * \param width The width of a single square as an integer.
	 * \return Returns TRUE if successful.
	 */
	public boolean setCellWidth(int width) {
		m_cellWidth = width;
		return true;
	}
	
	/**
	 * Returns the height of each individual square.
	 * \return Returns an integer detailing the height of a single square.
	 */
	public int getCellHeight() {
		return m_cellHeight;
	}
	
	/**
	 * Sets the height of a single square within the grid.
	 * \param height The height of a single square as an integer.
	 * \return Returns TRUE if successful.
	 */
	public boolean setCellHeight(int height) {
		m_cellHeight = height;
		return true;
	}
	
	/**
	 * Returns the instance of the controls class to which this class should
	 * send moves to.
	 * \return The instance of the class which moves should be sent to.
	 */
	public Controls getControls() {
		return m_controls;
	}
	
	/**
	 * Sets the instance of the class to which this class should send moves to.
	 * \param controls The pointer to the instance of the class.
	 * \return Returns TRUE if successful.
	 */
	public boolean setControls(Controls controls) {
		m_controls = controls;
		return true;
	}
	
	/**
	 * Constructor method which is used to initialise this class and indicate
	 * the size of the controllable area on the grid.
	 * \param width The width as an integer of the individual squares.
	 * \param height The height as an integer of the individual squares.
	 * \param controls The control class in which this class should call the
	 * MoveMade method of.
	 */
	public GameBoardControls(int width, int height, Controls controls) {
		setCellWidth(width);
		setCellHeight(height);
		setControls(controls);
	}
	
	/**
	 * Changed to accommodate the new AI classes
	 * This method is called whenever the mouse is clicked and overwrites the
	 * mouseClicked method within the MouseAdapter class that this extends.
	 */
	public void mouseClicked(MouseEvent e) {
		boolean m_Trace = false;
		
		int gridX = e.getX() / getCellWidth();
		int gridY = e.getY() / getCellHeight();
		if(m_Trace) System.out.println("GameBoardControls::mouseClicked()"
				+ " - Mouse clicked at grid: " + gridX + ":" + gridY);
		try {
			if(((m_controls.getGameWindow().getGame().getPlayerTurn() 
					== Game.PlayerTurn.PLAYER1 && 
				m_controls.getGameWindow().getGame().getPlayer1() 
				instanceof Human)||
				(m_controls.getGameWindow().getGame().getPlayerTurn() 
					== Game.PlayerTurn.PLAYER2 && 
				m_controls.getGameWindow().getGame().getPlayer2() 
				instanceof Human))&&
				(!m_controls.getGameWindow().getDrawing().getGridPanel().
						GetFlip())){
					getControls().moveMade(new Coordinate(gridX, gridY));
			}
			//getControls().moveMade(new Coordinate(gridX, gridY));
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void main(String args[]) throws InterruptedException{
		int testWidth =30;
		int testHeight = 30;
		Game connect4 = new ConnectFour();
		Player player1 = new Human(connect4);
		Player player2 = new ConnectFourAI(connect4);
		player1.setPlayerName("Gavin");
		player2.setPlayerName("Lucy");
		player1.setPlayerColour(Color.RED);
		player2.setPlayerColour(Color.YELLOW);
		connect4.setPlayer1(player1);
		connect4.setPlayer2(player2);
		connect4.start();
		GameBoardControls controls = new GameBoardControls(
				testWidth,testHeight,connect4.getWindow().getControls());
		controls.setCellHeight(testHeight + 1);
		controls.setCellWidth(testWidth + 1);
		if(controls.getCellHeight() == testHeight+1)
			System.out.println("Changed Height Success");
		if(controls.getCellWidth() == testWidth + 1)
			System.out.println("Changed Width Success");
		controls.setControls(connect4.getWindow().getControls());
		if(connect4.getWindow().getControls() == controls.getControls())
			System.out.println("Controls are the same");
		
	}

	/**The cell width*/
	private int m_cellWidth;
	/**The cell height*/
	private int m_cellHeight;
	/**The Controls reference stored here*/
	private Controls m_controls;
}