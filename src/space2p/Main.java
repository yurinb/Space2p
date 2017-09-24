package space2p;

import java.awt.Toolkit;
import javax.swing.JFrame;

public class Main {
    
    static int screenSizeX;
    static int screenSizeY;
    static GameScreen gameScreen;

    public static void main(String[] args) {
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        screenSizeX = (int) tk.getScreenSize().getWidth();
        screenSizeY = (int) tk.getScreenSize().getHeight();
        
        JFrame JFscreen = new JFrame();
        JFscreen.setBounds(0, 0, screenSizeX, screenSizeY);
        JFscreen.setResizable(false);
        JFscreen.setAlwaysOnTop(true);
        JFscreen.setUndecorated(true);
                
        Naves naves = new Naves(screenSizeX, screenSizeY);
        
        gameScreen = new GameScreen(naves);
        
        JFscreen.add(gameScreen);
        
        JFscreen.setVisible(true);
        
    }
    
}
