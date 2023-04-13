package Main;

import Entity.Player;
import Tile.TileManager;
import object.SuperObject;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //Tile settings
    final int originalTileWidth = 16;
    final int originalTileHeight = 16;
    public final int scale = 3;
    public final int tileWidth = originalTileWidth * scale;
    public final int tileHeight = originalTileHeight * scale;

    //Screen settings
    public final int maxScreenCol = 26;
    public final int maxScreenRow = 14;
    public final int screenWidth = tileWidth * maxScreenCol;
    public final int screenHeight = tileHeight * maxScreenRow;

    //World settings
    public final int maxWorldCol = 104;
    public final int worldWidth = tileWidth * maxWorldCol;
    public final int FPS = 60;

    //System
    Thread gameThread;
    KeyHandler key = new KeyHandler();
    TileManager tileManager = new TileManager(this);
    public  CollisionChecker cChecker = new CollisionChecker(this);
    AssetSetter aSetter = new AssetSetter(this);
    Sound sound = new Sound();

    //Entity and object
    public Player player = new Player(this, key);
    public SuperObject obj[] = new SuperObject[10];


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);

        this.addKeyListener(key);
        this.setFocusable(true);
        gameThread = new Thread(this);
    }

    public void setUpGame() {

        aSetter.setObject();
        playMusic(0);
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null) {
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
        //tile
        tileManager.draw(g2);

        //object
        for(int i = 0; i < obj.length; i++) {
            if(obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        //player
        player.draw(g2);

        g2.dispose();
    }

    public void playMusic(int i) {

        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {

        sound.stop();
    }

    public void playSE(int i) {

        sound.setFile(i);
        sound.play();
    }
}
