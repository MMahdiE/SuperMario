package object;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    BufferedImage image;
    String name;
    boolean collision = false;
    public int worldX, y;

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;

        if(worldX + gp.tileWidth > gp.player.worldX - gp.player.screenX && worldX - gp.tileWidth < gp.player.worldX + gp.player.screenX) {
            g2.drawImage(image, screenX, y, gp.tileWidth, gp.tileHeight, null);
        }
    }
}
