package io.github.codedumper.view;

import java.awt.*;
import java.awt.image.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Button extends JPanel {
    private BufferedImage button;
    JPanel p1 = new JPanel(null);

    public Button() {
        JButton b2 = new JButton("Start");
        JButton b3 = new JButton("End");

        try {
            ImageIcon icon2 = new ImageIcon("start.jpg");
            ImageIcon icon3 = new ImageIcon("end.jpg");

            b2.setIcon(icon2);
            b3.setIcon(icon3);

            b2.setPreferredSize(new Dimension(500, 200));
            b3.setPreferredSize(new Dimension(500, 200));
        } catch (Exception e) {
            e.printStackTrace();
        }
        b2.setBounds(0, 0, 150, 50);
        b3.setBounds(0, 50, 150, 50);

        // Set transparency
        b2.setOpaque(false);
        b2.setContentAreaFilled(false);
        b2.setBorderPainted(false);

        b3.setOpaque(false);
        b3.setContentAreaFilled(false);
        b3.setBorderPainted(false);

        p1.add(b2);
        p1.add(b3);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (button != null) {
            g.drawImage(button, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public JPanel getButtonPanel() {
        return p1;
    }
}