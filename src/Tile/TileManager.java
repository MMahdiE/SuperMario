package Tile;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10];

        mapTileNum = new int[gp.maxWorldRow][gp.maxWorldCol];
        loadMap("/maps/map01.txt");

        getTileImage();
    }

    void loadMap(String filePath) {
        try{
            InputStream is = getClass().getResourceAsStream(filePath );
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(row < gp.maxWorldRow) {
                String line = br.readLine();
                String[] numbers = line.split(" ");

                while(col < gp.maxWorldCol) {
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[row][col] = num;

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
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/blue-sky.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ground-brown.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/brick-brown.png"));
        } catch (IOException e)  {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while(worldRow < gp.maxWorldRow) {
            while(worldCol < gp.maxWorldCol) {

                int worldX = worldCol * gp.tileWidth;
                int worldY = worldRow * gp.tileHeight;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                g2.drawImage(tile[mapTileNum[worldRow][worldCol]].image, screenX, screenY, gp.tileWidth, gp.tileHeight, null);
                worldCol++;
            }
            worldCol = 0;
            worldRow++;
        }
    }
}
