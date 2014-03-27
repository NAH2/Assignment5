import java.awt.Color;

/**
 *  \file	-Player.Java
 * 	\author	-C. Hazelton
 * 	\date	-14/02/2014
 * 	
 * 	\brief	Gets move that returns coordinates. Contains name and colour get/set methods.
 */

public abstract class Player {
	
	/**
	 * Sets the game that is going to be played
	 * \param instance of the game class
	 */
	protected void setGame(Game game) {
		m_game = game;
	}
	/**
	 * Gets the game thats going to be played
	 * \return game being played
	 */
	protected Game getGame() {
		return m_game;
	}
	/**
	 * Gets whether the turn is each players'
	 * \return true or false whether it is the players' turn
	 */
	protected boolean getYourTurn() {
		return m_YourTurn;
	}
	/**
	 * Sets whether the turn is each players'
	 * \param set to true if it is players turn, otherwise false
	 */
	protected void setYourTurn(boolean turn) {
		m_YourTurn = turn;
	}
	
	/**
	 * Sets the player name
	 * \param playerName
	 */
	public void setPlayerName(String playerName){
		this.m_playerName=playerName;
	}
	/**
	 * sets the player colour
	 * \param playerColour
	 */
	public void setPlayerColour(Color playerColour){
		m_playerColour = playerColour;
	}	
	/**
	 * gets the name of the player
	 * \return player name
	 */
	public String getPlayerName(){
		return m_playerName;
	}
	/**
	 * 	gets the colour of the player
	 * \return colour
	 */
	public Color getPlayerColour(){
	    //System.out.println(m_playerColour.getRGB());
		return m_playerColour;
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
	 * abstract method that can be implemented 
	 * which gets called whenever it is players turn
	 * \throws InterruptedException 
	 */
	public abstract void isYourMove() throws InterruptedException;
	/**
	 * abstract method which is called whenever gamewindow receives a move
	 * \param move that was made
	 * \throws InterruptedException 
	 */
	public abstract void sendMove(Coordinate move) throws InterruptedException;
	
	public abstract void sendMove() throws InterruptedException;
	
	public String toString(){
        String playerData = getClass().getSimpleName() + "," + getPlayerName() + ","
                + getPlayerColour().getRGB() + "," + getYourTurn() + ",";
        System.out.println(playerData);
        return playerData;
    }
	
	private String m_playerName;
	private Color m_playerColour;
	private Game m_game;
	private boolean m_YourTurn = false;

}
