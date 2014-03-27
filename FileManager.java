import java.io.File;
/**
 * \\file -FileManager.java 
 * \author - ADDME
 * \date -26th March 14
 * 
 * \brief Class to handle the locations of individual files
 * 
 * This Class controls the paths of the individual save files
 */
public class FileManager {    
    /**
     * Accessor method of TIMER_FILE
     * 
     * \return String TIMER_FILE - loacation of timer save file
     */
    public String getTimerFile() {
        return TIMER_FILE;
    }
    
    /**
     * Accessor method to set individual index of m_gridArray
     * 
     * \param int x - x index of m_gridArray
     * \param int y - y index of m_gridArray
     * \param Game.PlayerTurn pt - to be stored in the m_gridArray
     * \return true on success
     */
    public Boolean setGridArray(int x, int y, Game.PlayerTurn pt) {
        m_gridArray[x][y] = pt;
        return true;
    }
    
    /**
     * Accessor method to get m_gridArray
     * 
     * \return Game.Playerturn m_gridArray
     */
    public Game.PlayerTurn[][] getGridArray() {
        return m_gridArray;
    }
    
    /**
     * Accessor method to set m_game
     * 
     * \param Game g - reference to current game
     * \return true on success
     */
    public Boolean setGame(Game g) {
        m_game = g;
        setGridObj();
        
        return true;
    }
    
    /**
     * Accessor method to get game reference
     * 
     * \return Game m_game - reference to game
     */
    public Game getGame() {
        return m_game;
    }
    
    /**
     * Accessor method to set path
     * 
     * \param String path - the location of files to be saved and loaded
     * \return true on success
     */
    public boolean setPath(String p) {
        m_path = p;
        
        return true;
    }
    
    /**
     * Accessor method to get path
     * 
     * \return String path
     */
    public String getPath() {
        return m_path;
    }
    
    /**
     * Accessor method to set inFile
     * 
     * \param String file - current file to work on
     * \return true on success
     */
    public boolean setFile(String file) {
        m_inFile = new File(this.m_path + file);
        return true;
    }
    
    /**
     * Accessor method to get inFile
     * 
     * \return File inFile
     */
    public File getFile() {
        return m_inFile;
    }
    
    /**
     * Accessor method to get grid file location
     * 
     * \return String GRID_FILE - location of grid save
     */
    public String getGridFile() {
        return GRID_FILE;
    }
    
    /**
     * Accessor method to get player1 file location
     * 
     * \return String PLAYER1_FILE - location of player1 save
     */
    public String getPlayer1File() {
        return PLAYER1_FILE;
    }
    
    /**
     * Accessor method to get player2 file location
     * 
     * \return String PLAYER2_FILE - location of player2 save
     */
    public String getPlayer2File() {
        return PLAYER2_FILE;
    }
    
    /**
     * Accessor method to set m_grid
     * 
     * \return true on success
     */
    public boolean setGridObj() {
        m_gridObj = m_game.getGrid();
        
        return true;
    }
    
    /**
     * Accessor method to get reference to m_gridObj
     * 
     * \return Grid m_gridObj - reference to the grid object
     */
    public Grid getGridObj() {
        return m_gridObj;
    }
    
    /**
     * Accessor method to set player1
     * 
     * \param Plater p - player object
     * \return true on success
     */
    public boolean setPlayer1(Player p) {
        m_player1 = p;
        
        return true;
    }
    
    /**
     * Accessor method to set player2
     * 
     * \param Plater p - player object
     * \return true on success
     */
    public boolean setPlayer2(Player p) {
        m_player2 = p;
        
        return true;
    }
    
    /**
     * Accessor method to get player1
     * 
     * \return Player m_player1 - player1 loaded from file
     */
    public Player getPlayer1() {
        return m_player1;
    }
    
    /**
     * Accessor method to get player2
     * 
     * \return Player m_player2 - player2 loaded from file
     */
    public Player getPlayer2() {
        return m_player2;
    }
    
    /**
     * Accessor method to set m_timer
     * 
     * \param Timer t - timer loaded from file
     * \return true on success
     */
    public boolean setTimer(Timer t) {
        m_timer = t;
        
        return true;
    }
    
    /**
     * Accessor method to get m_timer
     * 
     * \return Timer m_timer - timer loaded from file
     */
    public Timer getTimer() {
        return m_timer;
    }
    
    /**
     * method to initialise the grid array to the correct size 
     * 
     * \return true on success
     */
    public Boolean initialiseGrid() {
        m_gridArray = new Game.PlayerTurn[m_gridObj.getGridWidth()][m_gridObj.getGridHeight()];
        
        return true;
    }
    
    /**
     * method to create the required directories to save the files
     * 
     * \return true on success
     */
    public boolean createDirs() {
        new File(getPath()).mkdirs();
        
        return true;
    }
    
    /** String to hold the path of files */
    private String m_path = "";
    /** Store the current file */
    private File m_inFile;
    /** Store reference to grid object */
    private Grid m_gridObj;
    /** Strings of file names */
    private final String GRID_FILE = "grid.txt";
    private final String PLAYER1_FILE = "player1.txt";
    private final String PLAYER2_FILE = "player2.txt";
    private final String TIMER_FILE = "timer.txt";
    /** Array to be created for grid */
    private Game.PlayerTurn[][] m_gridArray;
    /** player objects to be poaded from file */
    private Player m_player1;
    private Player m_player2;
    /** timer object to be loaded from file */
    private Timer m_timer;
    /** reference to the game object */
    private Game m_game;
}
