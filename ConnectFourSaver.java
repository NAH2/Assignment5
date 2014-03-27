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
public class ConnectFourSaver extends Saver{
	/**
	 * Accessor 
	 * \param 
	 */
    public ConnectFourSaver(Game g) {
        setGame(g);
        setPath(CONNECTFOUR_PATH);
        
        createDirs();
    }
    /**
	 * Accessor 
	 * \param 
	 */
    public ConnectFourSaver() {
        setPath(CONNECTFOUR_TEST_PATH);
        
        createDirs();
    }
    /**the  */
    private final String CONNECTFOUR_PATH = "saveData/connectfour/";
    /**the  */
    private final String CONNECTFOUR_TEST_PATH = "saveDataTest/connectfour/";
}
