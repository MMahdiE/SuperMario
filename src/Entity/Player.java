package Entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = (gp.screenWidth - gp.tileWidth) / 2;

        setDefaultValues();
        getPlayerImage();
    }

    public void getPlayerImage() {
        try{
            walkLeft1 = ImageIO.read(getClass().getResourceAsStream("/Player/walking-left-1.png"));
            walkLeft2 = ImageIO.read(getClass().getResourceAsStream("/Player/walking-left-2.png"));
            walkRight1 = ImageIO.read(getClass().getResourceAsStream("/Player/walking-1.png"));
            walkRight2 = ImageIO.read(getClass().getResourceAsStream("/Player/walking-2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDefaultValues() {
        worldX = 2000;
        y = 5 * gp.tileHeight;
        velocity = gp.FPS/12;
        direction = "right";
    }

    public void update() {
        if(keyH.up || keyH.down || keyH.right || keyH.left) {
            if (keyH.up) {
                //y += velocity;
            }
            if (keyH.down) {
                //y -= velocity;
            }
            if (keyH.right) {
                direction = "right";
                worldX += velocity;
            }
            if (keyH.left) {
                direction = "left";
                worldX  -= velocity;
            }

            spriteCounter++;
            if(spriteCounter > velocity*2) {
                if(spriteNum == 1) {
                    spriteNum = 2;
                }
                else if(spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "right":
                if(spriteNum == 1) {
                    image = walkRight1;
                }
                if(spriteNum == 2) {
                    image = walkRight2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = walkLeft1;
                }
                if(spriteNum == 2) {
                    image = walkLeft2;
                }
                break;
        }

        g2.drawImage(image, screenX, y, gp.tileWidth, gp.tileHeight, null);
    }
}
