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
	protected Boolean setGame(Game game) {
		m_game = game;
		return true;
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
	protected boolean setYourTurn(boolean turn) {
		m_YourTurn = turn;
		return true;
	}
	
	/**
	 * Sets the player name
	 * \param m_playerName a string for the player name
	 */
	public boolean setPlayerName(String playerName){
		this.m_playerName=playerName;
		return true;
	}
	/**
	 * sets the player colour
	 * \param m_playerColour for the players colour
	 */
	public boolean setPlayerColour(Color playerColour){
		m_playerColour = playerColour;
		return true;
	}	
	/**
	 * gets the name of the player
	 * \return m_playerName returns a string of the players name
	 */
	public String getPlayerName(){
		return m_playerName;
	}
	/**
	 * 	gets the colour of the player
	 * \return m_playerColour returns the players colour
	 */
	public Color getPlayerColour(){
	    //System.out.println(m_playerColour.getRGB());
		return m_playerColour;
	}	
	
	 /**
	  * Constructor of Player, receives the type of game 
	  * \param game the type of game to be played
	  */
	public Player(Game game) {
		setGame(game);
	}
	 /**
	  * Constructor of player, receive the type of game, name of player and colour
	  * \param game a type of game for selecting the game
	  * \param name a string for the player name
	  * \param color a type colour for selecting colour
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
	public abstract boolean isYourMove() throws InterruptedException;
	/**
	 * abstract method which is called whenever gamewindow receives a move
	 * \param move that was made
	 * \throws InterruptedException 
	 */
	public abstract boolean sendMove(Coordinate move) throws InterruptedException;
	
	/**
	 * method called when it recieves a move
	 *\throws InterruptedException
	 */
	public abstract boolean sendMove() throws InterruptedException;
	
	/**
	 * Method used to gather the player data
	 * \return playerData returns the player name,colour and turn
	 */
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
