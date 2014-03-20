
public class ConnectFourSaver extends Saver{
    private final String CONNECTFOUR_PATH = "saveData/connectfour/";
    
    public ConnectFourSaver(Game g) {
        setGame(g);
        setPath(CONNECTFOUR_PATH);
    }
}
