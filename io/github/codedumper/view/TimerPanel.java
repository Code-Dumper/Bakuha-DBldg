package io.github.codedumper.view;


import javax.swing.*;

import io.github.codedumper.controller.GameController;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class TimerPanel extends JPanel {
    private JLabel timerLabel;
    private GameController controller;
    int min, sec, milsec;
    public TimerPanel(double remainSeconds, GameController controller) {
        this.setLayout(null); //絶対位置指定レイアウトへ
        this.setSize(new Dimension(100, 50));//TODO 保守性が最悪
        this.setBackground(Color.BLACK);
        this.controller = controller;
        timerLabel = new JLabel(formatTime(remainSeconds)); //残り時間の指定
        min = 60;
        sec = 0;
        milsec = 0;
        timerLabel.setBounds(0,0,100,50);
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        timerLabel.setVerticalAlignment(JLabel.CENTER);
        timerLabel.setFont(controller.getFont("DS-DIGI.TTF", 30));
        timerLabel.setForeground(Color.RED);
        this.add(timerLabel);
    }

    private String formatTime(double seconds) {
        min = (int)seconds / 60;
        sec = (int)seconds % 60;
        milsec = (int)((seconds - min * 60 - sec) * 100);
        return String.format("%02d:%02d:%02d", min, sec,milsec);
    }

    public void updateTime(double time) { //時間の更新
        if(min % 10 == 0 && sec == 0){
            this.setBackground(Color.RED);
            timerLabel.setForeground(Color.BLACK);
        }else{
            this.setBackground(Color.BLACK);
            timerLabel.setForeground(Color.RED);
        }
        timerLabel.setText(formatTime(time));
    }
}
