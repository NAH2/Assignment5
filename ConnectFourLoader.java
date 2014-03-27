/**
 * \\file - OthelloSaver.java
 * \author 
 * \date 26th March 14
 * 
 * \see 
 * 
 * \brief 
 *
 * The class 
 */
public class ConnectFourLoader extends Loader{
	
	/**
	 * Accessor 
	 * \param 
	 */
    public ConnectFourLoader(Game g) {
        setGame(g);
        setPath(CONNECTFOUR_PATH);
        
        loadAll();
    }
    
    /**
	 * Accessor 
	 * \param 
	 */
    public ConnectFourLoader(Game g, String s) {
        setGame(g);

        setPath(CONNECTFOUR_TEST_PATH);
    }
	/**the  */
    private final String CONNECTFOUR_PATH = "saveData/connectfour/";
	/**the  */
    private final String CONNECTFOUR_TEST_PATH = "saveDataTest/connectfour/";
}
