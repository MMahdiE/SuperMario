package Main;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font maruMonica;
    Font arial_40, arial_80B;
    public boolean gameFinished = false;
    public int commandNum = 0;
    public int titleScreenState = 0;
    double time = 60.0;

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);

        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(maruMonica);

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

//        //title state
//        if(gp.gameState == gp.titleState) {
//            drawTitleScreen();
//        }
        //game state
        if(gp.gameState == gp.playState) {

        }
        //pause state
        else if(gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }

        g2.dispose();
    }

    public void drawPauseScreen() {

        String text;
        int x;
        int y;

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        g2.setColor(Color.WHITE);
        text = "PAUSED";
        x = getXForCenterText(text);
        y = gp.screenHeight/2;
        g2.drawString(text, x, y);
    }

//    public void drawTitleScreen() {
//
//        String text;
//        int x, y;
//
//        if(titleScreenState == 0) {
//            //title name
//            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 100F));
//            text = "Super Mario";
//            x = getXForCenterText(text);
//            y = gp.tileHeight * 2;
//
//            //shadow
//            g2.setColor(Color.GRAY);
//            g2.drawString(text, x + 3, y + 3);
//
//            //main text
//            g2.setColor(Color.WHITE);
//            g2.drawString(text, x, y);
//
//            //Menu
//            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
//
//            text = "SIGN IN";
//            x = getXForCenterText(text);
//            y += gp.tileHeight * 3;
//            g2.drawString(text, x, y);
//            if (commandNum == 0) {
//                g2.drawString(">", x - gp.tileWidth, y);
//            }
//
//            text = "SIGN UP";
//            x = getXForCenterText(text);
//            y += gp.tileHeight;
//            g2.drawString(text, x, y);
//            if (commandNum == 1) {
//                g2.drawString(">", x - gp.tileWidth, y);
//            }
//
//            text = "QUITE";
//            x = getXForCenterText(text);
//            y += gp.tileHeight;
//            g2.drawString(text, x, y);
//            if (commandNum == 2) {
//                g2.drawString(">", x - gp.tileWidth, y);
//            }
//        }
//        else if(titleScreenState == 1) {
//            //Sign in screen
//            g2.setColor(Color.WHITE);
//            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 44F));
//
//            //text = ""
//        }
//    }

    public int getXForCenterText(String text) {

        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - textLength/2;

        return x;
    }
}
