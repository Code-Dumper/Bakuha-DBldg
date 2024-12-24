package io.github.codedumper.controller;

import java.awt.event.MouseAdapter;

import io.github.codedumper.model.*;
import io.github.codedumper.view.GameViewPanel;
public class GameController extends MouseAdapter{

    protected GameModel model;
    private GameViewPanel view;
    public GameController(GameModel model, GameViewPanel view) {
        this.model = model;
        this.view = view;
        view.addMouseListener(this);
        view.addMouseMotionListener(this);
    }

    public void transition(Event event) {
        model.setCurrentState(event);
    }

    
}