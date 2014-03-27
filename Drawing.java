import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
//******************
import java.util.ArrayList;

import javax.swing.JSlider;

import java.awt.GridLayout;
import java.awt.Dimension;

import javax.swing.event.*;
import javax.swing.JComboBox;

import java.awt.event.*;

//******************
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * \author 708830
 * \date 26 Feb 2014
 * \file Drawing.java
 * 
 * 
 * \brief Creates and updates the side bar.
 * 
 * This class creates the side bar for the game which includes information
 * such as player name, score and piece colour. This is also updated as the 
 * game continues.
 * 
 */
public class Drawing {

	/**
	 * Method to set the connect4 game board
	 * \param the board name
	 */
	private void setBoard(String board){
		gameBoardGraphics.SetBoard(board);
	}
	/**
	 * Method to set the animation speed
	 * \param the animation speed ,an integer, represents millisecond time delay per movement
	 */
	private void setSpeed(int speed){
		gameBoardGraphics.SetSpeed(speed);
		m_responseTime = BASETIME + speed*TIME_RATIO;
		if(gameBoardGraphics.GetPlayer1() instanceof OthelloAI){
			((OthelloAI)gameBoardGraphics.GetPlayer1()).SetTime(m_responseTime);
		} else if(gameBoardGraphics.GetPlayer2() instanceof OthelloAI){		
			((OthelloAI)gameBoardGraphics.GetPlayer2()).SetTime(m_responseTime);
		} else if(gameBoardGraphics.GetPlayer1() instanceof ConnectFourAI){
			((ConnectFourAI)gameBoardGraphics.GetPlayer1()).SetTime(m_responseTime);
		} else if(gameBoardGraphics.GetPlayer2() instanceof ConnectFourAI){
			((ConnectFourAI)gameBoardGraphics.GetPlayer2()).SetTime(m_responseTime);
		} else if(gameBoardGraphics.GetPlayer1() instanceof AIEasy){
			((AIEasy)gameBoardGraphics.GetPlayer1()).SetTime(m_responseTime);
		} else if(gameBoardGraphics.GetPlayer2() instanceof AIEasy){
			((AIEasy)gameBoardGraphics.GetPlayer2()).SetTime(m_responseTime);
		}
		
	}
	//******************************
	/**
	* Method responsible for setting AI move to GameBoardGraphics class
	* \param move - coordinate of AI move
	*/
	public void SetAImove(Coordinate move){
		gameBoardGraphics.SetAImove(move);
	}
	
	/**
	* Method responsible for passing animation data to GameBoardGraphics class
	* \param type - type of animation that is either flip or fall
	* \param changes - the list stores the pieces which need the animation
	*/
	public void SetAnimation(String type, ArrayList<Coordinate> changes){
		gameBoardGraphics.SetAnimation(type, changes);
	}
	
	/**
	 * Method to set the game is not over after the game restarts
	 * \param isOver - the boolean true means the game is over
	 */
	public void SetOver(boolean isOver){
		gameBoardGraphics.SetOver(isOver);
	}
	
	//******************************
	
	/**
	 * This method sets the player 1 score on the side bar.
	 * \param Score This hold the score for player 1 in an integer form.
	 */
	public void setPlayer1Score(int score){
		m_player1Score.setText("" + score);	
	}
	
	/**
	 * This method sets the player 2 score on the side bar.
	 * \param Score This hold the score for player 2 in an integer form.
	 */
	public void setPlayer2Score(int score){
		m_player2Score.setText("" + score);	
	}
	
	/**
	* This method returns the sidebar panel for player 1.
	* \return This method retrives the score from the game for player 1
	*/
	public JLabel getPlayer1Score() {
		return m_player1Score;
	}
	
	/**
	* This method returns the sidebar panel for player 2.
	* \return This method retrives the score from the game for player 1
	*/
	public JLabel getPlayer2Score() {
		return m_player2Score;
	}
	
	/**
	* This method sets the name of player 1.
	* \param label This stores the name of player 1
	*/
	public void setPlayer1Name(JLabel label) {
		m_player1Name = label;
	}
	
	/**
	* This method sets the name of player 2.
	* \param label This stores the name of player 2
	*/
	public void setPlayer2Name(JLabel label) {
		m_player2Name = label;
	}
	
	/**
	* This method returns the name of player 1.
	* \return m_player1Name This returns the name of player 1 when called
	*/
	public JLabel getPlayer1Name() {
		return m_player1Name;
	}
	
	/**
	* This method returns the name of player 2.
	* \return m_player2Name This returns the name of player 2 when called
	*/
	public JLabel getPlayer2Name() {
		return m_player2Name;
	}
	
	/**
	* This method takes in information about the number of turns taken and
	* stores it.
	* \param label This stores the information on the amount of turns taken
	*/
	public void setTurnsTaken(JLabel label) {
		m_turnsTaken = label;
	}
	
	/**
	* This method returns the number of turns that has been made.
	* \return m_turnsTaken This returns the amount of turns taken in the game
	*/
	public JLabel getTurnsTaken() {
		return m_turnsTaken;
	}
	
	/**
	* This method assigns the information on the colour of the piece chosen by
	* player 1.
	* \param label This stores the information on the piece colour of player 1
	*/
	public void setPlayer1Piece(JLabel label) {
		m_player1Piece = label;
	}
	
	/**
	* This method assigns the information on the colour of the piece chosen by
	* player 2.
	* \param label This stores the information on the piece colour of player 2
	*/
	public void setPlayer2Piece(JLabel label) {
		m_player2Piece = label;
	}
	
	/**
	* This returns the colour of the piece chosen by player 1.
	* \return m_player1Piece This returns the colour of the player 1 piece
	*/
	public JLabel getPlayer1Piece() {
		return m_player1Piece;
	}
	
	/**
	* This returns the colour of the piece chosen by player 2.
	* \return m_player2Piece This returns the colour of the player 2 piece
	*/
	public JLabel getPlayer2Piece() {
		return m_player2Piece;
	}

	/**
	* This method sets the the player 1 side bar.
	* \param panel Instantiates m_barPlayer1 with JPanel
	*/
	public void setBarPlayer1(JPanel panel) {
		m_barPlayer1 = panel;
	}
	
	/**
	* This method sets the the player 2 side bar.
	* \param panel Instantiates m_barPlayer2 with JPanel
	*/
	public void setBarPlayer2(JPanel panel) {
		m_barPlayer2 = panel;
	}
	
	/**
	* This method returns the relevant iformation on the player 1
	* game panel.
	* \return m_barPlayer1 This returns the player 1 game info panel
	*/
	public JPanel getBarPlayer1() {
		return m_barPlayer1;
	}
	
	/**
	* This method returns the relevant iformation on the player 2
	* game panel.
	* \return m_barPlayer2 This returns the player 2 game info panel
	*/
	public JPanel getBarPlayer2() {
		return m_barPlayer2;
	}
	
	/**
	 * This method returns the graphics that were created.
	 * \return Returns the GameBoardGrpahics when it is called.
	 */
	public GameBoardGraphics getGridPanel() {
		return gameBoardGraphics;
	}
	
	/**
	 * This method returns the side bar that was created.
	 * \return Returns the side bar which holds the scores for the game.
	 */
	public JPanel getSideBarPanel() {
		return m_sideBar;
	}
	
	/**
	 * This method sets all relevent information for Player 1
	 * \param Player1 This is an object which holds data for player 1.
	 */
	public void setPlayer1(Player Player1){
		boolean m_Trace = false;
		
		getPlayer1Name().setText(Player1.getPlayerName());
		m_p1piece = new BufferedImage
				(OVALSIZE, OVALSIZE, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g2 = m_p1piece.createGraphics();
		m_p1colour = Player1.getPlayerColour();
		if(m_p1colour.equals(Color.BLACK)){
			g2.setColor(Color.WHITE);
			g2.fillRect(0, 0, OVALSIZE, OVALSIZE);
		}
		g2.setColor(m_p1colour);
		g2.fillOval(0, 0, OVALSIZE, OVALSIZE);
		if(m_Trace) System.out.println
		("Drawing::SetPlayer1() - colour is " + Player1.getPlayerColour());
		getPlayer1Piece().setIcon(new ImageIcon(m_p1piece));
	}
	
	/**
	 * This method sets all relevent information for Player 2
	 * \param Player2 This is an object which holds data for player 2.
	 */
	public void setPlayer2(Player Player2){
		boolean m_Trace = false;
		
		getPlayer2Name().setText(Player2.getPlayerName());
		m_p2piece = new BufferedImage
				(OVALSIZE, OVALSIZE, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g2 = m_p2piece.createGraphics();
		m_p2colour = Player2.getPlayerColour();
		if(m_p2colour.equals(Color.BLACK)){
			g2.setColor(Color.WHITE);
			g2.fillRect(0, 0, OVALSIZE, OVALSIZE);
		}
		g2.setColor(m_p2colour);
		g2.fillOval(0, 0, OVALSIZE, OVALSIZE);
		if(m_Trace) System.out.println
		("Drawing::SetPlayer2() - colour is " + Player2.getPlayerColour());
		getPlayer2Piece().setIcon(new ImageIcon(m_p2piece));
	}
	
	/**
	 * This method sets the players turns, and highlights the in turn player.
	 * \param PlayerTurn This holds information on the turns that have been
	 * taken.
	 * \param isAI indicates if the turn is an AI turn
	 */
	public void setPlayerTurn(Game.PlayerTurn PlayerTurn, boolean isAI){
		
		if(PlayerTurn == Game.PlayerTurn.PLAYER1){
			getBarPlayer1().setBackground(Color.GRAY);
					
			BufferedImage Piece = new BufferedImage
					(OVALSIZE, OVALSIZE, BufferedImage.TYPE_3BYTE_BGR);
			Graphics2D g2 = Piece.createGraphics();
			if(m_p1colour.equals(Color.BLACK)){
				g2.setColor(Color.WHITE);
				g2.fillRect(0, 0, OVALSIZE, OVALSIZE);
			}
			g2.setColor(m_p1colour);
			g2.fillOval(0, 0, OVALSIZE, OVALSIZE);
			g2.setColor(m_p2colour);
			if(isAI){
				g2.drawString("AI", AI_X, YOUR_Y);
			} else {
				g2.drawString("YOUR", X, YOUR_Y);
			}
			g2.drawString("TURN", X, TURN_Y);
			getPlayer1Piece().setIcon(new ImageIcon(Piece));
			getPlayer2Piece().setIcon(new ImageIcon(m_p2piece));			
			
			getBarPlayer2().setBackground(Color.WHITE);
		} else {
			getBarPlayer2().setBackground(Color.GRAY);
			
			BufferedImage Piece = new BufferedImage
					(OVALSIZE, OVALSIZE, BufferedImage.TYPE_3BYTE_BGR);
			Graphics2D g2 = Piece.createGraphics();
			if(m_p2colour.equals(Color.BLACK)){
				g2.setColor(Color.WHITE);
				g2.fillRect(0, 0, OVALSIZE, OVALSIZE);
			}
			g2.setColor(m_p2colour);
			g2.fillOval(0, 0, OVALSIZE, OVALSIZE);
			g2.setColor(m_p1colour);
			if(isAI){
				g2.drawString("AI", AI_X, YOUR_Y);
			} else {
				g2.drawString("YOUR", X, YOUR_Y);
			}
			g2.drawString("TURN", X, TURN_Y);
			getPlayer2Piece().setIcon(new ImageIcon(Piece));
			getPlayer1Piece().setIcon(new ImageIcon(m_p1piece));
			
			getBarPlayer1().setBackground(Color.WHITE);
		}
	}
	
	/**
	 * This method sets the grid.
	 * \param grid  Holds information on the grid,.
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
	
	public boolean setTimerDisplay(String time) {
	    m_timerLabel.setText(time);
          
        return true;
    }
	
	/**
	 * \param game This receives an instance of the Game class.
	 * \param handler This receives the GUIEventHandler for the combobox and slider
	 * This method creates layout of the sidebar
	 */
	private boolean setLayout(Game game, GUIEventHandler handler){
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(PADDING,PADDING,PADDING,PADDING);
		GridBagLayout layout = new GridBagLayout();
		GridBagLayout layoutP1 = new GridBagLayout();
		GridBagLayout layoutP2 = new GridBagLayout();
		GridBagConstraints pc1 = new GridBagConstraints();
		GridBagConstraints pc2 = new GridBagConstraints();
		GridLayout setting = new GridLayout(SPEED_ROW,1);
		
		m_sideBar = new JPanel(layout);
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
	    m_sideBar.add(getBarPlayer1(),c);	    
	    c.gridy = BAR2GRIDY;
	    m_sideBar.add(getBarPlayer2(),c);
	
	    m_speed.setText("Game speed");
	    if(game instanceof ConnectFour){	
	    	gameBoardGraphics.SetSpeed(DEFAULT_FALL_SPEED);
			m_slider = new JSlider (MINSPEED, MAX_FALL_SPEED, DEFAULT_FALL_SPEED);
	    } else {
	    	gameBoardGraphics.SetSpeed(DEFAULT_FLIP_SPEED);
	    	m_slider = new JSlider (MINSPEED, MAX_FLIP_SPEED, DEFAULT_FLIP_SPEED);
	    }
	    
	    m_d = m_slider.getPreferredSize();
	    m_d.width = SLIDER_WIDTH;
	    m_slider.setPreferredSize(m_d);
		m_slider.addChangeListener(handler);
		m_slider.setInverted(true);
	    
	    m_setting.setLayout(setting);
		m_setting.add(m_speed);
		m_setting.add(m_slider);
		m_setting.setBackground(Color.WHITE);
		c.gridy = BAR3GRIDY;
		m_sideBar.add(m_setting,c);
		
		String m_boards[] = new String[]{"board1","board2","board3"};
		m_skin = new JComboBox<String>(m_boards);
		m_skin.setSelectedIndex(0);
		m_skin.addActionListener(handler);
		
		c.gridy = BAR3GRIDY + 1;
		m_sideBar.add(m_skin,c);
		
		c.gridy = BAR3GRIDY + GRID2;
        m_sideBar.add(m_timerLabel,c);
        return true;
	}
	
	/**
	 * \param game This creates an instance of the Game class.
	 * This method creates the panel and layout for the game to be played
	 * on, and adds the relevant information.
	 */
	public Drawing(Game game) {
		boolean m_Trace = false;
		
		if(m_Trace) { System.out.println
			("Drawing::Drawing() - drawing initalizing");}
		
		if(m_Trace) { System.out.println
			("Drawing::Drawing() - Grid size = " + 
				game.getGrid().getGridWidth() + "X" + 
					game.getGrid().getGridHeight());}
		if(m_Trace) { System.out.println("Drawing::Drawing() - Players = " +
			game.getPlayer2().getPlayerName() + ":" + 
				game.getPlayer1().getPlayerName());}
		try {
			gameBoardGraphics = new GameBoardGraphics
				(game.getGrid(), game.getPlayer1(), game.getPlayer2()); 
		}catch(java.io.IOException e){} 
		
		setPlayer1(game.getPlayer1());
		setPlayer2(game.getPlayer2());
		
		handler = new GUIEventHandler();
		setLayout(game, handler);
	}
	
	
	
	private class GUIEventHandler implements ActionListener, ChangeListener {
		
		//Change handler (e.g. for sliders)
        public void stateChanged(ChangeEvent e) {
        	setSpeed(m_slider.getValue());
        }
        public void actionPerformed(ActionEvent event) {
        	String m_board = (String)m_skin.getSelectedItem();
        	setBoard(m_board);
        }
    }
	

	public void Message(String msg) {
		JOptionPane.showMessageDialog(null,
			     msg,
			    "Information",
			    JOptionPane.INFORMATION_MESSAGE);
	}
	/**
	 * This is the main method. It is used for testing purposes
	 */
	public static void main(String args[]) {
		ConnectFour game = new ConnectFour();
		//Othello game = new Othello();
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
		drawing.setPlayerTurn(Game.PlayerTurn.PLAYER1, false);
		frame.pack();
	}
	
	

	GameBoardGraphics gameBoardGraphics;
	private JPanel m_sideBar;
	private JLabel m_player1Name = new JLabel();
	private JLabel m_player1Score = new JLabel("" + 0);
	private JLabel m_player2Name = new JLabel();
	private JLabel m_player2Score = new JLabel("" + 0);
	private JLabel m_turnsTaken = new JLabel();
	
	private JLabel m_player1Piece = new JLabel();
	private JLabel m_player2Piece = new JLabel();

	private JPanel m_barPlayer1 = new JPanel();
	private JPanel m_barPlayer2 = new JPanel();
	//******************************
	private JPanel m_setting = new JPanel();
	private JLabel m_speed = new JLabel("Animation speed", JLabel.CENTER);
	private JSlider m_slider;
	private Dimension m_d;
	private final int PADDING = 6;
	private final int MINSPEED = 1;
	private final int MAX_FALL_SPEED = 40;
	private final int DEFAULT_FALL_SPEED = 20;
	private final int MAX_FLIP_SPEED = 20;
	private final int DEFAULT_FLIP_SPEED = 10;
	private final int SLIDER_WIDTH = 80;
	private final GUIEventHandler handler;
	private JComboBox m_skin;
	private final int SPEED_ROW = 2;
	private Color m_p1colour;
	private Color m_p2colour;
	private BufferedImage m_p1piece;
	private BufferedImage m_p2piece;
	private JLabel m_timerLabel = new JLabel("00:00:00");
	private int m_responseTime;
	private final int BASETIME = 1000;
	private final int TIME_RATIO = 50;
	private final int OVALSIZE = 60;
	private final int X = 14;
	private final int AI_X = 24;
	private final int YOUR_Y = 29;
	private final int TURN_Y = 39;
	private final int P1SCOREY = 1;
	private final int P1PIECEY = 2;
	private final int P2SCOREY = 1;
	private final int P2PIECEY = 2;
	private final int BAR2GRIDY = 1;
	private final int BAR3GRIDY = 2;
	private final int GRID2 = 2;
}
