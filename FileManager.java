import java.io.File;


public class FileManager {    
    
    public String getTimerFile() {
        return TIMER_FILE;
    }
    
    
    public Boolean setGridArray(int x, int y, Game.PlayerTurn pt) {
        m_gridArray[x][y] = pt;
        return true;
    }
    
    public Game.PlayerTurn[][] getGridArray() {
        return m_gridArray;
    }
    
    public Boolean setGame(Game g) {
        m_game = g;
        setGridObj();
        
        return true;
    }
    
    public Game getGame() {
        return m_game;
    }
    
    public boolean setPath(String path) {
        PATH = path;
        
        return true;
    }
    
    public String getPath() {
        return PATH;
    }
    
    public boolean setFile(String file) {
        inFile = new File(this.PATH + file);
        return true;
    }
    
    public File getFile() {
        return inFile;
    }
    
    public String getGridFile() {
        return GRID_FILE;
    }
    
    public String getPlayer1File() {
        return PLAYER1_FILE;
    }
    
    public String getPlayer2File() {
        return PLAYER2_FILE;
    }
    
    public void setGridObj() {
        m_gridObj = m_game.getGrid();
    }
    
    public Grid getGridObj() {
        return m_gridObj;
    }
    
    public boolean setPlayer1(Player p) {
        m_player1 = p;
        
        return true;
    }
    
    public boolean setPlayer2(Player p) {
        m_player2 = p;
        
        return true;
    }
    
    public Player getPlayer1() {
        return m_player1;
    }
    
    public Player getPlayer2() {
        return m_player2;
    }
    
    public boolean setTimer(Timer t) {
        m_timer = t;
        
        return true;
    }
    
    public Timer getTimer() {
        return m_timer;
    }
    
    public Boolean initialiseGrid() {
        m_gridArray = new Game.PlayerTurn[m_gridObj.getGridWidth()][m_gridObj.getGridHeight()];
        
        return true;
    }
    
    private String PATH = "";
    private File inFile;
    private Grid m_gridObj;
    private final String GRID_FILE = "grid.txt";
    private final String PLAYER1_FILE = "player1.txt";
    private final String PLAYER2_FILE = "player2.txt";
    private final String TIMER_FILE = "timer.txt";
    private Game.PlayerTurn[][] m_gridArray;
    private Player m_player1;
    private Player m_player2;
    private Timer m_timer;
    private Game m_game;
}
