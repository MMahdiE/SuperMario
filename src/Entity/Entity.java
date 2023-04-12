package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX;
    public int y;
    public int velocity;
    BufferedImage[] walkRight = new BufferedImage[10];
    BufferedImage[] walkLeft = new BufferedImage[10];
    public String direction;
    int spriteCounter = 0;
    int spriteNum = 1;
    public Rectangle solidArea;
    public boolean collisionOn = false;
}
