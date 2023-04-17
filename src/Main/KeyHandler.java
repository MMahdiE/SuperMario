package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;

    public boolean up, down, right, left;
    boolean checkDrawTime = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        //Title state
        if(gp.gameState == 0) {
            if (keyCode == KeyEvent.VK_UP) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum == 3) {
                    gp.ui.commandNum = 0;
                }
            }
            if (keyCode == KeyEvent.VK_DOWN) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum == -1) {
                    gp.ui.commandNum = 2;
                }
            }
            if (keyCode == KeyEvent.VK_ENTER) {
                if(gp.ui.commandNum == 0) {
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }
                else if(gp.ui.commandNum == 1) {

                }
                else if(gp.ui.commandNum == 2) {
                    System.exit(0);
                }
            }
        }

        //game state
        else if(gp.gameState == gp.playState) {
            if (keyCode == KeyEvent.VK_UP) {
                up = true;
            }
            if (keyCode == KeyEvent.VK_RIGHT) {
                right = true;
            }
            if (keyCode == KeyEvent.VK_LEFT) {
                left = true;
            }
            if (keyCode == KeyEvent.VK_P) {
                gp.gameState = gp.pauseState;
            }
        }
        //pause state
        else if(gp.gameState == gp.pauseState) {
            if (keyCode == KeyEvent.VK_P) {
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
