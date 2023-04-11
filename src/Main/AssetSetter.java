package Main;

import object.OBJ_Coin;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter (GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_Coin();
        gp.obj[0].worldX = 2200;
        gp.obj[0].y = 5 * gp.tileHeight;

        gp.obj[1] = new OBJ_Coin();
        gp.obj[1].worldX = 2100;
        gp.obj[1].y = 5 * gp.tileHeight;
    }
}
