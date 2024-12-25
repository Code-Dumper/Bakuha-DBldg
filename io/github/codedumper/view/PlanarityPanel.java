package io.github.codedumper.view;

import java.util.*;
import java.util.List;

import io.github.codedumper.controller.GameController;
import io.github.codedumper.model.*;
import io.github.codedumper.model.planarity.*;
import java.awt.*;

@SuppressWarnings("deprecation")
public class PlanarityPanel extends BasePanel implements Observer{
    private GameModel model;

    public PlanarityPanel(GameModel model, GameController controller){
        super(controller);
        this.model = model;
        this.model.addObserver(this);
    }

    @Override 
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        //エッジの描画
        List<Edge> edges = model.getEdges();
        for(Edge edge : edges){
            Point start = model.getNodes().get(edge.getStartIndex());
            Point end = model.getNodes().get(edge.getEndIndex());
            g.setColor(Color.BLACK);
            g.drawLine((int)start.getX(), (int)start.getY(), (int)end.getX(), (int)end.getY());
        }

        //交差
        List<Edge> intersectingEdges = model.getIntersectingEdges();
        for(Edge edge : intersectingEdges){
            Point start = model.getNodes().get(edge.getStartIndex());
            Point end = model.getNodes().get(edge.getEndIndex());
            g.setColor(Color.RED);
            g.drawLine((int)start.getX(), (int)start.getY(), (int)end.getX(), (int)end.getY());
        }

        for (Point node : model.getNodes()) {
            g.setColor(Color.BLUE);
            g.fillOval((int)node.getX() - 5, (int)node.getY() - 5, 10, 10);
        }
    }

    @Override
    public void update(Observable o, Object arg){
        if(model.isPuzzleSolved()){
            model.disarmBomb(model.getCurrentState());
            repaint();
        }else{
            repaint();
        }
        
    }
}