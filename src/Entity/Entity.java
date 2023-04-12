package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX;
    public int y;
    public double velocityHorizontal;
    public double velocityVertical;
    BufferedImage[] walkRight;
    BufferedImage[] walkLeft;
    public boolean direction = true;
    int spriteCounter = 0;
    int spriteNum = 1;
    public Rectangle solidArea;
    public boolean collisionHorizontalOn = false;
    public boolean collisionVerticalOn = false;
    public String keyPressed;
}
