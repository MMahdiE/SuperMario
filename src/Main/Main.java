package Main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("SuperMario");
        frame.setLocationRelativeTo(null);

        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);

        frame.pack();
        frame.setVisible(true);

        gamePanel.setUpGame();
        gamePanel.thread.start();
    }
}