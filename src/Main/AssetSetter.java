package Main;

import Entity.Goomba;
import Entity.PiranhaPlant;
import object.OBJ_Coin;
import object.OBJ_GoalPole;
import object.OBJ_MysteryBlock;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter (GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_Coin(gp);
        gp.obj[0].worldX = 2200;
        gp.obj[0].y = 5 * gp.tileHeight;

        gp.obj[1] = new OBJ_Coin(gp);
        gp.obj[1].worldX = 2100;
        gp.obj[1].y = 5 * gp.tileHeight;

        gp.obj[2] = new OBJ_MysteryBlock(gp);
        gp.obj[2].worldX = 48 * gp.tileWidth;
        gp.obj[2].y = 6 * gp.tileHeight;

        gp.obj[3] = new OBJ_MysteryBlock(gp);
        gp.obj[3].worldX = 54 * gp.tileWidth;
        gp.obj[3].y = 5 * gp.tileHeight;

        gp.obj[4] = new OBJ_GoalPole(gp);
        gp.obj[4].worldX = 103 * gp.tileWidth;
        gp.obj[4].y = 0;
    }

    public void setEnemy() {

        gp.enemies[0] = new PiranhaPlant(gp);
        gp.enemies[0].worldX =(int) (gp.tileWidth * 44.5);
        gp.enemies[0].y = 9 * gp.tileHeight;
        gp.enemies[0].setDefaultValues();

        gp.enemies[1] = new Goomba(gp);
        gp.enemies[1].worldX = 55 * gp.tileWidth ;
        gp.enemies[1].y = 9 * gp.tileHeight;
    }
}
