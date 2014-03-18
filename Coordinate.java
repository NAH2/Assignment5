/*
 *  @file	-Coordinate.Java
 * 	@author	-B. Golightly (659715)
 * 	@date	-12/02/2014
 * 	
 * 	/brief	Coordinate objects are used to hold information about one individual
 * 			point in the game board, such as an Othello square or Connect Four
 * 			place. They contain X and Y values to identify their position in
 * 			game/logical (not pixel) space, and may also contain a value that,
 * 			if set, contains information to identify the owner of the point.
 *      A coordinate may be owned by the grid, or simply a holder for information
 *      about a grid location that can be easily "committed" - for example
 *      see the Game class which iterates over an ArrayList of Coordinates that
 *      represent the result of a move according to given game rules.
 */
public class Coordinate
{
	private int m_X;
	private int m_Y;
	private Game.PlayerTurn m_Value;
	
	/**
	 * Coordinate constructor
	 * @param x The horizontal position of the coordinate ranging from 0 to
	 *			the grid logical width minus one.
	 * @param y The vertical position of the coordinate ranging from 0 to
	 *			the grid logical height minus one.
	 * @return	A coordinate initialised with a default zero value.
	 */
	public Coordinate(int x, int y) {
		this(x, y, Game.PlayerTurn.NONE);
	}
	
	/**
	 * Coordinate constructor
	 * @param x The horizontal position of the coordinate ranging from 0 to
	 *			the grid logical width minus one.
	 * @param y The vertical position of the coordinate ranging from 0 to
	 *			the grid logical height minus one.
	 * @param value Any valid player enum.
	 * @return	A coordinate initialised to the specified values.
	 */
	public Coordinate(int x, int y, Game.PlayerTurn value) {
		if (x < 0) { throw new IllegalArgumentException("Illegal X argument " + x); }
		if (y < 0) { throw new IllegalArgumentException("Illegal Y argument " + y); }
		
		m_X = x;
		m_Y = y;
		m_Value = value;
	}
	
	/**
	 * Coordinate constructor
	 * @param c Coordinate to duplicate.
	 * @return	A coordinate initialised as a copy of an existing coordinate.
	 */
	public Coordinate(Coordinate c) {
		this(c.getX(), c.getY(), c.getValue());
	}
	
	/**
	 * SetValue
	 * @param value Any valid player enum.
	 */
	public void setValue(Game.PlayerTurn value) {
		m_Value = value;
	}
	
	/**
	 * Query the logical horizontal position of the coordinate
	 * @return The coordinate's x value.
	 */
	public int getX() {
		 return m_X;
	}
	
	/**
	 * Query the logical vertical position of the coordinate
	 * @return The coordinate's y value.
	 */
	public int getY() {
		 return m_Y;
	}
	
	/**
	 * Query the set value of the coordinate
	 * @return The coordinate's current value.
	 */
	public Game.PlayerTurn getValue() {
		return m_Value;
	}
	
	/**
	 * Query if a coordinate value equals a certain value
	 * @return True if it does.
	 */
	public boolean equals(Game.PlayerTurn value) {
		return (m_Value == value);
	}
	
	/**
	 * Query if a coordinate value equals a certain value
	 * @return True if it doesn't.
	 */
	public boolean notEquals(Game.PlayerTurn value) {
		return (m_Value != value);
	}
	
	/**
	 * Query if a coordinate value represents the empty value
	 * @return True if it does.
	 */
	public boolean isEmpty() {
		return (m_Value == Game.PlayerTurn.NONE);
	}
	
	/**
	 * Query if a coordinate value represents a non-empty value
	 * @return True if it does.
	 */
	public boolean isFilled() {
		return (m_Value != Game.PlayerTurn.NONE);
	}
	
	/**
	 * Translate the position of a coordinate
	 * @param x distance to move horizontally
	 * @param y distance to move vertically
	 */
	public void move(int x, int y) {
		// used by Othello class to iterate in a direction
		// when capturing peices or checking if a move
		// is valid.
		m_X += x;
		m_Y += y;
	}
	
	/**
	 * Query if a coordinate lies within a certain range
	 * @param x0 Start x position (inclusive/left-bounded)
	 * @param y0 Start y position (inclusive/left-bounded)
	 * @param x1 End x position (exclusive/right-open)
	 * @param y1 End y position (exclusive/right-open)
	 * @return True if it does.
	 */
	public boolean within(int x0, int y0, int x1, int y1) {
		// x1/y1 are right-open
		return ((m_X >= x0) && (m_Y >= y0) && (m_X < x1) && (m_Y < y1));
	}
	
	/**
	 * Construct a string representation of a coordinate for debugging purposes.
	 * @return A string representation of a coordinate
	 */
	public String toString() {
		return "Coordinate(" + m_X + ", " + m_Y + ", " + m_Value + ")";
	}
}
