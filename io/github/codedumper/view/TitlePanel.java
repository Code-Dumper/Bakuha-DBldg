package io.github.codedumper.view;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TitlePanel extends JPanel {
    private BufferedImage title;
    JPanel p1 = new JPanel(null);

    public TitlePanel() {
        JLabel b1 = new JLabel();
        try {
            ImageIcon icon1 = new ImageIcon("title1.jpg");
            b1.setIcon(icon1);
            b1.setPreferredSize(new Dimension(600, 200));
        } catch (Exception e) {
            e.printStackTrace();
        }
        b1.setBounds(0, 0, 600, 200);
        p1.add(b1);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (title != null) {
            g.drawImage(title, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public JPanel getTitlePanel() {
        return p1;
    }
}