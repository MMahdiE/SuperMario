package Entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    double accelerationHorizontal;
    int maxHorizontalVelocity;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        accelerationHorizontal = gp.FPS/1200.0;
        maxHorizontalVelocity = gp.FPS/12;

        screenX = (gp.screenWidth - gp.tileWidth) / 2;

        solidArea = new Rectangle();
        solidArea.x = 1 * gp.scale;
        solidArea.y = 2 * gp.scale;
        solidArea.width = gp.tileWidth - (2*solidArea.x);
        solidArea.height = gp.tileHeight - (2*solidArea.y);

        setDefaultValues();
        getPlayerImage();
    }

    public void getPlayerImage() {
        try{
            walkRight[0] = ImageIO.read(getClass().getResourceAsStream("/Player/walk01.png"));
            walkRight[1] = ImageIO.read(getClass().getResourceAsStream("/Player/walk02.png"));
            walkRight[2] = ImageIO.read(getClass().getResourceAsStream("/Player/walk03.png"));
            walkRight[3] = ImageIO.read(getClass().getResourceAsStream("/Player/walk04.png"));
            walkRight[4] = ImageIO.read(getClass().getResourceAsStream("/Player/walk05.png"));
            walkRight[5] = ImageIO.read(getClass().getResourceAsStream("/Player/walk06.png"));
            walkRight[6] = ImageIO.read(getClass().getResourceAsStream("/Player/walk07.png"));
            walkRight[7] = ImageIO.read(getClass().getResourceAsStream("/Player/walk08.png"));
            walkRight[8] = ImageIO.read(getClass().getResourceAsStream("/Player/walk09.png"));
            walkRight[9] = ImageIO.read(getClass().getResourceAsStream("/Player/walk10.png"));

            walkLeft[0] = ImageIO.read(getClass().getResourceAsStream("/Player/walk-left01.png"));
            walkLeft[1] = ImageIO.read(getClass().getResourceAsStream("/Player/walk-left02.png"));
            walkLeft[2] = ImageIO.read(getClass().getResourceAsStream("/Player/walk-left03.png"));
            walkLeft[3] = ImageIO.read(getClass().getResourceAsStream("/Player/walk-left04.png"));
            walkLeft[4] = ImageIO.read(getClass().getResourceAsStream("/Player/walk-left05.png"));
            walkLeft[5] = ImageIO.read(getClass().getResourceAsStream("/Player/walk-left06.png"));
            walkLeft[6] = ImageIO.read(getClass().getResourceAsStream("/Player/walk-left07.png"));
            walkLeft[7] = ImageIO.read(getClass().getResourceAsStream("/Player/walk-left08.png"));
            walkLeft[8] = ImageIO.read(getClass().getResourceAsStream("/Player/walk-left09.png"));
            walkLeft[9] = ImageIO.read(getClass().getResourceAsStream("/Player/walk-left10.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDefaultValues() {
        worldX = 2000;
        y = 5 * gp.tileHeight;
        velocityHorizontal = 0.0;
        direction = "right";
    }

    public void update() {
        if(keyH.up || keyH.down || keyH.right || keyH.left) {
            if (keyH.up) {
                //y += velocity;
            }
            if (keyH.down) {
                //y -= velocity;
            }
            if (keyH.right) {
                direction = "right";
            }
            if (keyH.left) {
                direction = "left";
            }



            //If collisionOn is false, player can move
//            if(collisionOn == false) {
                if (keyH.up) {
                    //y += velocity;
                }
                if (keyH.down) {
                    //y -= velocity;
                }
                if (keyH.right) {
                    direction = "right";
                    if((velocityHorizontal + accelerationHorizontal) < maxHorizontalVelocity) {
                        velocityHorizontal += accelerationHorizontal;
                    }
                }
                if (keyH.left) {
                    if((velocityHorizontal - accelerationHorizontal) > (-maxHorizontalVelocity)) {
                        velocityHorizontal -= accelerationHorizontal;
                    }
                }
//            }

            if(collisionOn) {
                velocityHorizontal = 0;
            }

            spriteCounter++;
            if(spriteCounter > gp.FPS/6) {
                if(spriteNum == 10) {
                    spriteNum = 1;
                }
                else {
                    spriteNum++;
                }
                spriteCounter = 0;
            }
        }
        else {
            if(velocityHorizontal > 0) {
                velocityHorizontal -= (2 * accelerationHorizontal);
                if(velocityHorizontal < 0) {
                    velocityHorizontal = 0;
                }
            }
            else if(velocityHorizontal < 0) {
                velocityHorizontal += (2 * accelerationHorizontal);
                if(velocityHorizontal > 0) {
                    velocityHorizontal = 0;
                }
            }
        }

        //Check tile collision
        collisionOn = false;
        gp.cChecker.checkTile(this);
        if(collisionOn) {
            velocityHorizontal = 0.0;
        }

        worldX += (int) velocityHorizontal;
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "right":
                image = walkRight[spriteNum-1];
                break;
            case "left":
                image = walkLeft[spriteNum-1];
                break;
        }

        g2.drawImage(image, screenX, y, gp.tileWidth, gp.tileHeight, null);
    }
}
