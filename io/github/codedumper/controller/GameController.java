package io.github.codedumper.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Point;

import io.github.codedumper.model.*;
import io.github.codedumper.view.GameViewPanel;
public class GameController extends MouseAdapter{

    protected GameModel model;
    private GameViewPanel view;
    //動かすノードのインデックス
    private int selectedNodeIndex = -1;
    
    public GameController(GameModel model) {
        this.model = model;
    }

    public void addView(GameViewPanel view){
        this.view = view;
        view.addMouseListener(this);
        view.addMouseMotionListener(this);
    }
    public void transition(State event) {
        model.setCurrentState(event);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //ミニゲームでなければマウスの処理は必要ないので処理終了する
        if(model.getCurrentState() != State.STATE_MINIGAME){
            return;
        }
        double minDistance = Double.MAX_VALUE;
        int closestIndex = -1;
        // ノードの選択
        for (int i = 0; i < model.getNodes().size(); i++) {
            Point node = model.getNodes().get(i);
            double distance = node.distance(e.getPoint());
            if (distance < minDistance && distance < 10) {
                minDistance = distance;
                closestIndex = i;
            }
        }
        if(closestIndex != -1){
            selectedNodeIndex = closestIndex;
        }else{
            selectedNodeIndex = -1;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // ノードの移動
        if (selectedNodeIndex != -1) {
            model.moveNode(selectedNodeIndex, e.getPoint());
            view.repaint();
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        selectedNodeIndex = -1;
    }
}