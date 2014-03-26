public class ConnectFourLoader extends Loader{
    private final String CONNECTFOUR_PATH = "saveData/connectfour/";
    private final String CONNECTFOUR_TEST_PATH = "saveDataTest/connectfour/";

    public ConnectFourLoader(Game g) {
        setGame(g);
        setPath(CONNECTFOUR_PATH);
        
        loadAll();
    }
    
    public ConnectFourLoader(Game g, String s) {
        setGame(g);

        setPath(CONNECTFOUR_TEST_PATH);
    }
    
    
}
