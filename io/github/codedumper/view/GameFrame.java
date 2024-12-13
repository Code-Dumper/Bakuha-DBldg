package io.github.codedumper.view;

import javax.swing.JFrame;

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
        this.setSize(800, 600);
        this.add(view);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public static void main(String argv[]) {
        new GameFrame();
    }
}