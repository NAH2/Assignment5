import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @brief Creates and updates the side bar.
 * 
 * This class creates the side bar for the game which includes information
 * such as player name, score and piece colour. This is also updated as the 
 * game continues.
 * 
 * @author 708830
 * @date 26 Feb 2014
 * @file Drawing.java
 */
public class Drawing {

	JPanel SideBar;

	GameBoardGraphics gameBoardGraphics;
	
	private JLabel m_player1Name = new JLabel();
	private JLabel m_player1Score = new JLabel("" + 0);
	private JLabel m_player2Name = new JLabel();
	private JLabel m_player2Score = new JLabel("" + 0);
	private JLabel m_turnsTaken = new JLabel();
	
	private JLabel m_player1Piece = new JLabel();
	private JLabel m_player2Piece = new JLabel();

	private JPanel m_barPlayer1 = new JPanel();
	private JPanel m_barPlayer2 = new JPanel();
		
	/**
	 * This method sets the player 1 score on the side bar.
	 * @param Score This hold the score for player 1 in an integer form.
	 */
	public void setPlayer1Score(int score){
		m_player1Score.setText("" + score);	
	}
	
	/**
	 * This method sets the player 2 score on the side bar.
	 * @param Score This hold the score for player 2 in an integer form.
	 */
	public void setPlayer2Score(int score){
		m_player2Score.setText("" + score);	
	}
	
	/**
	* This method returns the sidebar panel for player 1.
	* @return This method retrives the score from the game for player 1
	*/
	public JLabel getPlayer1Score() {
		return m_player1Score;
	}
	
	/**
	* This method returns the sidebar panel for player 2.
	* @return This method retrives the score from the game for player 1
	*/
	public JLabel getPlayer2Score() {
		return m_player2Score;
	}
	
	/**
	* This method sets the name of player 1.
	* @param label This stores the name of player 1
	*/
	public void setPlayer1Name(JLabel label) {
		m_player1Name = label;
	}
	
	/**
	* This method sets the name of player 2.
	* @param label This stores the name of player 2
	*/
	public void setPlayer2Name(JLabel label) {
		m_player2Name = label;
	}
	
	/**
	* This method returns the name of player 1.
	* @return m_player1Name This returns the name of player 1 when called
	*/
	public JLabel getPlayer1Name() {
		return m_player1Name;
	}
	
	/**
	* This method returns the name of player 2.
	* @return m_player2Name This returns the name of player 2 when called
	*/
	public JLabel getPlayer2Name() {
		return m_player2Name;
	}
	
	/**
	* This method takes in information about the number of turns taken and
	* stores it.
	* @param label This stores the information on the amount of turns taken
	*/
	public void setTurnsTaken(JLabel label) {
		m_turnsTaken = label;
	}
	
	/**
	* This method returns the number of turns that has been made.
	* @return m_turnsTaken This returns the amount of turns taken in the game
	*/
	public JLabel getTurnsTaken() {
		return m_turnsTaken;
	}
	
	/**
	* This method assigns the information on the colour of the piece chosen by
	* player 1.
	* @param label This stores the information on the piece colour of player 1
	*/
	public void setPlayer1Piece(JLabel label) {
		m_player1Piece = label;
	}
	
	/**
	* This method assigns the information on the colour of the piece chosen by
	* player 2.
	* @param label This stores the information on the piece colour of player 2
	*/
	public void setPlayer2Piece(JLabel label) {
		m_player2Piece = label;
	}
	
	/**
	* This returns the colour of the piece chosen by player 1.
	* @return m_player1Piece This returns the colour of the player 1 piece
	*/
	public JLabel getPlayer1Piece() {
		return m_player1Piece;
	}
	
	/**
	* This returns the colour of the piece chosen by player 2.
	* @return m_player2Piece This returns the colour of the player 2 piece
	*/
	public JLabel getPlayer2Piece() {
		return m_player2Piece;
	}

	/**
	* This method sets the the player 1 side bar.
	* @param panel Instantiates m_barPlayer1 with JPanel
	*/
	public void setBarPlayer1(JPanel panel) {
		m_barPlayer1 = panel;
	}
	
	/**
	* This method sets the the player 2 side bar.
	* @param panel Instantiates m_barPlayer2 with JPanel
	*/
	public void setBarPlayer2(JPanel panel) {
		m_barPlayer2 = panel;
	}
	
	/**
	* This method returns the relevant iformation on the player 1
	* game panel.
	* @return m_barPlayer1 This returns the player 1 game info panel
	*/
	public JPanel getBarPlayer1() {
		return m_barPlayer1;
	}
	
	/**
	* This method returns the relevant iformation on the player 2
	* game panel.
	* @return m_barPlayer2 This returns the player 2 game info panel
	*/
	public JPanel getBarPlayer2() {
		return m_barPlayer2;
	}
	
	/**
	 * This method returns the graphics that were created.
	 * @return Returns the GameBoardGrpahics when it is called.
	 */
	public GameBoardGraphics getGridPanel() {
		return gameBoardGraphics;
	}
	
	/**
	 * This method returns the side bar that was created.
	 * @return Returns the side bar which holds the scores for the game.
	 */
	public JPanel getSideBarPanel() {
		return SideBar;
	}
	
	/**
	 * This method sets all relevent information for Player 1
	 * @param Player1 This is an object which holds data for player 1.
	 */
	public void setPlayer1(Player Player1){
		boolean m_Trace = false;
		final int OVALSIZE = 60;
		
		getPlayer1Name().setText(Player1.getPlayerName());
		BufferedImage Piece = new BufferedImage
				(OVALSIZE, OVALSIZE, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g2 = Piece.createGraphics();
		g2.setColor(Player1.getPlayerColour());
		g2.fillOval(0, 0, OVALSIZE, OVALSIZE);
		if(m_Trace) System.out.println
		("Drawing::SetPlayer1() - colour is " + Player1.getPlayerColour());
		getPlayer1Piece().setIcon(new ImageIcon(Piece));
	}
	
	/**
	 * This method sets all relevent information for Player 2
	 * @param Player2 This is an object which holds data for player 2.
	 */
	public void setPlayer2(Player Player2){
		boolean m_Trace = false;
		
		final int OVALSIZE = 60;
		
		getPlayer2Name().setText(Player2.getPlayerName());
		BufferedImage Piece = new BufferedImage
				(OVALSIZE, OVALSIZE, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g2 = Piece.createGraphics();
		g2.setColor(Player2.getPlayerColour());
		g2.fillOval(0, 0, OVALSIZE, OVALSIZE);
		if(m_Trace) System.out.println
		("Drawing::SetPlayer2() - colour is " + Player2.getPlayerColour());
		getPlayer2Piece().setIcon(new ImageIcon(Piece));
	}
	
	/**
	 * This method sets the players turns, and highlights the in turn player.
	 * @param PlayerTurn This holds information on the turns that have been
	 * taken.
	 */
	public void setPlayerTurn(Game.PlayerTurn PlayerTurn){
		if(PlayerTurn == Game.PlayerTurn.PLAYER1){
			getBarPlayer1().setBackground(Color.GRAY);
			getBarPlayer2().setBackground(Color.WHITE);
		} else {
			getBarPlayer2().setBackground(Color.GRAY);
			getBarPlayer1().setBackground(Color.WHITE);
		}
	}
	
	/**
	 * This method sets the grid.
	 * @param grid  Holds information on the grid,.
	 */
	public void setGrid(Grid grid, Game game){
		boolean m_Trace = false;
		if(game.isOver()){
			System.out.println("paintwin");
			//System.out.println("win is empty?"+game.getWin().isEmpty());
			gameBoardGraphics.setIsOver(true, game.getWin());
		}
		if(m_Trace) System.out.println
		("Drawing::SetGrid() - Grid has been updated");
		getGridPanel().setGrid(grid);
	}
	
	/**
	 * @param game This creates an instance of the Game class.
	 * This method creates the panel and layout for the game to be played
	 * on, and adds the relevant information.
	 */
	public Drawing(Game game) {
		boolean m_Trace = false;
		
		final int P1SCOREY = 1;
		final int P1PIECEY = 2;
		final int P2SCOREY = 1;
		final int P2PIECEY = 2;
		final int BAR2GRIDY = 1;

		if(m_Trace) { System.out.println
			("Drawing::Drawing() - drawing initalizing");}
		
		if(m_Trace) { System.out.println
			("Drawing::Drawing() - Grid size = " + 
				game.getGrid().getGridWidth() + "X" + 
					game.getGrid().getGridHeight());}
		
		if(m_Trace) { System.out.println("Drawing::Drawing() - Players = " +
			game.getPlayer2().getPlayerName() + ":" + 
				game.getPlayer1().getPlayerName());}
		gameBoardGraphics = new GameBoardGraphics
				(game.getGrid(), game.getPlayer1(), game.getPlayer2());
		
		setPlayer1(game.getPlayer1());
		setPlayer2(game.getPlayer2());
		
		GridBagLayout layout = new GridBagLayout();
		GridBagLayout layoutP1 = new GridBagLayout();
		GridBagLayout layoutP2 = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		GridBagConstraints pc1 = new GridBagConstraints();
		GridBagConstraints pc2 = new GridBagConstraints();
		
		SideBar = new JPanel(layout);
		
		JPanel BarTurn = new JPanel();
		
		getBarPlayer1().setLayout(layoutP1);
		getBarPlayer2().setLayout(layoutP2);
		
	    layoutP1.setConstraints(getPlayer1Name(), pc1);
	    getBarPlayer1().add(getPlayer1Name());
	    
	    pc1.gridy = P1SCOREY;
	    layoutP1.setConstraints(getPlayer1Score(), pc1);
	    getBarPlayer1().add(getPlayer1Score());
	    
	    pc1.gridy = P1PIECEY;
	    layoutP1.setConstraints(getPlayer1Piece(), pc1);
	    getBarPlayer1().add(getPlayer1Piece());
	    
	    
	    layoutP2.setConstraints(getPlayer2Name(), pc2);
	    getBarPlayer2().add(getPlayer2Name());
	    
	    pc2.gridy = P2SCOREY;
	    layoutP2.setConstraints(getPlayer2Score(), pc2);
	    getBarPlayer2().add(getPlayer2Score());
	    
	    pc2.gridy = P2PIECEY;
	    layoutP2.setConstraints(getPlayer2Piece(), pc2);
	    getBarPlayer2().add(getPlayer2Piece());
	    
	    BarTurn.add(getTurnsTaken());
	    
	    
	    layout.setConstraints(getBarPlayer1(), c);
	    SideBar.add(getBarPlayer1());
	    
	    c.gridy = BAR2GRIDY;
	    layout.setConstraints(getBarPlayer2(), c);
	    SideBar.add(getBarPlayer2());
	}
	
	/**
	 * This method shows an error if a player makes an invalid move.
	 */
	public void invalidMove() {
		JOptionPane.showMessageDialog(null,
			    "That move is not valid Please try again.",
			    "Invalid Move",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * This is the main method. It is used for testing purposes
	 */
	public static void main(String args[]) {
		ConnectFour game = new ConnectFour();
		Player player1 = new Human(game);
		Player player2 = new Human(game);
		player1.setPlayerName("Gavin");
		player2.setPlayerName("Lucy");
		player1.setPlayerColour(Color.RED);
		player2.setPlayerColour(Color.YELLOW);
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		Drawing drawing = new Drawing(game);
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(drawing.getGridPanel());
		frame.add(drawing.getSideBarPanel());
		drawing.setPlayerTurn(Game.PlayerTurn.PLAYER1);
		frame.pack();
	}
}
