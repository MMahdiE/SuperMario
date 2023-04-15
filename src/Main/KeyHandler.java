package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;

    public boolean up, right, left;
    boolean checkDrawTime = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_UP) {
            up = true;
        }
        if(keyCode == KeyEvent.VK_RIGHT) {
            right = true;
        }
        if(keyCode == KeyEvent.VK_LEFT) {
            left = true;
        }
        if(keyCode == KeyEvent.VK_P) {
            if(gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
            }
            else if(gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
            }
        }

        //debug
        if(keyCode == KeyEvent.VK_T) {
            if(checkDrawTime == false) {
                checkDrawTime = true;
            }
            else if(checkDrawTime == true) {
                checkDrawTime = false;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_UP) {
            up = false;
        }
        if(keyCode == KeyEvent.VK_RIGHT) {
            right = false;
        }
        if(keyCode == KeyEvent.VK_LEFT) {
            left = false;
        }
    }
}
