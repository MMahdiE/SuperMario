package object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class OBJ_Coin extends SuperObject{

    GamePanel gp;

    public OBJ_Coin(GamePanel gp) {

        this.gp = gp;

        name = "Coin";
        image = new BufferedImage[4];
        try {
            image[0] = ImageIO.read(getClass().getResourceAsStream("/objects/coin01.png"));
            image[0] = uTool.scaleImage(image[0], gp.tileWidth, gp.tileHeight);
            image[1] = ImageIO.read(getClass().getResourceAsStream("/objects/coin02.png"));
            image[1] = uTool.scaleImage(image[1], gp.tileWidth, gp.tileHeight);
            image[2] = ImageIO.read(getClass().getResourceAsStream("/objects/coin03.png"));
            image[2] = uTool.scaleImage(image[2], gp.tileWidth, gp.tileHeight);
            image[3] = ImageIO.read(getClass().getResourceAsStream("/objects/coin04.png"));
            image[3] = uTool.scaleImage(image[3], gp.tileWidth, gp.tileHeight);

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
            g2.drawImage(image[spriteNum - 1], screenX, y, null);
        }
    }
}
