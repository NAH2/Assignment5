import java.awt.Color;
import java.util.Scanner;

public class Loader extends FileManager{
    
    public boolean loadGrid() {
        m_hashString = "";
        initialiseGrid();
        m_hash = 0;
        m_valid = false;
        
        try {
            setFile(getGridFile());
            m_in = new Scanner(this.getFile()); // may throw exception
            m_in.useDelimiter(",");
            //try reading file
            try {
                //read the file
                for (int y = 0; y < getGridObj().getGridHeight(); ++y) {
                    for (int x = 0; x < getGridObj().getGridWidth(); ++x) {                        
                        if (m_in.hasNext()) {
                            String player = m_in.next();
                            
                            m_hashString += player + ",";
                            
                            if (player.equals(PLAYER_ONE)) {                                
                                setGridArray(x,y,Game.PlayerTurn.PLAYER1);
                            } else if (player.equals(PLAYER_TWO)) {                                
                                setGridArray(x,y,Game.PlayerTurn.PLAYER2);
                            } else if (player.equals(NONE)) {                                
                                setGridArray(x,y,Game.PlayerTurn.NONE);
                            } else if (player.equals(PLAYER1_AM)) {                                
                                setGridArray(x,y,Game.PlayerTurn.PLAYER1_AM);
                            } else if (player.equals(PLAYER2_AM)) {                                
                                setGridArray(x,y,Game.PlayerTurn.PLAYER2_AM);
                            } else {
                                System.out.println(INVALID);
                            }
                        }
                    }
                }
                
                m_hash = m_hashString.hashCode();
                m_in.reset();
                
                if (m_in.hasNext()) {
                    String b = m_in.next();
                    int readHash = 0;
                    readHash = Integer.parseInt(b.substring(1));
                    m_valid = (m_hash == readHash);
                }
            }finally {
                m_in.close();
            }                 
        } catch (Exception exc) {
            System.err.println("Error Reading File");
        }
        
        
        if (m_valid) {
            return true;
        } else {
            System.err.println("File Corrupt");
            m_allValid = false;
            return false;
        }
    }
    
    public Player loadPlayer1(Player player1) {
        if (loadPlayer(getPlayer1File())) {
            
            player1 = createPlayerType(m_playerInfo[PLAYER_TYPE_INDEX],player1);
            
            player1.setPlayerName(m_playerInfo[PLAYER_NAME_INDEX]);
            player1.setPlayerColour(new Color(Integer.parseInt(
                                            m_playerInfo[PLAYER_COLOR_INDEX])));
            player1.setYourTurn(Boolean.parseBoolean(
                                              m_playerInfo[PLAYER_TURN_INDEX]));
            System.out.println("Player 1 Colour: " + player1.getPlayerColour());
            
            if (Boolean.parseBoolean(m_playerInfo[PLAYER_TURN_INDEX])) {
                getGame().setPlayerTurn(Game.PlayerTurn.PLAYER1);
            }
        } else {
            m_allValid = false;
            System.err.println("Error with player 1");
        }
        
        return player1;
    }
    
    public Player loadPlayer2(Player player2) {
        if (loadPlayer(getPlayer2File())) {
            player2 = createPlayerType(m_playerInfo[PLAYER_TYPE_INDEX],player2);
            
            player2.setPlayerName(m_playerInfo[PLAYER_NAME_INDEX]);
            player2.setPlayerColour(new Color(Integer.parseInt(
                                            m_playerInfo[PLAYER_COLOR_INDEX])));
            player2.setYourTurn(Boolean.parseBoolean(
                                              m_playerInfo[PLAYER_TURN_INDEX]));
            System.out.println("Player 2 Colour: " + player2.getPlayerColour());

            
            if (Boolean.parseBoolean(m_playerInfo[PLAYER_TURN_INDEX])) {
                getGame().setPlayerTurn(Game.PlayerTurn.PLAYER2);
            }
        } else {
            m_allValid = false;
            System.err.println("Error with player 1");
        }
        
        return player2;
    }
    
    public Player createPlayerType(String type, Player player) {
        switch (m_playerInfo[PLAYER_TYPE_INDEX]) {
            case HUMAN_PLAYER: player = new Human(getGame());
                break;
            case EASY_AI_PLAYER: player = new AIEasy(getGame());
                break;
            case HARD_OTHELLO_PLAYER: player = new OthelloAI(getGame());
            break;
        }
        
        
        return player;
    }
    
    public Boolean loadPlayer(String file) {
        m_valid = false;
        m_playerInfo = new String[PLAYER_INFO_SIZE];
        m_hashString = "";
        
        try {
            setFile(file);
            m_in = new Scanner(this.getFile()); // may throw exception
            m_in.useDelimiter(",");
            //try reading file
            try {
                //read the file
                for (int x = 0; x < m_playerInfo.length; ++x) {
                    if (m_in.hasNext()) {
                        m_playerInfo[x] = m_in.next();
                        m_hashString += m_playerInfo[x] + ",";
                    }
                }
                m_hash = m_hashString.hashCode();
                m_in.reset();
                
                if (m_in.hasNext()) {
                    String b = m_in.next();
                    int readHash = 0;
                    readHash = Integer.parseInt(b.substring(1));
                    m_valid = (m_hash == readHash);
                }
            }finally {
                m_in.close();
            }                 
        } catch (Exception exc) {
            System.err.println("Error Reading File");
        }
        return m_valid;
    }
    
    public Timer loadTimer(Timer timer) {        
        m_valid = false;
        m_hashString = "";
        m_timerInfo = new int[TIMER_INFO_SIZE];
        
        try {
            setFile(getTimerFile());
            m_in = new Scanner(this.getFile()); // may throw exception
            m_in.useDelimiter(",");
            //try reading file
            try {
                //read the file
                for (int x = 0; x < m_timerInfo.length; ++x) {
                    if (m_in.hasNext()) {
                        m_timerInfo[x] = Integer.parseInt(m_in.next());
                        m_hashString += m_timerInfo[x] + ",";
                    }
                }
                m_hash = m_hashString.hashCode();
                m_in.reset();
                
                if (m_in.hasNext()) {
                    String b = m_in.next();
                    int readHash = 0;
                    readHash = Integer.parseInt(b.substring(1));
                    m_valid = (m_hash == readHash);
                }
            }finally {
                m_in.close();
            }                 
        } catch (Exception exc) {
            System.err.println("Error Reading File");
        }
        if(m_valid) {
            timer = new Timer(getGame());
            timer.setHours(m_timerInfo[HOURS]);
            timer.setMinutes(m_timerInfo[MINUTES]);
            timer.setSeconds(m_timerInfo[SECONDS]);
        } else {
            m_allValid = false;
        }

        return timer;
    }
    
    public Boolean getValid() {
        return m_allValid;
    }
    
    private String m_hashString;
    private int m_hash;
    private boolean m_valid;
    private boolean m_allValid = true;
    private Scanner m_in;
    private String[] m_playerInfo;
    private int[] m_timerInfo;
    private final String PLAYER_ONE = "PLAYER1";
    private final String PLAYER_TWO = "PLAYER2";
    private final String NONE = "NONE";
    private final String PLAYER1_AM = "PLAYER1_AM";
    private final String PLAYER2_AM = "PLAYER2_AM";
    private final String INVALID = "Invalid";
    private final int PLAYER_INFO_SIZE = 4;
    private final int PLAYER_TYPE_INDEX = 0;
    private final int PLAYER_NAME_INDEX = 1;
    private final int PLAYER_COLOR_INDEX = 2;
    private final int PLAYER_TURN_INDEX = 3;
    private final String HUMAN_PLAYER = "Human";
    private final String EASY_AI_PLAYER = "AIEasy";
    private final String HARD_OTHELLO_PLAYER = "OthelloAI";
    private final int TIMER_INFO_SIZE = 3;
    private final int HOURS = 0;
    private final int MINUTES = 1;
    private final int SECONDS = 2;
}
