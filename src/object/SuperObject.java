package object;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class SuperObject {
    BufferedImage[] image;
    String name;
    boolean collision = false;
    public int worldX, y;
    int spriteNum = 1;
    int spriteCounter = 0;

    public abstract void draw(Graphics2D g2, GamePanel gp);
}
