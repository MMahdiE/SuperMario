package Entity;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Goomba extends Entity {

    BufferedImage right, left, dead;


    public Goomba(GamePanel gp) {

        super(gp);

        name = "Goomba";

        getImage();

        direction = false;
        velocityHorizontal = gp.FPS/60.0 * -1;
        velocityVertical = 0;
    }

    @Override
    public void getImage() {

        right = setup("/enemies/goomba01");
        left = setup("/enemies/goomba02");
        dead = setup("/enemies/goomba03");
    }

    public void setDefaultValues() {

    }

    @Override
    public void setAction() {

    }

    @Override
    public void update() {

        //Horizontal
        collisionHorizontalOn = false;
        gp.cChecker.checkTileHorizontal(this);

        if(collisionHorizontalOn) {
            velocityHorizontal *= -1;
            spriteNum = 1;
        }

        spriteCounter++;
        if(spriteCounter > gp.FPS/4) {
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

        worldX += (int) velocityHorizontal;

        //Vertical
        if(velocityVertical > maxVerticalVelocity * -1) {
            velocityVertical += gp.gravitationalAcceleration;
        }

        collisionVerticalOn = false;
        gp.cChecker.checkTileVertical(this);
        if(collisionVerticalOn) {
            velocityVertical = 0.0;
        }

        y -= (int) velocityVertical;
    }

    @Override
    public void draw(Graphics2D g2) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX;

        if(worldX + gp.tileWidth > gp.player.worldX - gp.player.screenX && worldX - gp.tileWidth < gp.player.worldX + gp.player.screenX) {
            BufferedImage image = null;

            switch (spriteNum) {
                case 1:
                    image = right;
                    break;
                case 2:
                    image = left;
                    break;
            }

            g2.drawImage(image, screenX, y, null);
        }
    }
}
