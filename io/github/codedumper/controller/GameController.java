package io.github.codedumper.controller;

import io.github.codedumper.model.*;
public class GameController {
    protected GameModel model;
    public GameController(GameModel model) {
        this.model = model;
    }
    public void transition(Event event) {
        model.setCurrentState(event);
    }
}