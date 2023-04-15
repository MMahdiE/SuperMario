package Entity;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PiranhaPlant extends Entity{

    BufferedImage open;
    BufferedImage close;
    int maxY, minY;
    int waitCounter = 0;

    public PiranhaPlant(GamePanel gp) {

        super(gp);

        name = "Piranha Plant";
        velocityVertical = gp.FPS/60.0;

        getImage();
    }

    public void getImage() {

        open = setup("/enemies/piranha-plant01");
        close = setup("/enemies/piranha-plant02");
    }

    public void setDefaultValues() {

        maxY = y;
        minY = y - gp.tileHeight;
    }

    @Override
    public void setAction() {}

    @Override
    public void update() {

        //upper bound is minY
        if(y > maxY) {
            waitCounter++;
            if(waitCounter >= 30) {
                //if mario is standing on the pipe the plant won't come out
                int entityLeftWorldX = gp.player.worldX + gp.player.solidArea.x;
                int entityRightWorldX = gp.player.worldX + gp.player.solidArea.x + gp.player.solidArea.width;
                int entityBottomY = gp.player.y + gp.player.solidArea.y + gp.player.solidArea.height;

                int entityLeftWorldCol = entityLeftWorldX / gp.tileWidth;
                int entityRightWorldCol = entityRightWorldX / gp.tileWidth;
                int entityBottomRow;

                int tileNum1, tileNum2;

                entityBottomRow = (entityBottomY + 1) / gp.tileHeight;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftWorldCol][entityBottomRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightWorldCol][entityBottomRow];
                if(gp.tileManager.tile[tileNum1] == gp.tileManager.tile[3] || gp.tileManager.tile[tileNum1] == gp.tileManager.tile[4]
                        || gp.tileManager.tile[tileNum2] == gp.tileManager.tile[3] || gp.tileManager.tile[tileNum2] == gp.tileManager.tile[4]) {
                    waitCounter = 0;
                }
                else {
                    velocityVertical *= -1;
                    y -= (int) velocityVertical;
                    waitCounter = 0;
                }
            }
        }
        else if(y < minY) {
            waitCounter++;
            if(waitCounter >=30) {
                velocityVertical *= -1;
                y -= (int) velocityVertical;
                waitCounter = 0;
            }
        }
        else {
            y -= (int) velocityVertical;
        }

        spriteCounter++;
        if(spriteCounter > gp.FPS/3) {
            switch (spriteNum) {
                case 1:
                    spriteNum = 2;
                    break;
                case 2:
                    spriteNum = 1;
                    break;
            }
            spriteCounter = 0;
        }
    }

    @Override
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;

        if(worldX + gp.tileWidth > gp.player.worldX - gp.player.screenX && worldX - gp.tileWidth < gp.player.worldX + gp.player.screenX) {
            switch (spriteNum) {
                case 1:
                    image = open;
                    break;
                case 2:
                    image = close;
                    break;
            }
            g2.drawImage(image, screenX, y, null);
        }
    }
}
