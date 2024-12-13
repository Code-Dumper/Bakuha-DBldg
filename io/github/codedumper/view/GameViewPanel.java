package io.github.codedumper.view;

import io.github.codedumper.model.*;
import io.github.codedumper.controller.*;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;
import java.awt.*;

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

        // タイマーのラベルを設定
        timerLabel = new JLabel("残り時間: " + model.getTimeRemaining() + "秒");

        // メインパネルのレイアウト設定
        setLayout(new BorderLayout());

        // タイマーを北側に配置
        add(timerLabel, BorderLayout.NORTH);

        // 現在のパネルを中央に配置
        currentPanel = PanelFactory.createPanel(model.getCurrentState());
        add(currentPanel, BorderLayout.CENTER);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof GameModel) {
            if ("TIME_CHANGE".equals(arg)) {
                timerLabel.setText("残り時間: " + model.getTimeRemaining() + "秒");
            } else if ("STATE_CHANGE".equals(arg)) {
                // 状態に応じてパネルを切り替える
                remove(currentPanel);
                currentPanel = PanelFactory.createPanel(model.getCurrentState());
                add(currentPanel, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        }
    }
}
