package Tile;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[11];

        mapTileNum = new int[gp.maxWorldCol][gp.maxScreenRow];
        loadMap("/maps/map01.txt");

        getTileImage();
    }

    void loadMap(String filePath) {
        try{
            InputStream is = getClass().getResourceAsStream(filePath );
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(row < gp.maxScreenRow) {
                String line = br.readLine();
                String[] numbers = line.split(" ");

                while(col < gp.maxWorldCol) {
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;

                    col++;
                }
                col = 0;
                row++;
            }
            br.close();

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    void getTileImage() {

        setup(0, "blue-sky", false);
        setup(1, "ground-brown", true);
        setup(2, "brick-brown", true);
        setup(3, "pipe01", true);
        setup(4, "pipe02", true);
        setup(5, "pipe03", true);
        setup(6, "pipe04", true);
        setup(7, "brick-brown-top-white", true);
        setup(8, "goal-pole03", true);
    }

    public void setup(int index, String imageName, boolean collision) {

        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileWidth, gp.tileHeight);
            tile[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int row = 0;
        int y = 0;

        while(row < gp.maxScreenRow) {
            while(worldCol < gp.maxWorldCol) {

                int worldX = worldCol * gp.tileWidth;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;

                if(worldX + gp.tileWidth > gp.player.worldX - gp.player.screenX && worldX - gp.tileWidth < gp.player.worldX + gp.player.screenX) {
                    if(tile[mapTileNum[worldCol][row]].collision == false) {
                        g2.drawImage(tile[mapTileNum[worldCol][row]].image, screenX, y, null);
                    }
                }
                worldCol++;
            }
            worldCol = 0;
            row++;
            y += gp.tileHeight;
        }
    }

    public void drawWithCollision(Graphics2D g2) {
        int worldCol = 0;
        int row = 0;
        int y = 0;

        while(row < gp.maxScreenRow) {
            while(worldCol < gp.maxWorldCol) {

                int worldX = worldCol * gp.tileWidth;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;

                if(worldX + gp.tileWidth > gp.player.worldX - gp.player.screenX && worldX - gp.tileWidth < gp.player.worldX + gp.player.screenX) {
                    if(tile[mapTileNum[worldCol][row]].collision == true) {
                        g2.drawImage(tile[mapTileNum[worldCol][row]].image, screenX, y, null);
                    }
                }
                worldCol++;
            }
            worldCol = 0;
            row++;
            y += gp.tileHeight;
        }
    }
}
