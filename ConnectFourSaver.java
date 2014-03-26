
public class ConnectFourSaver extends Saver{
    private final String CONNECTFOUR_PATH = "saveData/connectfour/";
    private final String CONNECTFOUR_TEST_PATH = "saveDataTest/connectfour/";

    
    public ConnectFourSaver(Game g) {
        setGame(g);
        setPath(CONNECTFOUR_PATH);
        
        createDirs();
    }
    
    public ConnectFourSaver() {
        setPath(CONNECTFOUR_TEST_PATH);
        
        createDirs();
    }
}
