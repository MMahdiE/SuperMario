package Entity;

import java.awt.image.BufferedImage;

public class Entity {
    public int worldX;
    public int worldY;
    int velocity;
    BufferedImage walkLeft1, walkLeft2, walkRight1, walkRight2;
    String direction;
    int spriteCounter = 0;
    int spriteNum = 1;
}
