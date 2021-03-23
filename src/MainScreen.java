
import java.awt.HeadlessException;
import javax.swing.JFrame;


public class MainScreen extends JFrame{
    
    
    
    public static void main(String[] args) {
        
        MainScreen screen = new MainScreen("Tank Game");
        
        screen.setFocusable(false);
        screen.setSize(1368, 724);
        screen.setResizable(false);
        screen.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        GameScreen game = new GameScreen();
        
        game.requestFocus();
        game.addKeyListener(game);
        game.setFocusable(true);
        game.setFocusTraversalKeysEnabled(false);
        
        screen.add(game);
        screen.setVisible(true);
        
    
        
    }

    public MainScreen(String title) throws HeadlessException {
        super(title);
    }
    
    
    
}
