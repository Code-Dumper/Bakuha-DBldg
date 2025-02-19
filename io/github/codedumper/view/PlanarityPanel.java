package io.github.codedumper.view;

import java.util.*;
import java.util.List;

import io.github.codedumper.controller.GameController;
import io.github.codedumper.model.*;
import io.github.codedumper.model.planarity.*;
import java.awt.*;

@SuppressWarnings("deprecation")
public class PlanarityPanel extends BasePanel implements Observer{
    public PlanarityPanel(GameController controller){
        super(controller);
        this.setBackground(Color.GRAY);
        this.createButtonWithImage(State.STATE_1F, "io/github/codedumper/view/image-Direction-Rear.jpg", 0, 600, 100, 100);
        createButton(State.STATE_1F, new ButtonProperties(0, 600, 100, 100));
        controller.getModel().addObserver(this);
    }

    @Override 
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        //エッジの描画
        List<Edge> edges = controller.getEdges();
        for(Edge edge : edges){
            Point start = controller.getNodes().get(edge.getStartIndex());
            Point end = controller.getNodes().get(edge.getEndIndex());
            g.setColor(Color.BLACK);
            g.drawLine((int)start.getX(), (int)start.getY(), (int)end.getX(), (int)end.getY());
        }

        //交差
        List<Edge> intersectingEdges = controller.getIntersectingEdges();
        for(Edge edge : intersectingEdges){
            Point start = controller.getNodes().get(edge.getStartIndex());
            Point end = controller.getNodes().get(edge.getEndIndex());
            g.setColor(Color.RED);
            g.drawLine((int)start.getX(), (int)start.getY(), (int)end.getX(), (int)end.getY());
        }

        for (Point node : controller.getNodes()) {
            g.setColor(Color.BLUE);
            g.fillOval((int)node.getX() - 5, (int)node.getY() - 5, 10, 10);
        }
    }

    

    @Override
    public void update(Observable o, Object arg){
            repaint();
            if(controller.isPuzzleSolved()){
                addLabel("パズルが解除された。",250,500,200,100,Color.BLACK);
                addLabel("爆弾の鍵が解除されます...",250,550,200,100,Color.BLACK);
            }
    }
}