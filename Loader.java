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
                                m_allValid = false;
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
    
    public Boolean loadPlayer1() {
        if (loadPlayer(getPlayer1File())) {
            setPlayer1(createPlayerType(m_playerInfo[PLAYER_TYPE_INDEX]));
            
            getPlayer1().setPlayerName(m_playerInfo[PLAYER_NAME_INDEX]);
            getPlayer1().setPlayerColour(new Color(Integer.parseInt(
                                            m_playerInfo[PLAYER_COLOR_INDEX])));
            getPlayer1().setYourTurn(Boolean.parseBoolean(
                                              m_playerInfo[PLAYER_TURN_INDEX]));

            
            if (Boolean.parseBoolean(m_playerInfo[PLAYER_TURN_INDEX])) {
                getGame().setPlayerTurn(Game.PlayerTurn.PLAYER1);
            }
        } else {
            m_allValid = false;
            System.err.println("Error with player 1");
        }
        
        return true;
    }
    
    public Boolean loadPlayer2() {
        if (loadPlayer(getPlayer2File())) {
            setPlayer2(createPlayerType(m_playerInfo[PLAYER_TYPE_INDEX]));
            
            getPlayer2().setPlayerName(m_playerInfo[PLAYER_NAME_INDEX]);
            getPlayer2().setPlayerColour(new Color(Integer.parseInt(
                                            m_playerInfo[PLAYER_COLOR_INDEX])));
            getPlayer2().setYourTurn(Boolean.parseBoolean(
                                              m_playerInfo[PLAYER_TURN_INDEX]));

            
            if (Boolean.parseBoolean(m_playerInfo[PLAYER_TURN_INDEX])) {
                getGame().setPlayerTurn(Game.PlayerTurn.PLAYER2);
            }
        } else {
            m_allValid = false;
            System.err.println("Error with player 2");
        }
        
        return true;
    }
    
    public Player createPlayerType(String type) {
        switch (m_playerInfo[PLAYER_TYPE_INDEX]) {
            case HUMAN_PLAYER: return new Human(getGame());
            case EASY_AI_PLAYER: return new AIEasy(getGame());
            case HARD_OTHELLO_PLAYER: return new OthelloAI(getGame());
            case HARD_CONNECTFOUR_PLAYER: return new ConnectFourAI(getGame());
        }
        return new Human(getGame());
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
    
    public boolean loadTimer() {        
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
            setTimer(new Timer(getGame()));
            getTimer().setHours(m_timerInfo[HOURS]);
            getTimer().setMinutes(m_timerInfo[MINUTES]);
            getTimer().setSeconds(m_timerInfo[SECONDS]);
        } else {
            m_allValid = false;
        }

        return true;
    }
    
    public boolean loadAll() {
        loadGrid();
        loadPlayer1();
        loadPlayer2();
        loadTimer();
        
        return true;
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
    private final String EASY_AI_PLAYER = "AIEasy"; //will need to change
    private final String HARD_OTHELLO_PLAYER = "OthelloAI";
    private final String HARD_CONNECTFOUR_PLAYER = "ConnectFourAI";
    private final int TIMER_INFO_SIZE = 3;
    private final int HOURS = 0;
    private final int MINUTES = 1;
    private final int SECONDS = 2;
}
