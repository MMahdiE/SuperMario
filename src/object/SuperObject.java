package object;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    BufferedImage[] image = new BufferedImage[8];
    String name;
    boolean collision = false;
    public int worldX, y;
    int spriteNum = 1;
    int spriteCounter = 0;

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;

        if(worldX + gp.tileWidth > gp.player.worldX - gp.player.screenX && worldX - gp.tileWidth < gp.player.worldX + gp.player.screenX) {
            spriteCounter++;
            if(spriteCounter == gp.FPS/8) {
                if (spriteNum != 8) {
                    spriteNum++;
                }
                else {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
            g2.drawImage(image[spriteNum - 1], screenX, y, gp.tileWidth, gp.tileHeight, null);
        }
    }
}
