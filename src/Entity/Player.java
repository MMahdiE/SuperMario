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
    double gravitationalAcceleration;
    int maxVerticalVelocity;
    boolean jumping;
    boolean falling;
    int distanceJumped;
    BufferedImage jumpRight;
    BufferedImage jumpLeft;
    BufferedImage wantedToGoRight;
    BufferedImage wantedToGoLeft;
    BufferedImage standStillRight;
    BufferedImage standStillLeft;


    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        accelerationHorizontal = gp.FPS/1200.0/2;
        maxHorizontalVelocity = gp.FPS/12;
        gravitationalAcceleration = gp.FPS/480.0 * -1;
        maxVerticalVelocity = gp.FPS/30;


        screenX = (gp.screenWidth - gp.tileWidth) / 2;

        solidArea = new Rectangle();
        solidArea.x = 1 * gp.scale;
        solidArea.y = 2 * gp.scale;
        solidArea.width = gp.tileWidth - (2*solidArea.x);
        //Why do I need the - 1 ???????????????????????????????????????????????????????????????????????
        solidArea.height = gp.tileHeight - (solidArea.y) - 1;

        setDefaultValues();
        getPlayerImage();
    }

    public void getPlayerImage() {
        try{
            walkRight = new BufferedImage[4];
            walkLeft = new BufferedImage[4];

            walkRight[0] = ImageIO.read(getClass().getResourceAsStream("/Player/walk01.png"));
            walkRight[1] = ImageIO.read(getClass().getResourceAsStream("/Player/walk02.png"));
            walkRight[2] = ImageIO.read(getClass().getResourceAsStream("/Player/walk03.png"));
            walkRight[3] = ImageIO.read(getClass().getResourceAsStream("/Player/walk02.png"));

            walkLeft[0] = ImageIO.read(getClass().getResourceAsStream("/Player/walk01-left.png"));
            walkLeft[1] = ImageIO.read(getClass().getResourceAsStream("/Player/walk02-left.png"));
            walkLeft[2] = ImageIO.read(getClass().getResourceAsStream("/Player/walk03-left.png"));
            walkLeft[3] = ImageIO.read(getClass().getResourceAsStream("/Player/walk02-left.png"));

            jumpRight= ImageIO.read(getClass().getResourceAsStream("/Player/jump.png"));
            jumpLeft = ImageIO.read(getClass().getResourceAsStream("/Player/jump-left.png"));
            wantedToGoRight = ImageIO.read(getClass().getResourceAsStream("/Player/wanted-to-go-right.png"));
            wantedToGoLeft = ImageIO.read(getClass().getResourceAsStream("/Player/wanted-to-go-left.png"));
            standStillRight = ImageIO.read(getClass().getResourceAsStream("/Player/stand-still.png"));
            standStillLeft = ImageIO.read(getClass().getResourceAsStream("/Player/stand-still-left.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDefaultValues() {
        worldX = 200;
        y = 5 * gp.tileHeight;
        velocityHorizontal = 0.0;
        velocityVertical = 0.0;
        distanceJumped = 0;
        jumping = false;
        falling = false;
    }

    public void update() {
        //Horizontal
        boolean isStandingOnSth = gp.cChecker.checkIfStandingOnSth(this);
        if(keyH.right || keyH.left) {
            if (keyH.right) {
                keyPressed = "right";
                direction = true;
                if(isStandingOnSth) {
                    if((velocityHorizontal + accelerationHorizontal) < maxHorizontalVelocity) {
                        velocityHorizontal += accelerationHorizontal;
                    }
                }
                else {
                    if ((velocityHorizontal + accelerationHorizontal) < maxHorizontalVelocity) {
                        velocityHorizontal += accelerationHorizontal/2;
                    }
                }
            }
            if (keyH.left) {
                keyPressed = "left";
                direction = false;
                if(isStandingOnSth) {
                    if ((velocityHorizontal - accelerationHorizontal) > (-maxHorizontalVelocity)) {
                        velocityHorizontal -= accelerationHorizontal;
                    }
                }
                else {
                    if ((velocityHorizontal - accelerationHorizontal) > (-maxHorizontalVelocity)) {
                        velocityHorizontal -= accelerationHorizontal/2;
                    }
                }
            }
        }
        else {
            keyPressed = "";
            if(velocityHorizontal > 0) {
                velocityHorizontal -= (accelerationHorizontal);
                if(velocityHorizontal < 0) {
                    velocityHorizontal = 0;
                }
            }
            else if(velocityHorizontal < 0) {
                velocityHorizontal += (accelerationHorizontal);
                if(velocityHorizontal > 0) {
                    velocityHorizontal = 0;
                }
            }
        }

        //Check tile collision
        collisionHorizontalOn = false;
        gp.cChecker.checkTileHorizontal(this);
        if(collisionHorizontalOn) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!1");
            velocityHorizontal = 0.0;
        }

        System.out.println(velocityHorizontal);
        worldX += (int) velocityHorizontal;



        //Vertical
        if(velocityVertical > maxVerticalVelocity * -1) {
            velocityVertical += gravitationalAcceleration;
        }

        if(keyH.up && distanceJumped < 180 && !falling && (jumping || gp.cChecker.checkIfStandingOnSth(this))) {
            jumping = true;
            velocityVertical = maxVerticalVelocity;
            distanceJumped += velocityVertical;
        }
        else {
            distanceJumped = 0;
            jumping = false;
        }

        collisionVerticalOn = false;
        gp.cChecker.checkTileVertical(this);
        if(collisionVerticalOn) {
            velocityVertical = 0;
        }

        falling = false;
        if(jumping == false && (int)velocityVertical != 0) {
            falling = true;
        }

        y -= (int) velocityVertical;
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        if(direction) {
            if (!gp.cChecker.checkIfStandingOnSth(this)) {
                image = jumpRight;
                spriteNum = 1;
            } else if ((int) velocityHorizontal == 0) {
                image = standStillRight;
                spriteNum = 1;
            } else if (velocityHorizontal > 0) {
                spriteCounter++;
                if (spriteCounter > gp.FPS/velocityHorizontal) {
                    if (spriteNum == 4) {
                        spriteNum = 1;
                    } else {
                        spriteNum++;
                    }
                    spriteCounter = 0;
                }
                image = walkRight[spriteNum - 1];
            } else if (velocityHorizontal < 0) {
                image = wantedToGoRight;
                spriteNum = 1;
            }
        }
        else {
            if(!gp.cChecker.checkIfStandingOnSth(this)) {
                image = jumpLeft;
                spriteNum = 1;
            }
            else if((int) velocityHorizontal == 0) {
                image = standStillLeft;
                spriteNum = 1;
            }
            else if(velocityHorizontal < 0) {
                spriteCounter++;
                if(spriteCounter > gp.FPS/velocityHorizontal * -1) {
                    if(spriteNum == 4) {
                        spriteNum = 1;
                    }
                    else {
                        spriteNum++;
                    }
                    spriteCounter = 0;
                }
                image = walkLeft[spriteNum - 1];
            }
            else if(velocityHorizontal > 0) {
                image = wantedToGoLeft;
                spriteNum = 1;
            }
        }

        g2.drawImage(image, screenX, y, gp.tileWidth, gp.tileHeight, null);
    }
}
