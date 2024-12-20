package io.github.codedumper.view;

import javax.swing.*;
import java.awt.*;

public class TimerPanel extends JPanel {
    private JLabel timerLabel;
    public TimerPanel(int remainSeconds) {
        this.setLayout(null); //絶対位置指定レイアウトへ
        this.setSize(new Dimension(100, 50));//TODO 保守性が最悪
        timerLabel = new JLabel(formatTime(remainSeconds)); //残り時間の指定
        timerLabel.setBounds(0,0,100,50);
        timerLabel.setFont(new Font("Monospaced", Font.BOLD, 20)); //フォントの指定
        this.add(timerLabel);
    }

    private String formatTime(int seconds) {
        int min = seconds / 60;
        int sec = seconds % 60;
        return String.format("%02d分%02d秒", min, sec);
    }

    public void updateTime(int time) { //時間の更新
        timerLabel.setText(formatTime(time));
    }
}
