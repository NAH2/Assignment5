/**
 * \\file -ConnectFourLoader.java 
 * \author -Jake Plumley
 * \date -26th March 14
 * 
 * \see FileManager.java
 * \see Loader.java
 * 
 * \brief Class to set file paths for the Loading of ConnectFour
 * 
 * This Class extends the Loader class to and controls the path of the save file
 */
public class ConnectFourLoader extends Loader{
    /**
     * Constructor to initiate the loading of the files
     * 
     * \param Game game - A reference to the Game to be loaded
     */
    public ConnectFourLoader(Game game) {
        boolean test = false;
        if (test || m_test) {
            System.out.println("ConnectFourLoader :: ConnectFourLoader() "
                                                                     + "BEGIN");
        }
        
        setGame(game);
        setPath(CONNECTFOUR_PATH);
        
        loadAll();
        
        if (test || m_test) {
            System.out.println("ConnectFourLoader :: ConnectFourLoader() END");
        }
    }
    
    /**
     * Test constructor
     * 
     * \param Game game - reference to the game to be loaded
     * \param String s - used to overload the constructor
     */
    public ConnectFourLoader(Game game, String s) {
        boolean test = false;
        if (test || m_test) {
            System.out.println("ConnectFourLoader :: ConnectFourLoader() "
                                                                     + "BEGIN");
        }
        
        setGame(game);
        setPath(CONNECTFOUR_TEST_PATH);
        
        if (test || m_test) {
            System.out.println("ConnectFourLoader :: ConnectFourLoader() END");
        }
    }
    
    /** file paths to load files */
    private final String CONNECTFOUR_PATH = "saveData/connectfour/";
    /** file path to test loading */
    private final String CONNECTFOUR_TEST_PATH = "saveDataTest/connectfour/";
    /** Test variable */
    private boolean m_test = false;
}
