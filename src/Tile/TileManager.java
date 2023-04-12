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
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/blue-sky.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ground-brown.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/brick-brown.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/pipe01.png"));
            tile[3].collision = true;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/pipe02.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/pipe03.png"));
            tile[5].collision = true;

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/pipe04.png"));
            tile[6].collision = true;

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/brick-brown-top-white.png"));
            tile[7].collision = true;

        } catch (IOException e)  {
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
                    g2.drawImage(tile[mapTileNum[worldCol][row]].image, screenX, y, gp.tileWidth, gp.tileHeight, null);
                }
                worldCol++;
            }
            worldCol = 0;
            row++;
            y += gp.tileHeight;
        }
    }
}
