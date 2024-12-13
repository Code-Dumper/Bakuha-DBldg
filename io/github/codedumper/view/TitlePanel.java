package io.github.codedumper.view;

import javax.swing.*;
import java.awt.*;

public class TitlePanel extends JPanel {
    JPanel p1 = new JPanel(null);

    public TitlePanel() {
        JLabel b1 = new JLabel();
        try {
            ImageIcon icon1 = new ImageIcon(getClass().getResource("/io/github/codedumper/view/title.jpg"));
            b1.setIcon(icon1);
            b1.setPreferredSize(new Dimension(600, 200));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("画像の貼り付けに失敗しました");
        }
        b1.setBounds(0, 0, 600, 200);
        this.add(b1);
    }

    public JPanel getTitlePanel() {
        return p1;
    }
}