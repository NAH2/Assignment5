import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.*;
import javax.swing.*;
/**
 * @file GameBoardGraphics.java
 * @author Daniel 709547
 * @date 25/02/2014
 * @brief Class which deals with the graphics of the game board
 *
 * This class deals with all the graphics of the actual game board
 * itself, being called whenever the gameboard is updated
 * or needs to be repainted.
 */
public class GameBoardGraphics extends JComponent{
	
	/**
	 * returns the variable m_grid to the caller of the method.
	 * @return private member variable m_grid, a grid object.
	 */
	
	public Grid getGrid() {
		return m_grid;
	}
	
	/**
	* Setter method to set the value of a private member variable grid
	* @param grid - object storing data for the game grid 
	*/
	public void setGrid(Grid grid) {
		m_grid = grid;
		
		repaint();
	}
	
	/**
	 * Method to get the constant SQUARE_WIDTH
	 * @return constant variable SQUARE_WIDTH
	 */
	
	public int getSquareWidth() {
		return SQUARE_WIDTH;
	}
	
	/**
	 * Method to get the constant SQUARE_HEIGHT
	 * @return constant variable SQUARE_HEIGHT
	 */
	
	public int getSquareHeight() {
		return SQUARE_HEIGHT;
	}
	
	/**
	 * Method to get the constant Y_SQUARES
	 * @return constant variable Y_SQUARES
	 */
	
	public int getYSquares() {
		return Y_SQUARES;
	}
	
	/**
	 * Method to get the constant X_SQUARES
	 * @return constant variable X_SQUARES
	 */
	
	public int getXSquares() {
		return X_SQUARES;
	}
	
	/**
	* Class Constructor for GameBoardGraphics, initialises all 
	* necessary variables.
	* @param grid - object storing data for the game grid
	* @param player1 - object containing the data for player 1
	* @param player2 - object containing the data for player 2
	*/
	public GameBoardGraphics(Grid grid, Player player1, Player player2) {
		setGrid(grid);
		Y_SQUARES = getGrid().getGridHeight();
		X_SQUARES = getGrid().getGridWidth();
		GRID_WIDTH = (getXSquares() * getSquareWidth());
		GRID_HEIGHT = (getYSquares() * getSquareHeight());
		PLAYER1_COLOUR = player1.getPlayerColour();
		PLAYER2_COLOUR = player2.getPlayerColour();
		setPreferredSize(new Dimension(GRID_WIDTH, GRID_HEIGHT));		
	}
	
	/**
	* Method to update all the GUI elements and paint them to the screen.
	* @param g - graphics object to handle all the data for creating
	* / repainting the gameboard
	*/

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		final int RED = 122;
		final int GREEN = 129;
		final int BLUE = 214;
		
		for (int i = 0; i < GRID_WIDTH; i+=getSquareWidth()) {
			for (int j = 0; j < GRID_HEIGHT; j+=getSquareHeight()) {				
				g2.setColor(new Color(RED,GREEN,BLUE));
				g2.fillRect(i, j, getSquareWidth(), getSquareHeight());
				
				g2.setColor(Color.WHITE);
				g2.setStroke(new BasicStroke(2));
				g2.drawRect(i, j, getSquareWidth(), getSquareHeight());
				
				if (getGrid().getCoordinate(i/getSquareWidth(),j/
						getSquareHeight()).getValue()==Game.PlayerTurn.PLAYER1) {
					g2.setColor(PLAYER1_COLOUR);
					g2.fillOval(i, j, getSquareWidth(), getSquareHeight());
				} else if (getGrid().getCoordinate(i/getSquareWidth(),j/
						getSquareHeight()).getValue()==Game.PlayerTurn.PLAYER2) {
					g2.setColor(PLAYER2_COLOUR);
					g2.fillOval(i, j, getSquareWidth(), getSquareHeight());
				}
				
			}
		}
		if(m_isOver){
			paintWin(g2);
		}
	}
	
	public void setIsOver(boolean over, Set<Coordinate> win){
		m_isOver = over;
		m_win = new HashSet<Coordinate>(win);
		//System.out.println("win is empty?"+win.isEmpty());
	}
	
	public void paintWin(Graphics g){
		Iterator<Coordinate> iterator = m_win.iterator();
		while (iterator.hasNext()){
			//System.out.println(iterator.next()+"SSS");
			g.setColor(Color.GREEN);  
			m_next = iterator.next();
			//System.out.println(m_next);
			g.fillOval((SQUARE_WIDTH*m_next.getX()+middlePosition), (SQUARE_HEIGHT*m_next.getY()+middlePosition), smallSize, smallSize);
		}
		m_win.clear();
	}
	
	//private member variables
	private Grid m_grid;
	private final int Y_SQUARES;
	private final int X_SQUARES;
	private final Color PLAYER1_COLOUR;
	private final Color PLAYER2_COLOUR;
	private final int SQUARE_WIDTH = 60;
	private final int SQUARE_HEIGHT = 60;
	private final int GRID_WIDTH;
	private final int GRID_HEIGHT;
	private boolean m_isOver = false;
	private Set<Coordinate> m_win;
	private Coordinate m_next;
	private final int middlePosition = (SQUARE_WIDTH + SQUARE_HEIGHT) / 6;
	private final int smallSize = (SQUARE_WIDTH + SQUARE_HEIGHT) / 6;
}
