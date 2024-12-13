package io.github.codedumper.view;
import javax.swing.*;
import java.awt.*;

class TimerFrame extends JFrame {
    private static final int totalsec = 3600;
    private int remaintime = totalsec;

    public TimerFrame(){
        this.setSize(200,100);
        this.setLayout(new GridLayout(1,1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel p1 = new JLabel(formatTime(remaintime),JLabel.CENTER);
        p1.setFont(new Font("Monospaced",Font.BOLD,50));
        this.add(p1);

        this.setVisible(true);
    }

    private String formatTime(int seconds){
        int min = seconds / 60;
        int sec = seconds % 60;
        return String.format("%02d:%02d",min,sec);
    }

    public static void main(String argv[]){
        new TimerFrame();
    }
}