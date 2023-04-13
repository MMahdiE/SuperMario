package Main;

import Entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTileHorizontal(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.y + entity.solidArea.y;
        int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftWorldCol;
        int entityRightWorldCol;
        int entityTopRow = entityTopY / gp.tileHeight;
        int entityBottomRow = entityBottomY / gp.tileHeight;

        int tileNum1, tileNum2;

        if(gp.player.velocityHorizontal < 0) {
            entityLeftWorldX += (int) entity.velocityHorizontal;
            if(entityLeftWorldX < 0) {
                entity.collisionHorizontalOn = true;
            }
            else {
                entityLeftWorldCol = entityLeftWorldX / gp.tileWidth;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftWorldCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityLeftWorldCol][entityBottomRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    entity.collisionHorizontalOn = true;
                }
            }
        }
        else if(gp.player.velocityHorizontal > 0) {
            entityRightWorldX += (int) entity.velocityHorizontal;
            if(entityRightWorldX >= gp.worldWidth) {
                entity.collisionHorizontalOn = true;
            }
            else {
                entityRightWorldCol = entityRightWorldX / gp.tileWidth;
                tileNum1 = gp.tileManager.mapTileNum[entityRightWorldCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightWorldCol][entityBottomRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    entity.collisionHorizontalOn = true;
                }
            }
        }
    }


    public void checkTileVertical(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.y + entity.solidArea.y;
        int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftWorldCol = entityLeftWorldX / gp.tileWidth;
        int entityRightWorldCol = entityRightWorldX / gp.tileWidth;
        int entityTopRow;
        int entityBottomRow;

        int tileNum1, tileNum2;

        //going down
        if(gp.player.velocityVertical < 0) {
            entityBottomRow = (entityBottomY - (int) entity.velocityVertical) / gp.tileHeight;
            tileNum1 = gp.tileManager.mapTileNum[entityLeftWorldCol][entityBottomRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightWorldCol][entityBottomRow];
                if(gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    entity.collisionVerticalOn = true;
                }
        }
        //going up
        else if(gp.player.velocityVertical > 0) {
            entityTopRow = (entityTopY - (int) entity.velocityVertical) / gp.tileHeight;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftWorldCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightWorldCol][entityTopRow];
                if(gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    entity.collisionVerticalOn = true;
                }
        }
    }

    public boolean checkIfStandingOnSth(Entity entity) {
        boolean itIs = false;

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftWorldCol = entityLeftWorldX / gp.tileWidth;
        int entityRightWorldCol = entityRightWorldX / gp.tileWidth;
        int entityBottomRow;

        int tileNum1, tileNum2;

        entityBottomRow = (entityBottomY + 1) / gp.tileHeight;
        tileNum1 = gp.tileManager.mapTileNum[entityLeftWorldCol][entityBottomRow];
        tileNum2 = gp.tileManager.mapTileNum[entityRightWorldCol][entityBottomRow];
        if(gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
            itIs = true;
        }

        return  itIs;
    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for(int i = 0; i < gp.obj.length; i++) {
            if(gp.obj[i] != null) {

                entity.solidArea.x += entity.worldX;
                entity.solidArea.y += entity.y;

                gp.obj[i].solidArea.x += gp.obj[i].worldX;
                gp.obj[i].solidArea.y += gp.obj[i].y;

                if((int) entity.velocityHorizontal != 0) {
                    entity.solidArea.x += (int) entity.velocityHorizontal;
                    if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                        if(gp.obj[i].collision) {
                            entity.collisionHorizontalOn = true;
                        }
                        if(player) {
                            index = i;
                        }
                    }
                }
                if((int) entity.velocityVertical != 0) {
                    entity.solidArea.y -= (int) entity.velocityVertical;
                    if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                        if(gp.obj[i].collision) {
                            entity.collisionVerticalOn = true;
                        }
                        if(player) {
                            index = i;
                        }
                    }
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }

        return index;
    }
}
