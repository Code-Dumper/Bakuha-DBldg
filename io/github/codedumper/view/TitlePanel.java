package io.github.codedumper.view;

import javax.swing.*;
import java.awt.*;

public class TitlePanel extends JPanel {
    JLayeredPane layeredPane = new JLayeredPane();
    
    public TitlePanel() {
        // メインレイアウトを設定
        setLayout(new BorderLayout());

        // 背景画像のパネル設定
        JLabel b1 = new JLabel();
        try {
            ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/io/github/codedumper/view/d.jpg"));
            if (backgroundIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                b1.setIcon(backgroundIcon);
                b1.setBounds(0, 0, 800, 600); // フレームの範囲内に配置
                layeredPane.add(b1, Integer.valueOf(0)); // 背景として追加
            } else {
                System.out.println("背景画像の読み込みに失敗しました");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("背景画像の貼り付けに失敗しました");
        }

        // メニュー画像のパネル設定
        JLabel title = new JLabel();
        try {
            ImageIcon titleIcon = new ImageIcon(getClass().getResource("/io/github/codedumper/view/title.jpg"));
            if (titleIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                title.setIcon(titleIcon);
                title.setBounds(100, 100, 600, 600); // 背景の中心に配置する
                layeredPane.add(title, Integer.valueOf(1)); // メニューとして追加
            } else {
                System.out.println("タイトル画像の読み込みに失敗しました");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("タイトル画像の貼り付けに失敗しました");
        }

        this.add(layeredPane, BorderLayout.CENTER);
    }

    public JPanel getTitlePanel() {
        return this;
    }
}
