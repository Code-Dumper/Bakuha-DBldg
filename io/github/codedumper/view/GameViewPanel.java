package io.github.codedumper.view;

import io.github.codedumper.model.*;
import io.github.codedumper.controller.*;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;

@SuppressWarnings("deprecation")
public class GameViewPanel extends JPanel implements Observer {
    private JLabel timerLabel;
    private JPanel currentPanel;
    private GameModel model;
    private GameController controller;

    public GameViewPanel(GameModel model, GameController controller) {
        this.model = model;
        this.controller = controller;
        model.addObserver(this); // Observerに登録

        timerLabel = new JLabel("残り時間: " + model.getTimeRemaining() + "秒");
        add(timerLabel, "North"); // ラベルを北側に配置

        currentPanel = PanelFactory.createPanel(model.getCurrentState());
        System.out.println("" + model.getCurrentState());
        add(currentPanel, "Center");
    }

    @Override
    public void update(Observable o, Object arg) {
        //modelが時間変化、状態変化で送るメッセージ区別しているのでそこを区別して書く
        if (o instanceof GameModel) {
            // 残り時間を更新
            if("TIME_CHANGE".equals(arg)){
                timerLabel.setText("残り時間: " + model.getTimeRemaining() + "秒");
            }else if("STATE_CHANGE".equals(arg)){
                // 状態に応じてパネルを切り替え
                remove(currentPanel);
                currentPanel = PanelFactory.createPanel(model.getCurrentState());
                add(currentPanel, "Center");
                revalidate();
                repaint();
            }
            
        }
    }
}