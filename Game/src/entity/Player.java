package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    
    GamePanel gP;
    KeyHandler kH;

    public Player(GamePanel gP, KeyHandler kH) {
        
        this.gP = gP;
        this.kH = kH;
        setDefaultValues();
        getPlayerImage();
    }
    
    public void setDefaultValues() {
        
        x = 3 * gP.TILE_SIZE;      //tile col 4
        y = 3 * gP.TILE_SIZE;      //tile row 4
        speed = gP.TILE_SIZE / 2; //will move 1/2 tile (must use low fps though)
        direction = "down";
    }
    
    public void getPlayerImage() {
        
        try {
            
            
            up0 = ImageIO.read(getClass().getResourceAsStream(
                    "/player/cam-up-0.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream(
                    "/player/cam-up-1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream(
                    "/player/cam-up-2.png"));
            left0 = ImageIO.read(getClass().getResourceAsStream(
                    "/player/cam-left-0.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream(
                    "/player/cam-left-1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream(
                    "/player/cam-left-2.png"));
            down0 = ImageIO.read(getClass().getResourceAsStream(
                    "/player/cam-down-0.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream(
                    "/player/cam-down-1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream(
                    "/player/cam-down-2.png"));
            right0 = ImageIO.read(getClass().getResourceAsStream(
                    "/player/cam-right-0.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream(
                    "/player/cam-right-1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream(
                    "/player/cam-right-2.png"));
        
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public void update() {
        
        if(kH.upPressed == true || kH.leftPressed == true ||
           kH.downPressed == true || kH.rightPressed == true) {
        
            if(kH.upPressed == true) {
                direction = "up";
                y -= speed;
            }
            else if(kH.leftPressed == true) {
                direction = "left";
                x -= speed;
            }
            else if(kH.downPressed == true) {
                direction = "down";
                y += speed;
            }
            else if(kH.rightPressed == true) {
                direction = "right";
                x += speed;
            }
        
            spriteCounter++;
            if(spriteCounter > 1) {     //image changes every 1 frame(s)
            
                if(spriteNum == 0)
                    spriteNum = 1;
                else if(spriteNum == 1)
                    spriteNum = 2;
                else if(spriteNum == 2)
                    spriteNum = 0;
            
                spriteCounter = 0;
            }
        }
    }
    
    public void draw(Graphics2D g2) {
        
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gP.TILE_SIZE, gP.TILE_SIZE);
        
        BufferedImage image = null;
        
        switch(direction) {
            case "up":
                if(spriteNum == 0)
                    image = up0;
                if(spriteNum == 1)
                    image = up1;
                if(spriteNum ==2)
                    image = up2;
                break;
            case "left":
                if(spriteNum == 0)
                    image = left0;
                if(spriteNum == 1)
                    image = left1;
                if(spriteNum ==2)
                    image = left2;
                break;
            case "down":
                if(spriteNum == 0)
                    image = down0;
                if(spriteNum == 1)
                    image = down1;
                if(spriteNum ==2)
                    image = down2;
                break;
            case "right":
                if(spriteNum == 0)
                    image = right0;
                if(spriteNum == 1)
                    image = right1;
                if(spriteNum ==2)
                    image = right2;
                break;
        }
        
        g2.drawImage(image, x, y, gP.TILE_SIZE, gP.TILE_SIZE, null);
    }
}
