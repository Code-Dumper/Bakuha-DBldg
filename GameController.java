package io.github.codedumper.controller;

class GameController {
    protected GameModel model;
    public GameController(GameModel model) {
        this.model = model;
    }
    public Transition(Event event) {
        model.setCurrentState(event);
    }
}