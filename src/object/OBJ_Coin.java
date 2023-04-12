package object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class OBJ_Coin extends SuperObject{

    public OBJ_Coin() {
        name = "Coin";
        image = new BufferedImage[4];
        try {
            image[0] = ImageIO.read(getClass().getResourceAsStream("/objects/coin01.png"));
            image[1] = ImageIO.read(getClass().getResourceAsStream("/objects/coin02.png"));
            image[2] = ImageIO.read(getClass().getResourceAsStream("/objects/coin03.png"));
            image[3] = ImageIO.read(getClass().getResourceAsStream("/objects/coin04.png"));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;

        if(worldX + gp.tileWidth > gp.player.worldX - gp.player.screenX && worldX - gp.tileWidth < gp.player.worldX + gp.player.screenX) {
            spriteCounter++;
            if(spriteCounter == gp.FPS/4) {
                if (spriteNum != 4) {
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
