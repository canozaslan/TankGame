
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class Bomb1{
    private int x;
    private int y;

    public Bomb1(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
}

class Bomb2{
    private int x;
    private int y;

    public Bomb2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
}


public class GameScreen extends JPanel implements KeyListener, ActionListener{

    ArrayList<Bomb1> bombs1 = new ArrayList<Bomb1>();
    ArrayList<Bomb2> bombs2 = new ArrayList<Bomb2>();
    
    Timer timer = new Timer(2, this);
    private BufferedImage image1;
    private BufferedImage image2;
    private BufferedImage bombImage;
    private int tank1x = 600;
    private int tank2x = 600;
    private int tank1dirX = 15;
    private int tank2dirX = 15;
    private int bomb1x;
    private int bomb1dirY = 5;
    private int bomb2x;
    private int bomb2dirY = 5;
    private String winner_message = "";
    
    
    private boolean carpismaOlduMU1(){
        
         for(Bomb1 bomb : bombs1){
            if((new Rectangle(bomb.getX(), bomb.getY(), bombImage.getWidth()/5, bombImage.getHeight()/5)).intersects(new Rectangle(tank2x, 0, image2.getWidth()/2, image2.getHeight()/2))){
                winner_message += "Yeşil Tank Düşmanı Patlattı..!";
                return true;
               
            }
        }
        
        return false;
    }
    private boolean carpismaOlduMu2(){
        
        
        for(Bomb2 bomb : bombs2){
            if((new Rectangle(bomb.getX(), bomb.getY(), bombImage.getWidth()/5, bombImage.getHeight()/5)).intersects(new Rectangle(tank1x, 575, image1.getWidth()/2, image1.getHeight()/2))){
                winner_message += "Kırmızı Tank Düşmanı Patlattı..!";
                return true;
            }
        }
        return false;
        
    }
    
    
    public GameScreen() {
        
        try {
            image1 = ImageIO.read(new FileInputStream(new File("tank1.png")));
            image2 = ImageIO.read(new FileInputStream(new File("tank2.png")));
            bombImage = ImageIO.read(new FileInputStream(new File("bomb.png")));
                    
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        setBackground(Color.BLACK);
        
        timer.start();
        
    }

 
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int i = e.getKeyCode();
        //int j = e.getKeyCode();
        
        if(i == KeyEvent.VK_LEFT){
            
            tank1x -= tank1dirX;
            
            if(tank1x <= 0){
                
                tank1x = 0;
            }
            
        }
        else if(i == KeyEvent.VK_RIGHT){
            
            tank1x += tank1dirX;
            
            if(tank1x >= 1275){
                tank1x = 1275;
            }
        }
        else if(i == KeyEvent.VK_CONTROL){
            
            bombs1.add(new Bomb1(tank1x+21, 570));
            
        }
        if(i == KeyEvent.VK_A){
            tank2x -= tank2dirX;
            
            if(tank2x <= 0){
                
                tank2x = 0;
            }
        }
        else if( i == KeyEvent.VK_D){
            tank2x += tank2dirX;
            
            if(tank2x >= 1270){
                tank2x = 1270;
            }
        }
        else if(i == KeyEvent.VK_ALT){
            bombs2.add(new Bomb2(tank2x+23, 65));
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        
 
        for(Bomb1 bomb : bombs1){
            
            bomb.setY(bomb.getY() - bomb1dirY);
            
        }
        for(Bomb2 bomb : bombs2){
            
            bomb.setY(bomb.getY() + bomb2dirY);
            
        }
        /*for(Ates1 ates : atesler1){
            if(ates.getY() < 0){
                atesler1.remove(ates);
            }
            
        }
        for(Ates2 ates : atesler2){
            if(ates.getY() > 600){
                atesler2.remove(ates);
            }
            
        }*/
        
        if((carpismaOlduMU1() == true) || (carpismaOlduMu2() == true)){
           timer.stop();
           String mesaj = "Tebrikler, kazandınız... \n" + winner_message;
           
           JOptionPane.showMessageDialog(this, mesaj);
           System.exit(0);
           
       }
        
        repaint();
        
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        
        g.drawImage(image1, tank1x, 575, image1.getWidth()/2, image1.getHeight()/2, this);
        g.drawImage(image2, tank2x, 0, image2.getWidth()/2, image2.getHeight()/2, this);
        
        for(Bomb1 bomb : bombs1){
            
            g.drawImage(bombImage, bomb.getX(), bomb.getY(), bombImage.getWidth()/5, bombImage.getHeight()/5, this);
           
        }
        for(Bomb2 bomb : bombs2){
            
            g.drawImage(bombImage, bomb.getX(), bomb.getY(), bombImage.getWidth()/5, bombImage.getHeight()/5, this);
           
        }
        
    }

    @Override
    public void repaint() {
        super.repaint(); //To change body of generated methods, choose Tools | Templates.

    }
    
    
    
    
    
}
