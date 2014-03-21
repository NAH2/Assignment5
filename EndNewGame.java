
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @brief Displays this window when the game ends.
 * 
 * This class is a window in which the winner and scores are shown. 
 * It gives the user the option of player to play again.
 * 
 * @author Lewis Edwards
 * @date 26 Feb 2014
 * @file EndNewGame.java
 */

public class EndNewGame extends JFrame implements ActionListener {
	private final JButton YES_BUTTON = new JButton("Yes");
	private final JButton NO_BUTTON = new JButton("No");
	private Game m_game;
	
	public void setGame(Game game){
		m_game = game;
	}
	
	public Game getGame(){
		return m_game;
	}
	
	/**
	 * This method shows the panel at the end of a game, showing all the
	 * information on the game.
	 * @param game This is an instance of the game class.
	 */
	public EndNewGame(Game game) {
		
		final int YESGRIDX = 0;
		final int YESGRIDY = 0;
		final int YESPADY = 0;
		final double STATP1WEIGHTX = 0.5;
		final int STATP1WIDTH = 1;
		final int STATP1GRIDY = 1;
		final int STATP2GRIDX = 1;
		final int ENDLABELGRIDX = 0;
		final int ENDLABELGRIDY =	2;
		final int BUTTONPANELGRIDY = 3;
		final int BUTTONSIZEX = 100;
		final int BUTTONSIZEY = 30;
		
		
		setGame(game);
		JLabel endGameLabel = new JLabel("Would you like to play again?");
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel winner = new JLabel();
		if (game.isWinner() == Game.PlayerTurn.PLAYER1) {
			winner.setText(game.getPlayer1().getPlayerName() + " Wins!");
		}
		else if (game.isWinner() == Game.PlayerTurn.PLAYER2) {
			winner.setText(game.getPlayer2().getPlayerName() + " Wins!");
		}
		else {
			winner.setText("The game was a draw.");
		}
		YES_BUTTON.setPreferredSize(new Dimension(BUTTONSIZEX,BUTTONSIZEY));
		NO_BUTTON.setPreferredSize(new Dimension(BUTTONSIZEX,BUTTONSIZEY));
		getContentPane().setLayout(layout);
		
		c.gridy = YESGRIDY;
		c.gridx = YESGRIDX;
	    c.gridwidth = GridBagConstraints.REMAINDER;
		c.ipady = YESPADY;
		layout.setConstraints(winner, c);
		getContentPane().add(winner);
		
		
		JPanel stats1 = statsPlayer1(game);
		c.weightx = STATP1WEIGHTX;
		c.gridwidth = STATP1WIDTH;
		c.gridy = STATP1GRIDY;
		layout.setConstraints(stats1, c);
		getContentPane().add(stats1);
		
		JPanel stats2 = statsPlayer2(game);
		c.gridx = STATP2GRIDX;
		layout.setConstraints(stats2, c);
		getContentPane().add(stats2);

		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridx = ENDLABELGRIDX;
		c.gridy = ENDLABELGRIDY;
		layout.setConstraints(endGameLabel, c);
	    getContentPane().add(endGameLabel);
	    
	    JPanel buttonPanel = new JPanel();
	    buttonPanel.add(YES_BUTTON);
	    buttonPanel.add(NO_BUTTON);
	    
	    c.gridy = BUTTONPANELGRIDY;
	    layout.setConstraints(buttonPanel, c);
	    getContentPane().add(buttonPanel);
	    
	    YES_BUTTON.addActionListener(this);
	    NO_BUTTON.addActionListener(this);
	    setVisible(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    pack();
	    setLocationRelativeTo(null);
	}

	/**
	 * This method creates a new panel which hold the information on player 1.
	 * This includes the name, score, turns taken and the colour of piece they
	 * used.
	 * @param game This is an instance of the Game class.
	 * @return Returns the frame for the statistics of player 1.
	 */
	private JPanel statsPlayer1(Game game) {
		
		final int P1SCOREGRIDY = 1;
		final int P2SCOREGRIDY = 2;
		final int OVALSIZE = 30;
		
		JPanel stats = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		stats.setLayout(layout);
		
		JLabel player1Name = new JLabel(game.getPlayer1().getPlayerName());
		layout.setConstraints(player1Name, c);
		stats.add(player1Name);

		c.gridy = P1SCOREGRIDY;
		JLabel player1Score = new JLabel("" + game.getPlayer1Score());
		layout.setConstraints(player1Score, c);
		stats.add(player1Score);
		
		c.gridy = P2SCOREGRIDY;
		JLabel player1Piece = new JLabel();
		BufferedImage piece = new BufferedImage
				(OVALSIZE, OVALSIZE, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g2 = piece.createGraphics();
		g2.setColor(game.getPlayer1().getPlayerColour());
		g2.fillOval(0, 0, OVALSIZE, OVALSIZE);
		player1Piece.setIcon(new ImageIcon(piece));
		layout.setConstraints(player1Piece, c);
		stats.add(player1Piece);
		
		return stats;
	}
	/**
	 * This method creates a new panel which hold the information on player 2.
	 * This includes the name, score, turns taken and the colour of piece they
	 * used.
	 * @param game
	 * @return Returns the frame for the statistics of player 1.
	 */
	private JPanel statsPlayer2(Game game) {
		
		final int OVALSIZE = 30;
		final int SCOREPOSITION = 1;
		final int PIECEPOSITION = 2;
		
		JPanel stats = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		stats.setLayout(layout);
		
		JLabel player2Name = new JLabel(game.getPlayer2().getPlayerName());
		layout.setConstraints(player2Name, c);
		stats.add(player2Name);
		
		c.gridy = SCOREPOSITION;
		JLabel player2Score = new JLabel("" + game.getPlayer2Score());
		layout.setConstraints(player2Score, c);
		stats.add(player2Score);
		
		c.gridy = PIECEPOSITION;
		JLabel player2Piece = new JLabel();
		BufferedImage piece2 = new BufferedImage
				(OVALSIZE, OVALSIZE, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g2 = piece2.createGraphics();
		g2.setColor(game.getPlayer2().getPlayerColour());
		g2.fillOval(0, 0, OVALSIZE, OVALSIZE);
		player2Piece.setIcon(new ImageIcon(piece2));
		layout.setConstraints(player2Piece, c);
		stats.add(player2Piece);
		
		return stats;
	}

	
	/**
	 * actionPerformed closes the window if the no button is pressed, 
	 * and the game is reset if yes is pressed.
	 */
	public void actionPerformed(ActionEvent evt) {
	    Object src = evt.getSource();
	    if (src == YES_BUTTON) {
	    	dispose();
	    	getGame().reset();
	    } 
	    else if (src == NO_BUTTON) {
	    	dispose();
	    	getGame().getWindow().dispose();
	    	new ChooseGame();
	    }
	}
}