package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable{

    final int UNSCALED_TILE_SIZE = 16;
    final int SCALE = 3;
    public final int TILE_SIZE = UNSCALED_TILE_SIZE * SCALE;   
    final int MAX_SCREEN_COL = 16;
    final int MAX_SCREEN_ROW = 12;
    final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;
    final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;
   
    int fps = 8;
    
    KeyHandler kH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, kH);
    
    int playerX = 3 * TILE_SIZE;
    int playerY = 3 * TILE_SIZE;
    int playerSpeed = 4;
    
    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(kH);
        this.setFocusable(true);
        
    }

    @Override
    public void run()
    {
        
        double drawInterval = 1000000000 / fps; //1 sec/fps = 0.01666 sec
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        
        //display FPS (Not Necessary)
        long timer = 0;
        int drawCount = 0;
        
        while(gameThread != null) {
            
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime); //THIS IS JUST FOR DISPLAYING FPS
            lastTime = currentTime;
            
            if(delta >= 1) {
                update(); //UPDATE
                repaint(); //REDRAW SCREEN
                delta--;
                drawCount++;
            }
            
            //FOR DISPLAYING FPS
            if(timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
        
    }
    
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    public void update() {
        
       player.update(); 
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        
        player.draw(g2);
        g2.dispose();
    }
    
}
