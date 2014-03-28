/**
 *  \\file -Grid.java
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
		boolean test = false;
        if (test || m_test) {
            System.out.println("Grid :: setGrid() BEGIN");
        }
		m_Grid = grid;
		if (test || m_test) {
            System.out.println("Grid :: setGrid() END");
        }
	}
	
	/**
	 * method to get the height of the grid
	 * \return the height of the grid
	 */
	public int getGridHeight(){
		boolean test = false;
        if (test || m_test) {
            System.out.println("Grid :: getGridHeight() BEGIN");
        }
        if (test || m_test) {
            System.out.println("Grid :: getGridHeight() END");
        }
		return m_Grid[0].length;
	}
	
	/**
	 * method to get the width of the grid
	 * \return The width of the grid
	 */
	public int getGridWidth(){
		boolean test = false;
        if (test || m_test) {
            System.out.println("Grid :: getGridWidth() BEGIN");
        }
        if (test || m_test) {
            System.out.println("Grid :: getGridWidth() END");
        }
		return m_Grid.length;
	}
	
	/**
	 * Method used to set the coordinate
	 * \param a The coordinate of the piece that has been placed.
	 */
	public void setCoordinate(Coordinate a) {
		boolean test = false;
        if (test || m_test) {
            System.out.println("Grid :: setCoordinate() BEGIN");
        }
		m_Grid[a.getX()][a.getY()] = a.getValue();
		if (test || m_test) {
            System.out.println("Grid :: setCoordinate() END");
		}
	}
	
	/**
	 * Method used to get the coordinate
	 * \param x The x coordinate of the piece.
	 * \param y The y coordinate of the piece.
	 * \return the coordinate
	 */
	public Coordinate getCoordinate(int x, int y) {
		boolean test = false;
        if (test || m_test) {
            System.out.println("Grid :: getCoordinate() BEGIN");
        }
        if (test || m_test) {
            System.out.println("Grid :: getCoordinate() END");
        }
		return new Coordinate(x,y,m_Grid[x][y]);
	}
	/**
	 * Method used to get the coordinate
	 * \param c the c the c coordinate of the piece
	 * \return the coordinate
	 */
	 public Coordinate getCoordinate(Coordinate c) {
		 boolean test = false;
	        if (test || m_test) {
	            System.out.println("Grid :: getCoordinate() BEGIN");
	        }
	        if (test || m_test) {
	            System.out.println("Grid :: getCoordinate() END");
	        }
		return new Coordinate(c.getX(),c.getY(),m_Grid[c.getX()][c.getY()]);
	}
	
	/**
	 * Constructor method, gets the coordinate of the piece from Game class.
	 * \param x The x coordinate of the piece.
	 * \param y The y coordinate of the piece.
	 */
	public Grid(int x, int y){
		boolean test = false;
        if (test || m_test) {
            System.out.println("Grid :: Grid() BEGIN");
        }
		setGrid(new Game.PlayerTurn[x][y]);
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				setCoordinate(new Coordinate(i, j, Game.PlayerTurn.NONE));
				if (test || m_test) {
		        System.out.println("Grid :: Grid() END");
		        }
			}
		}
	}
	
	/**
	 * method used to get a string of the grid width and height
	 * \return gridString returns the string for the gridwidth and height
	 */
	public String toString() {
		boolean test = false;
        if (test || m_test) {
            System.out.println("Grid :: toString() BEGIN");
        }
	    String gridString = "";
	    for (int y = 0; y < getGridHeight(); ++y) {
	        for (int x = 0; x < getGridWidth(); ++x) {
	        gridString += m_Grid[x][y] + ",";
	        }
	    }
	    if (test || m_test) {
            System.out.println("Grid :: toString() END");
        }

	    return gridString;
	}
	
	
	/**
	 * main method for testing
	 */
	public static void main(String[] args) {
		
		
			
			Grid g = new Grid(GRIDTEST, GRIDTEST2);
			if(GRIDTEST == g.getGridWidth()) {
				System.out.println("GridWidth == " + GRIDTEST);
			}
			if(GRIDTEST2 == g.getGridHeight()) {
				System.out.println("GridHeight == " + GRIDTEST2);
			}
			
			Grid g2 = new Grid(GRIDTEST,GRIDTEST3);
			Coordinate c1 = new Coordinate(1, COORD, Game.PlayerTurn.PLAYER1);
			g2.setCoordinate(c1);
			if (g.getCoordinate(1, COORD).getValue() == 
			                                          Game.PlayerTurn.PLAYER1) {
				System.out.println("coordinate is 1," + COORD);
		}
	}
	
	/**
	 * Global variables
	 */
	private Game.PlayerTurn m_Grid[][];
	private final static int GRIDTEST = 111;
	private final static int GRIDTEST2 = 5;
	private final static int GRIDTEST3 = 222;
	private final static int COORD = 2;
	private boolean m_test = false;
}
