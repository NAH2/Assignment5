/**
 *  @file	-Player.Java
 * 	@author	-C. Hazelton
 * 	@date	-14/02/2014
 * 	
 * 	@brief	Gets move that returns coordinates. Contains name and colour get/set methods.
 */
import java.awt.Color;


public abstract class Player {
	private String playerName;
	private Color m_playerColour;
	private Game m_game;
	private boolean m_YourTurn = false;
	/**
	 * Sets the game that is going to be played
	 * @param instance of the game class
	 */
	protected void setGame(Game game) {
		m_game = game;
	}
	/**
	 * Gets the game thats going to be played
	 * @return game being played
	 */
	protected Game getGame() {
		return m_game;
	}
	/**
	 * Gets whether the turn is each players'
	 * @return true or false whether it is the players' turn
	 */
	protected boolean getYourTurn() {
		return m_YourTurn;
	}
	/**
	 * Sets whether the turn is each players'
	 * @param set to true if it is players turn, otherwise false
	 */
	protected void setYourTurn(boolean turn) {
		m_YourTurn = turn;
	}
	 /**
	  * constructor
	  */
	public Player(Game game) {
		setGame(game);
	}
	 /**
	  * constructor
	  */
	public Player(Game game, String name, Color color) {
		setGame(game);
		setPlayerName(name);
		setPlayerColour(color);
	}
	/**
	 * Sets the player name
	 * @param playerName
	 */
	public void setPlayerName(String playerName){
		this.playerName=playerName;
	}
	/**
	 * sets the player colour
	 * @param playerColour
	 */
	public void setPlayerColour(Color playerColour){
		m_playerColour = playerColour;
	}	
	/**
	 * gets the name of the player
	 * @return player name
	 */
	public String getPlayerName(){
		return playerName;
	}
	/**
	 * 	gets the colour of the player
	 * @return colour
	 */
	public Color getPlayerColour(){
	    //System.out.println(m_playerColour.getRGB());
		return m_playerColour;
	}	
	/**
	 * abstract method that can be implemented 
	 * which gets called whenever it is players turn
	 */
	public abstract void isYourMove();
	/**
	 * abstract method which is called whenever gamewindow receives a move
	 * @param move that was made
	 */
	public abstract void sendMove(Coordinate move);
	
	public abstract String toString();

}
