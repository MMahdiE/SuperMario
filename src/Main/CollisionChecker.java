package Main;

import Entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.y + entity.solidArea.y;
        int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftWorldCol = entityLeftWorldX / gp.tileWidth;
        int entityRightWorldCol = entityRightWorldX / gp.tileWidth;
        int entityTopRow = entityTopY / gp.tileHeight;
        int entityBottomRow = entityBottomY / gp.tileHeight;

        int tileNum1, tileNum2;

//        switch (gp.player.direction) {
//            case "up":
//                entityTopRow = (entityTopY - (int) entity.velocityHorizontal) / gp.tileHeight;
//                tileNum1 = gp.tileManager.mapTileNum[entityLeftWorldCol][entityTopRow];
//                tileNum2 = gp.tileManager.mapTileNum[entityRightWorldCol][entityTopRow];
//                if(gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
//                    entity.collisionOn = true;
//                }
//                break;
//            case "down":
//                entityBottomRow = (entityBottomY + (int)entity.velocityHorizontal) / gp.tileHeight;
//                tileNum1 = gp.tileManager.mapTileNum[entityLeftWorldCol][entityBottomRow];
//                tileNum2 = gp.tileManager.mapTileNum[entityRightWorldCol][entityBottomRow];
//                if(gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
//                    entity.collisionOn = true;
//                }
//                break;
//            case "right":
//                entityRightWorldCol = (entityRightWorldX + (int)entity.velocityHorizontal) / gp.tileWidth;
//                tileNum1 = gp.tileManager.mapTileNum[entityRightWorldCol][entityTopRow];
//                tileNum2 = gp.tileManager.mapTileNum[entityRightWorldCol][entityBottomRow];
//                if(gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
//                    entity.collisionOn = true;
//                }
//                break;
//            case "left":
//                entityLeftWorldCol = (entityLeftWorldX + (int)entity.velocityHorizontal) / gp.tileWidth;
//                tileNum1 = gp.tileManager.mapTileNum[entityLeftWorldCol][entityTopRow];
//                tileNum2 = gp.tileManager.mapTileNum[entityLeftWorldCol][entityBottomRow];
//                if(gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
//                    entity.collisionOn = true;
//                }
//                break;
//        }

        if(gp.player.velocityHorizontal < 0) {
            entityLeftWorldCol = (entityLeftWorldX + (int) entity.velocityHorizontal) / gp.tileWidth;
            tileNum1 = gp.tileManager.mapTileNum[entityLeftWorldCol][entityTopRow];
            tileNum2 = gp.tileManager.mapTileNum[entityLeftWorldCol][entityBottomRow];
            if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                entity.collisionOn = true;
            }
        }
        else if(gp.player.velocityHorizontal > 0) {
            entityRightWorldCol = (entityRightWorldX + (int)entity.velocityHorizontal) / gp.tileWidth;
                tileNum1 = gp.tileManager.mapTileNum[entityRightWorldCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightWorldCol][entityBottomRow];
                if(gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
        }
    }
}
