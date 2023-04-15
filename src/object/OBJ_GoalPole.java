package object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class OBJ_GoalPole extends SuperObject{

    GamePanel gp;

    public OBJ_GoalPole(GamePanel gp) {

        this.gp = gp;

        name = "Goal Pole";
        image = new BufferedImage[2];
        try {
            image[0] = ImageIO.read(getClass().getResourceAsStream("/objects/goal-pole01.png"));
            image[0] = uTool.scaleImage(image[0], gp.tileWidth, gp.tileHeight);
            image[1] = ImageIO.read(getClass().getResourceAsStream("/objects/goal-pole02.png"));
            image[1] = uTool.scaleImage(image[0], gp.tileWidth, gp.tileHeight);
        }catch (IOException e) {
            e.printStackTrace();
        }

        solidArea.x = 7 * 3;
        solidArea.y = 8 * 3;
        solidArea.width = 2 * 3;
        solidArea.height = 16 * 3 * 10;
    }

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;

        if(worldX + gp.tileWidth > gp.player.worldX - gp.player.screenX && worldX - gp.tileWidth < gp.player.worldX + gp.player.screenX) {

            g2.drawImage(image[0], screenX, y, gp.tileWidth, gp.tileHeight, null);
            g2.drawImage(image[1], screenX, y + (gp.tileHeight*1), null);
            g2.drawImage(image[1], screenX, y + (gp.tileHeight*2), null);
            g2.drawImage(image[1], screenX, y + (gp.tileHeight*3), null);
            g2.drawImage(image[1], screenX, y + (gp.tileHeight*4), null);
            g2.drawImage(image[1], screenX, y + (gp.tileHeight*5), null);
            g2.drawImage(image[1], screenX, y + (gp.tileHeight*6), null);
            g2.drawImage(image[1], screenX, y + (gp.tileHeight*7), null);
            g2.drawImage(image[1], screenX, y + (gp.tileHeight*8), null);
            g2.drawImage(image[1], screenX, y + (gp.tileHeight*9), null);

        }
    }
}
