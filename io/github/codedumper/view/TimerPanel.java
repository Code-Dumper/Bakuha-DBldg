package io.github.codedumper.view;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class TimerPanel extends JPanel {
    private JLabel timerLabel;
    public TimerPanel(double remainSeconds) {
        this.setLayout(null); //絶対位置指定レイアウトへ
        this.setSize(new Dimension(100, 50));//TODO 保守性が最悪
        this.setBackground(Color.BLACK);
        timerLabel = new JLabel(formatTime(remainSeconds)); //残り時間の指定
        timerLabel.setBounds(0,0,100,50);
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        timerLabel.setVerticalAlignment(JLabel.CENTER);
        timerLabel.setFont(createFont("DS-DIGI.TTF", 26)); //フォントの指定 
        timerLabel.setForeground(Color.RED);
        this.add(timerLabel);
    }

    private String formatTime(double seconds) {
        int min = (int)seconds / 60;
        int sec = (int)seconds % 60;
        int milsec = (int)((seconds - min * 60 - sec) * 100);
        return String.format("%02d:%02d:%02d", min, sec,milsec);
    }

    public void updateTime(double time) { //時間の更新
        timerLabel.setText(formatTime(time));
    }

    //ここにいるべきではないかもしれない
    public Font createFont(String filename, float size){
        Font font = null;
        InputStream is = null;
        try{
            is = getClass().getResourceAsStream("DS-DIGI.TTF");
            font = Font.createFont(Font.TRUETYPE_FONT,is);
            font = font.deriveFont(size);
            is.close();
        }catch(IOException e){
            e.printStackTrace();
        }catch(FontFormatException e){
            e.printStackTrace();
        }
        return font;
        
    }
}
