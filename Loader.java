import java.awt.Color;
import java.util.Scanner;

public class Loader extends FileManager{
    private String hashString;
    int hash;
    boolean valid;
    boolean allValid = true;

    Scanner in;
    String[] playerInfo;
    
    public boolean loadGrid() {
        hashString = "";
        initialiseGrid();
        hash = 0;
        valid = false;
        
        try {
            setFile(getGridFile());
            in = new Scanner(this.getFile()); // may throw exception
            in.useDelimiter(",");
            //try reading file
            try {
                //read the file
                for (int y = 0; y < getGridObj().getGridHeight(); ++y) { // hardcoded at the mo
                    for (int x = 0; x < getGridObj().getGridWidth(); ++x) {                        
                        if (in.hasNext()) {
                            String player = in.next();
                            
                            hashString += player + ",";
                            
                            if (player.equals("PLAYER1")) {                                
                                setGridArray(x,y,Game.PlayerTurn.PLAYER1);
                            } else if (player.equals("PLAYER2")) {                                
                                setGridArray(x,y,Game.PlayerTurn.PLAYER2);
                            } else if (player.equals("NONE")) {                                
                                setGridArray(x,y,Game.PlayerTurn.NONE);
                            } else {
                                System.out.println("Invalid");
                                //do something here to as it is an error
                            }
                        }
                    }
                }
                
                hash = hashString.hashCode();
                in.reset();
                
                if (in.hasNext()) {
                    String b = in.next();
                    int readHash = 0;
                    readHash = Integer.parseInt(b.substring(1));
                    valid = (hash == readHash);
                }
            }finally {
                in.close();
            }                 
        } catch (Exception exc) {
            System.err.println("Error Reading File");
        }
        
        if (valid) {
            getGridObj().setGrid(getGridArray());
            System.out.println("LOAD :: " + getGridObj().toString());
            return true;
        } else {
            System.err.println("File Corrupt");
            allValid = false;
            return false;
        }
    }
    
    public Player loadPlayer1(Player player1) {
        if (loadPlayer(getPlayer1File())) {
            switch (playerInfo[0]) {
                case "human": player1 = new Human(getGame());
            }
            
            player1.setPlayerName(playerInfo[1]); //FIX hardcode
            player1.setPlayerColour(new Color(Integer.parseInt(playerInfo[2])));
            player1.setYourTurn(Boolean.parseBoolean(playerInfo[3]));
        } else {
            allValid = false;
            System.err.println("Error with player 1");
        }
        
        return player1;
    }
    
    public Player loadPlayer2(Player player2) {
        if (loadPlayer(getPlayer2File())) {
            switch (playerInfo[0]) {
                case "human": player2 = new Human(getGame());
            }
            
            player2.setPlayerName(playerInfo[1]); //FIX hardcode
            player2.setPlayerColour(new Color(Integer.parseInt(playerInfo[2])));
            player2.setYourTurn(Boolean.parseBoolean(playerInfo[3]));
        } else {
            allValid = false;
            System.err.println("Error with player 1");
        }
        
        return player2;
    }
    
    public Boolean loadPlayer(String file) {
        valid = false;
        playerInfo = new String[5];
        hashString = "";
        
        try {
            setFile(file);
            in = new Scanner(this.getFile()); // may throw exception
            in.useDelimiter(",");
            //try reading file
            try {
                //read the file
                for (int x = 0; x < playerInfo.length - 1; ++x) {                        // CHANGE MAGIC NUMBER
                    if (in.hasNext()) {
                        playerInfo[x] = in.next();
                        hashString += playerInfo[x] + ",";
                    }
                }
                hash = hashString.hashCode();
                in.reset();
                
                if (in.hasNext()) {
                    String b = in.next();
                    int readHash = 0;
                    readHash = Integer.parseInt(b.substring(1));
                    valid = (hash == readHash);
                }
            }finally {
                in.close();
            }                 
        } catch (Exception exc) {
            System.err.println("Error Reading File");
        }
        return valid;
    }
    
    public Boolean getValid() {
        return allValid;
    }
}
