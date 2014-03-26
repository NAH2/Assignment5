public class ConnectFourLoader extends Loader{
    private final String CONNECTFOUR_PATH = "saveData/connectfour/";
    
    public ConnectFourLoader(Game g) {
        setGame(g);
        setPath(CONNECTFOUR_PATH);
        
        loadAll();
    }
    
    
}
