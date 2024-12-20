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
        this.setLayout(null);
        this.model = model;
        this.controller = controller;
        model.addObserver(this); // Observerに登録

        //TODO 仮の設計として、タイマーは50*800のJLabelとなっている。ここを本来のパネルに変えるべき。
        timerLabel = new JLabel("残り時間: " + model.getTimeRemaining() + "秒");
        timerLabel.setBounds(0, 0, 50, 800);
        this.add(timerLabel); // ラベルを北側に配置

        currentPanel = PanelFactory.createPanel(model.getCurrentState(), controller);
        currentPanel.setBounds(0,0,550,800);
        this.add(currentPanel);
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
                currentPanel = PanelFactory.createPanel(model.getCurrentState(), controller);
                currentPanel.setBounds(0,50,550,800);
                add(currentPanel);
                revalidate();
                repaint();
            }
            
        }
    }
}