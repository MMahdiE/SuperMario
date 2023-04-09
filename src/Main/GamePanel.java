package Main;

import Entity.Player;
import Tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //Tile settings
    final int originalTileWidth = 12;
    final int originalTileHeight = 16;
    final int scale = 3;
    public final int tileWidth = originalTileWidth * scale;
    public final int tileHeight = originalTileHeight * scale;

    //Screen settings
    public final int maxScreenCol = 26;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileWidth * maxScreenCol;
    public final int screenHeight = tileHeight * maxScreenRow;

    //World settings
    public final int maxWorldCol = 104;
    public final int worldWidth = tileWidth * maxWorldCol;

    public final int FPS = 60;

    Thread thread;
    KeyHandler key = new KeyHandler();
    public Player player = new Player(this, key);
    TileManager tileManager = new TileManager(this);


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);

        this.addKeyListener(key);
        this.setFocusable(true);
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(thread != null) {
            update();

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if(remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        player.update();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        //It's important what you draw first!!!!!
        tileManager.draw(g2);
        player.draw(g2);

        g2.dispose();
    }
}