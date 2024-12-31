package io.github.codedumper.model;

import java.util.List;

import io.github.codedumper.model.planarity.Edge;

import java.awt.Point;

public interface IGameModel {
    public int getTimeRemaining();
    public void setCurrentState(Event event);
    public Event getCurrentState();
    public void onTimeChange(int newTime);
    public void onTimeOut();
    public List<Point> getNodes();
    public void moveNode(int index, Point newPosition);
    public boolean isPuzzleSolved();
    public void recreatePuzzle();
    public List<Edge> getEdges();
    public List<Edge> getIntersectingEdges();
    public void disarmBomb(Event e);
}
