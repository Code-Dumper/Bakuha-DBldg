package io.github.codedumper.view;

import javax.swing.*;
import java.awt.*;

public class TimerPanel extends JPanel {
    private static final int TOTAL_SEC = 3600;
    private int remainTime = TOTAL_SEC;
    private JLabel timerLabel;

    public TimerPanel() {
        this.setLayout(new GridLayout(1, 1));
        this.setPreferredSize(new Dimension(200, 100));

        timerLabel = new JLabel(formatTime(remainTime), JLabel.CENTER);
        timerLabel.setFont(new Font("Monospaced", Font.BOLD, 50));
        this.add(timerLabel);

        new Timer(1000, e -> updateTime()).start(); // 1秒ごとに時間更新
    }

    private String formatTime(int seconds) {
        int min = seconds / 60;
        int sec = seconds % 60;
        return String.format("%02d:%02d", min, sec);
    }

    private void updateTime() {
        if (remainTime > 0) {
            remainTime--;
            timerLabel.setText(formatTime(remainTime));
        }
    }
}
