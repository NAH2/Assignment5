/**
 *  \file -Grid.java
 * 	\author - J.Dong
 *	\date	-12/02/2014
 *
 *	\brief This class contains methods to store information on grid 
 *		   and provides methods to set and get coordinates of the pieces.
 */

public class Grid {
	
	/**
	 * method to set the grid.
	 * \param grid the grid used for the games 
	 */
	public void setGrid(Game.PlayerTurn[][] grid) {
		m_Grid = grid;
	}
	
	/**
	 * method to get the height of the grid
	 * \return the height of the grid
	 */
	public int getGridHeight(){
		return m_Grid[0].length;
	}
	
	/**
	 * method to get the width of the grid
	 * \return The width of the grid
	 */
	public int getGridWidth(){
		return m_Grid.length;
	}
	
	/**
	 * Method used to set the coordinate
	 * \param a The coordinate of the piece that has been placed.
	 */
	public void setCoordinate(Coordinate a) {
		m_Grid[a.getX()][a.getY()] = a.getValue();
	}
	
	/**
	 * Method used to get the coordinate
	 * \param x The x coordinate of the piece.
	 * \param y The y coordinate of the piece.
	 * \return the coordinate
	 */
	public Coordinate getCoordinate(int x, int y) {
		return new Coordinate(x,y,m_Grid[x][y]);
	}
	/**
	 * Method used to get the coordinate
	 * \param c the c the c coordinate of the piece
	 * \return the coordinate
	 */
	 public Coordinate getCoordinate(Coordinate c) {
		return new Coordinate(c.getX(),c.getY(),m_Grid[c.getX()][c.getY()]);
	}
	
	/**
	 * Constructor method, gets the coordinate of the piece from Game class.
	 * \param x The x coordinate of the piece.
	 * \param y The y coordinate of the piece.
	 */
	public Grid(int x, int y){
		setGrid(new Game.PlayerTurn[x][y]);
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				setCoordinate(new Coordinate(i, j, Game.PlayerTurn.NONE));
			}
		}
	}
	
	/**
	 * method used to get a string of the grid width and height
	 * \return gridString returns the string for the gridwidth and height
	 */
	public String toString() {
	    String gridString = "";
	    for (int y = 0; y < getGridHeight(); ++y) {
	        for (int x = 0; x < getGridWidth(); ++x) {
	            gridString += m_Grid[x][y] + ",";
	        }
	    }
	    
	    return gridString;
	}
	
	/**
	 * Global variables
	 */
	private Game.PlayerTurn m_Grid[][];
}