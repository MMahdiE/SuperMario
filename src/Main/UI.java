package Main;

import java.awt.*;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    public boolean gameFinished = false;
    double time = 60.0;

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);

    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;

//        if(gameFinished) {
//
//            g2.setFont(arial_40);
//            g2.setColor(Color.WHITE);
//            String text;
//            int textLength;
//            int x;
//            int y;
//
//            g2.setFont(arial_40);
//            g2.setColor(Color.WHITE);
//            text = "You reached the goal pole!";
//            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//            x = gp.screenWidth/2 - textLength/2;
//            y = gp.screenHeight/2 - (gp.tileHeight*3);
//            g2.drawString(text, x, y);
//
//            g2.setFont(arial_80B);
//            g2.setColor(Color.YELLOW);
//            text = "CONGRATULATIONS!";
//            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//            x = gp.screenWidth/2 - textLength/2;
//            y = gp.screenHeight/2 + (gp.tileHeight*2);
//            g2.drawString(text, x, y);
//
//            gp.gameThread = null;
//        }
//        else {
//            time -= (double) 1 / gp.FPS;
//            g2.setFont(arial_40);
//            g2.setColor(Color.WHITE);
//            g2.drawString("COINS: " + gp.player.coins, 40, 40);
//            g2.drawString("LIVES: " + gp.player.lives, 400, 40);
//            g2.drawString("Time: " + (int)time, 800, 40);
//        }

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        if(gp.gameState == gp.playState) {

        }
        else if(gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
    }

    public void drawPauseScreen() {

        String text;
        int x;
        int y;

        g2.setFont(arial_80B);
        g2.setColor(Color.WHITE);
        text = "PAUSED";
        x = getXForCenterText(text);
        y = gp.screenHeight/2;
        g2.drawString(text, x, y);
    }

    public int getXForCenterText(String text) {

        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - textLength/2;

        return x;
    }
}
