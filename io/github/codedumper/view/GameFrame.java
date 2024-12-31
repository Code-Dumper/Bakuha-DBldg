package io.github.codedumper.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import io.github.codedumper.controller.*;
import io.github.codedumper.model.*;

class GameFrame extends JFrame {
    GameModel model;
    GameController controller;
    GameViewPanel view;
    public GameFrame() {
        model = new GameModel();
        controller = new GameController(model);
        view = new GameViewPanel(model, controller);
        controller.addView(view);
        this.setSize(600, 800);
        this.add(view);
        this.addWindowListener(new WindowClosing());
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
    }
    public class WindowClosing extends WindowAdapter{
        public void windowClosing(WindowEvent e){
            JOptionPane.showMessageDialog(GameFrame.this, "逃げるわけにはいかない。");
        }
    }
    public static void main(String argv[]) {
        new GameFrame();
    }
}