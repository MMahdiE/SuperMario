package Main;

import javax.swing.*;

public class SuperMarioFrame extends JFrame {

    public SuperMarioFrame() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("SuperMario");
        ImageIcon icon = new ImageIcon("res/Player/jump.png");
        this.setIconImage(icon.getImage());
    }
}
