import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SaveTest extends JFrame{
    JFrame frame;
    JPanel panel;
    JButton saveButton;
    JButton loadButton;
    Game game;
    protected Grid grid;
    boolean ISOTHELLO;
    
    public SaveTest(Game g, boolean isOth) {
        draw();
        game = g;
        grid = game.getGrid();
        ISOTHELLO = isOth;
    }
    
    public void draw() {
        frame = new JFrame("Save/Load Test");
        panel = new JPanel(new FlowLayout());
        
        loadButton = new JButton("Load");
        saveButton = new JButton("Save");
        
        //panel.add(loadButton);
        panel.add(saveButton);
        
        Handler h = new Handler();
        
        loadButton.addActionListener(h);
        saveButton.addActionListener(h);
        
        frame.add(panel);
        
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private class Handler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            
            if (e.getSource() == loadButton) {
                System.out.println("Load");
                //Load l = new Load();
                
                //l.setGrid(grid);
                //l.loadGrid();
            }
            
            if (e.getSource() == saveButton) {
                System.out.println("Save");
                if (ISOTHELLO) {
                    OthelloSaver os = new OthelloSaver(game);
                    os.saveGrid(grid.toString());
                    os.savePlayer1(game.getPlayer1().toString());
                    os.savePlayer2(game.getPlayer2().toString());
                } else {
                    ConnectFourSaver c4s = new ConnectFourSaver(game);
                    c4s.saveGrid(grid.toString());
                    c4s.savePlayer1(game.getPlayer1().toString());
                    c4s.savePlayer2(game.getPlayer2().toString());
                }
                
            }
        }
    }
    
    public static void main(String[] args) {
        //SaveTest s = new SaveTest();
    }
    
}
