package object;

import Main.GamePanel;
import Main.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class SuperObject {
    BufferedImage[] image;
    UtilityTool uTool = new UtilityTool();
    public String name;
    public boolean collision = false;
    public int worldX, y;
    int spriteNum = 1;
    int spriteCounter = 0;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public abstract void draw(Graphics2D g2, GamePanel gp);
}
