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
    
    public GameController(GameModel model, GameViewPanel view) {
        this.model = model;
        this.view = view;
        view.addMouseListener(this);
        view.addMouseMotionListener(this);
    }

    public void transition(Event event) {
        model.setCurrentState(event);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //ミニゲームでなければマウスの処理は必要ないので処理終了する
        if(model.getCurrentState() != Event.STATE_MINIGAME){
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
}