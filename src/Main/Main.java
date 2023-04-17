package Main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SuperMarioFrame frame = new SuperMarioFrame();

        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);

        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        gamePanel.setUpGame();
        gamePanel.gameThread.start();
    }
}