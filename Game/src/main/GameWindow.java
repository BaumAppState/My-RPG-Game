package main;

import javax.swing.JFrame;

public class GameWindow {
    
    private JFrame gameFrame;
    
    public GameWindow() {
        gameFrame = new JFrame();
        gameFrame.setLocation(400, 100);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setTitle("My Awesome Game");
        
        GamePanel gP = new GamePanel();
        gameFrame.add(gP);
        gameFrame.pack();
        
        gameFrame.setVisible(true);
        
        gP.startGameThread();
    }
}
