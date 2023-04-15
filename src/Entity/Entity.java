package Entity;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Entity {

    GamePanel gp;
    int maxVerticalVelocity;


    public Entity(GamePanel gp) {

        this.gp = gp;

        maxVerticalVelocity = gp.FPS/20;
    }

    public String name;
    public int worldX;
    public int y;
    public boolean collision = true;
    public double velocityHorizontal;
    public double velocityVertical;
    public boolean direction = true;
    int spriteCounter = 0;
    int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 47);
    //WHY NEEDS TO BE 47?!?!?!?!?!??!?!?!??!?!?!??!?!??!?!?!?!??!?
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public boolean collisionHorizontalOn = false;
    public boolean collisionVerticalOn = false;
    public String keyPressed;

    public abstract void getImage();
    public abstract void setDefaultValues();

    public BufferedImage setup(String filePath) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(filePath + ".png"));
            image = uTool.scaleImage(image, gp.tileWidth, gp.tileHeight);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }


    //setting random actions for NPCs(their AI)
    public abstract void setAction();
    public abstract void update();
    public abstract void draw(Graphics2D g2);
}
