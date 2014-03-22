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
		if(m_type.equals("fall")){
			new Thread(
					new Runnable() {
						public void run() {
						for(m_y = m_dropPoint; m_y <=  m_changes.get(0).getY()*getSquareHeight(); m_y = m_y+m_fallDistance){
							//System.out.println("drop:"+m_y);
							try{
								if(!m_isOver){
									repaint();
									Thread.sleep(m_fallTime);
								} else {
									repaint();
									Thread.sleep(m_fallTime);
									m_isOver = true;
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
							m_i = 0;
							for(m_w = getSquareHeight(); m_w > 0; m_w=m_w-2){							
									repaint();
									m_i = m_i + 1;
									Thread.sleep(m_flipTime);			
							}
							m_flip = true;
							for(m_w = 0; m_w < getSquareHeight(); m_w=m_w+2){
									repaint();
									m_i = m_i - 1;
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
		final int RED = 122;
		final int GREEN = 129;
		final int BLUE = 214;
		
		for (int i = 0; i < GRID_WIDTH; i+=getSquareWidth()) {
			for (int j = 0; j < GRID_HEIGHT; j+=getSquareHeight()) {
				//************************
				m_status = true;
				if(m_type.equals("flip")&&m_changes.size()>0&&m_start){
					Iterator<Coordinate> s = m_changes.iterator();
					for(s = m_changes.iterator(); s.hasNext(); ) {
					    Coordinate item = s.next();
					    if(item.getX()*getSquareWidth() == i && item.getY()*getSquareHeight() == j){
					    	m_status = false;
							g2.setColor(new Color(RED,GREEN,BLUE));
							g2.fillRect(i, j, getSquareWidth(), getSquareHeight());
							g2.setColor(Color.WHITE);
							g2.setStroke(new BasicStroke(2));
							g2.drawRect(i, j, getSquareWidth(), getSquareHeight());
							//System.out.println("FLIPPING");
							if(item.getValue()==Game.PlayerTurn.PLAYER1){
								if (!m_flip){
									//System.out.println("FLIPPING");
									g2.setColor(PLAYER2_COLOUR);
									g2.fillOval(i + m_i, j, m_w, getSquareHeight());
								} else {
									g2.setColor(PLAYER1_COLOUR);
									g2.fillOval(i + m_i, j, m_w, getSquareHeight());
								}
							} else {
								if (!m_flip){
									//System.out.println("FLIPPING");
									g2.setColor(PLAYER1_COLOUR);
									g2.fillOval(i + m_i, j, m_w, getSquareHeight());
								} else {
									g2.setColor(PLAYER2_COLOUR);
									g2.fillOval(i + m_i, j, m_w, getSquareHeight());
								}
							}
						}
					}
				}
				//************************
				if (m_status){
					g2.setColor(new Color(RED,GREEN,BLUE));
					g2.fillRect(i, j, getSquareWidth(), getSquareHeight());
				
					g2.setColor(Color.WHITE);
					g2.setStroke(new BasicStroke(2));
					g2.drawRect(i, j, getSquareWidth(), getSquareHeight());
				}
				//**********************
				if(m_type.equals("fall")&&m_changes.size()>0){
					if(i == m_changes.get(0).getX()*getSquareWidth() && j == m_changes.get(0).getY()*getSquareHeight()){
					continue;
					}
				}
				//**********************
				if(m_status){
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
		if(m_type.equals("fall")&&m_start&&m_changes.size()>0){
			int m_x = m_changes.get(0).getX()*getSquareWidth();
			if (m_changes.get(0).getValue() == Game.PlayerTurn.PLAYER1){
				g.setColor(PLAYER1_COLOUR);
			} else {
				g.setColor(PLAYER2_COLOUR);
			}
			//System.out.println(m_y);
				g.fillOval(m_x, m_y, getSquareWidth(), getSquareHeight());
		}
		//********************
		if(m_isOver){
			paintWin(g2);
		}
	}
	
	/**
	* Method to get the winning piece coordinates for showing the winning pieces graphically,
	* signal the game is over
	* @param win - a set of winning piece coordinates without repetition
	*/
	public void setIsOver(boolean over, Set<Coordinate> win){
		m_isOver = over;
		m_win = new HashSet<Coordinate>(win);
	}
	
	/**
	* Method to show green dots on the winning pieces when the game ends
	* @param g - graphics object to handle all the data for creating
	*/
	public void paintWin(Graphics g){
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
	private int m_i = 0;
	private boolean m_status;
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
	//********************
}
