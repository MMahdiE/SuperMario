package Entity;

import Main.GamePanel;
import Main.KeyHandler;
import Main.UtilityTool;

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
    public int coins = 0;
    public int lives = 999;


    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        accelerationHorizontal = gp.FPS/1200.0;
        maxHorizontalVelocity = gp.FPS/12;
        gravitationalAcceleration = gp.FPS/480.0 * -1;
        maxVerticalVelocity = gp.FPS/20;


        screenX = (gp.screenWidth - gp.tileWidth) / 2;

        solidArea = new Rectangle();
        solidArea.x = 1 * gp.scale;
        solidArea.y = 2 * gp.scale;
        solidArea.width = gp.tileWidth - (2*solidArea.x);
        //Why do I need the - 1 ???????????????????????????????????????????????????????????????????????
        solidArea.height = gp.tileHeight - (solidArea.y) - 1;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void getPlayerImage() {

        walkRight = new BufferedImage[4];
        walkLeft = new BufferedImage[4];


        walkRight[0] = setup("walk01");
        walkRight[1] = setup("walk02");
        walkRight[2] = setup("walk03");
        walkRight[3] = setup("walk02");
        walkLeft[0] = setup("walk01-left");
        walkLeft[1] = setup("walk02-left");
        walkLeft[2] = setup("walk03-left");
        walkLeft[3] = setup("walk02-left");
        jumpRight  = setup("jump");
        jumpLeft = setup("jump-left");
        wantedToGoRight = setup("wanted-to-go-right");
        wantedToGoLeft = setup("wanted-to-go-left");
        standStillRight = setup("stand-still");
        standStillLeft = setup("stand-still-left");
    }

    public BufferedImage setup(String fileName) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Player/" + fileName + ".png"));
            image = uTool.scaleImage(image, gp.tileWidth, gp.tileHeight);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    public void setDefaultValues() {
        worldX = 2000;
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
            velocityHorizontal = 0.0;
        }

        worldX += (int) velocityHorizontal;



        //Vertical
        if(velocityVertical > maxVerticalVelocity * -1) {
            velocityVertical += gravitationalAcceleration;
        }

        if(keyH.up && distanceJumped < 180 && !falling && (jumping || gp.cChecker.checkIfStandingOnSth(this))) {
            if(jumping == false) {
                gp.playSE(2);
            }

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
            velocityVertical = 0.0;
        }

        falling = false;
        if(jumping == false && (int)velocityVertical != 0) {
            falling = true;
        }

        y -= (int) velocityVertical;



        int objIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objIndex);
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

        g2.drawImage(image, screenX, y, null);
    }

    public void pickUpObject(int index) {
        if(index != 999) {
            switch (gp.obj[index].name) {
                case "Coin":
                    gp.playSE(1);
                    coins++;
                    gp.obj[index] = null;
                    break;
                case "Goal Pole":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSE(3);
                    break;
            }
        }
    }
}
