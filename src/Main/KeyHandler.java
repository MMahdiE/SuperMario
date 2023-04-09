package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean up, down, right, left;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_UP) {
            up = true;
        }
        if(keyCode == KeyEvent.VK_DOWN) {
            down = true;
        }
        if(keyCode == KeyEvent.VK_RIGHT) {
            right = true;
        }
        if(keyCode == KeyEvent.VK_LEFT) {
            left = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_UP) {
            up = false;
        }
        if(keyCode == KeyEvent.VK_DOWN) {
            down = false;
        }
        if(keyCode == KeyEvent.VK_RIGHT) {
            right = false;
        }
        if(keyCode == KeyEvent.VK_LEFT) {
            left = false;
        }
    }
}
