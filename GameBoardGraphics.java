import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
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
public class GameBoardGraphics extends JComponent implements MouseMotionListener{
	
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
		addMouseMotionListener(this);
		setGrid(grid);
		Y_SQUARES = getGrid().getGridHeight();
		X_SQUARES = getGrid().getGridWidth();
		GRID_WIDTH = (getXSquares() * getSquareWidth());
		GRID_HEIGHT = (getYSquares() * getSquareHeight());
		PLAYER1_COLOUR = player1.getPlayerColour();
		PLAYER2_COLOUR = player2.getPlayerColour();
		setPreferredSize(new Dimension(GRID_WIDTH, GRID_HEIGHT));		
	}
	
	
	
	//*********************************
	/**
	* Method responsible for generating animation of pieces in both game(Connect4 only atm)
	* @param type - type of animation that is either flip or fall
	* @param changes - the list stores the pieces which need the animation
	*/
	public void SetAnimation(String type, ArrayList<Coordinate> changes){
		m_type = type;
		m_changes = changes;
		m_start = true;
		m_lowestY = m_changes.get(0).getY()*getSquareHeight();
		if(m_type.equals("fall")){
			new Thread(
				new Runnable() {
					public void run() {
						for(m_y = m_dropPoint; m_y <= m_lowestY ; m_y = m_y+m_fallDistance){
							//System.out.println("drop:"+m_y);
							try{
								if(!m_isOver){
									repaint();
									Thread.sleep(m_fallTime);
								} else {
									repaint();
									Thread.sleep(m_fallTime);
								}
							} catch (Exception e){e.printStackTrace();}	
						}
						m_changes.clear();
					}
				}
			).start();
		} else {
			new Thread(
				new Runnable() {
					public void run() {
						try{
							m_x = 0;
							for(m_w = getSquareHeight(); m_w > 0; m_w=m_w-2){							
								repaint();
								m_x = m_x + 1;
								Thread.sleep(m_flipTime);			
							}
							m_flip = true;
							for(m_w = 0; m_w < getSquareHeight(); m_w=m_w+2){
								repaint();
								m_x = m_x - 1;
								Thread.sleep(m_flipTime);	
							} 		
						} catch (Exception e){e.printStackTrace();}		
						m_changes.clear();
						m_flip = false;
					}
				}
			).start();
		}
		
	}
	//**********************	
	
	/**
	* Method to update all the GUI elements and paint them to the screen.
	* @param g - graphics object to handle all the data for creating
	* / repainting the gameboard
	*/

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		for (int i = 0; i < GRID_WIDTH; i+=getSquareWidth()) {
			for (int j = 0; j < GRID_HEIGHT; j+=getSquareHeight()) {
				//************************
				paintFlip(g2, i ,j);
				//************************
				if (!m_flipPiece){
					g2.setColor(new Color(RED,GREEN,BLUE));
					g2.fillRect(i, j, getSquareWidth(), getSquareHeight());
				
					g2.setColor(Color.WHITE);
					g2.setStroke(new BasicStroke(2));
					g2.drawRect(i, j, getSquareWidth(), getSquareHeight());
				}
				//**********************
				if(m_type.equals("fall") && m_changes.size() > 0){
					if(i == m_changes.get(0).getX()*getSquareWidth() && j == m_changes.get(0).getY()*getSquareHeight()){
					continue;
					}
				}
				//**********************
				if(!m_flipPiece){
					if (getGrid().getCoordinate(i/getSquareWidth(),j/
						getSquareHeight()).getValue()==Game.PlayerTurn.PLAYER1) {
						g2.setColor(PLAYER1_COLOUR);
						g2.fillOval(i , j, getSquareWidth(), getSquareHeight());
					} else if (getGrid().getCoordinate(i/getSquareWidth(),j/
						getSquareHeight()).getValue()==Game.PlayerTurn.PLAYER2) {
						g2.setColor(PLAYER2_COLOUR);
						g2.fillOval(i, j, getSquareWidth(), getSquareHeight());
					}
				}
			}
		}
		//*****************		
		paintFall(g2);
		//********************
		if(!m_isOver){	
			if(player == Game.PlayerTurn.PLAYER1) {
				g2.setColor(PLAYER2_COLOUR);
				g2.fillOval(m_posX , m_posY, getSquareWidth(), getSquareHeight());//if(m_type.equals("flip")&&m_changes.size()>0){
			} else {
				g2.setColor(PLAYER1_COLOUR);
				g2.fillOval(m_posX , m_posY, getSquareWidth(), getSquareHeight());
			}
		} else {
			paintWin(g2);
			player = Game.PlayerTurn.PLAYER2;
		}
	}
	
	/**
	* Method to paint the animation of the falling connect four piece
	* @param g2 - graphics object to handle all the data for creating
	*/
	private void paintFall(Graphics2D g2){
		if(m_type.equals("fall") && m_start && m_changes.size() > 0){
			int m_x = m_changes.get(0).getX()*getSquareWidth();
			if (m_changes.get(0).getValue() == Game.PlayerTurn.PLAYER1){
				player = Game.PlayerTurn.PLAYER1;
				g2.setColor(PLAYER1_COLOUR);
			} else {
				player = Game.PlayerTurn.PLAYER2;
				g2.setColor(PLAYER2_COLOUR);
			}
			//System.out.println(m_y);
			g2.fillOval(m_x, m_y, getSquareWidth(), getSquareHeight());
		}
	}
	
	/**
	* Method to paint the animation of the flipping othello piece
	* @param g2 - graphics object to handle all the data for creating
	* @param i - point X of the piece to be flipped
	* @param j - point Y of the piece to be flipped
	*/
	private void paintFlip(Graphics2D g2, int i, int j){
		m_flipPiece = false;
		if(m_type.equals("flip") && m_changes.size()>0 && m_start){
			Iterator<Coordinate> s = m_changes.iterator();
			for(s = m_changes.iterator(); s.hasNext(); ) {
				Coordinate item = s.next();
				if(item.getX()*getSquareWidth() == i && item.getY()*getSquareHeight() == j){
				    m_flipPiece = true;
					g2.setColor(new Color(RED,GREEN,BLUE));
					g2.fillRect(i, j, getSquareWidth(), getSquareHeight());
					g2.setColor(Color.WHITE);
					g2.setStroke(new BasicStroke(2));
					g2.drawRect(i, j, getSquareWidth(), getSquareHeight());
					//System.out.println("FLIPPING");
					if(item.getValue()==Game.PlayerTurn.PLAYER1){
						player = Game.PlayerTurn.PLAYER1;
						if (!m_flip){
							//System.out.println("FLIPPING");
							g2.setColor(PLAYER2_COLOUR);
							g2.fillOval(i + m_x, j, m_w, getSquareHeight());
						} else {
							g2.setColor(PLAYER1_COLOUR);
							g2.fillOval(i + m_x, j, m_w, getSquareHeight());
						}
					} else {
						player = Game.PlayerTurn.PLAYER2;
						if (!m_flip){
							//System.out.println("FLIPPING");
							g2.setColor(PLAYER1_COLOUR);
							g2.fillOval(i + m_x, j, m_w, getSquareHeight());
						} else {
							g2.setColor(PLAYER2_COLOUR);
							g2.fillOval(i + m_x, j, m_w, getSquareHeight());
						}
					}
				}
			}
		}
	}
	
	/**
	* Method to get the winning piece coordinates for showing the winning pieces graphically,
	* signal the game is over
	* @param win - a set of winning piece coordinates without repetition
	* @param over - a boolean, true when the game is over
	*/
	public void setIsOver(boolean over, Set<Coordinate> win){
		m_isOver = over;
		m_win = new HashSet<Coordinate>(win);
	}
	
	/**
	* Method to show green dots on the winning pieces when the game ends
	* @param g - graphics object to handle all the data for creating
	*/
	private void paintWin(Graphics g){
		Iterator<Coordinate> iterator = m_win.iterator();
		while (iterator.hasNext()){
			//System.out.println(iterator.next()+"SSS");
			g.setColor(Color.GREEN);  
			m_next = iterator.next();
			//System.out.println(m_next);
			g.fillOval((SQUARE_WIDTH*m_next.getX()+middlePosition), (SQUARE_HEIGHT*m_next.getY()+middlePosition), smallSize, smallSize);
		}
		//m_win.clear();
	}
	
	/**
	* Method to update the mouse position to draw a piece following the cursor
	* @param e - An event which indicates that a mouse action occurred in a component
	*/
	public void mouseMoved(MouseEvent e) {
		//System.out.println("x:"+e.getX()+", y:"+e.getY());
		m_posX = e.getX() - getSquareWidth()/2;
		m_posY = e.getY() - getSquareHeight()/2;
		repaint();
		if(m_isOver){
			m_isOver = false;
		}
	}
	
	/**
	* Method only for completing the implementation of MouseMotionListener
	* @param e - An event which indicates that a mouse action occurred in a component
	*/
	public void mouseDragged(MouseEvent e) {
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
	//********************
	private int m_posX;
	private int m_posY;
	private int m_x = 0;
	private boolean m_flipPiece;
	private boolean m_flip = false;
	private int m_dropPoint = -30;
	private int m_fallTime = 20;
	private int m_flipTime = 10;
	private int m_fallDistance = 30;
	private String m_type = "";
	private ArrayList<Coordinate> m_changes;
	private int m_w = 0;
	private int m_y = 0;
	private boolean m_start = false;
	private int m_lowestY;
	private Game.PlayerTurn player;
	private final int RED = 122;
	private final int GREEN = 129;
	private final int BLUE = 214;
	//********************
}
