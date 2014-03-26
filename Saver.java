import java.io.PrintWriter;

public class Saver extends FileManager{
    
    public Boolean saveGrid(String data) {
        saveData(data, getGridFile());
        
        return true;
    }
    
    public Boolean savePlayer1(String data) {
        saveData(data, getPlayer1File());
        
        return true;
    }
    
    public Boolean savePlayer2(String data) {
        saveData(data, getPlayer2File());
        
        return true;
    }
    
    public Boolean saveTimer(String data) {
        saveData(data, getTimerFile());
        
        return true;
    }
    
    public Boolean saveData(String data,String dataFile) {
        try {
            setFile(dataFile);
            PrintWriter out = new PrintWriter(this.getFile());
            //try reading file
            try {
                out.println(data + data.hashCode());
            }finally {
                out.close();
            }                 
        } catch (Exception exc) {
            System.err.println("Write Error");
        }
        return true;
    }
}