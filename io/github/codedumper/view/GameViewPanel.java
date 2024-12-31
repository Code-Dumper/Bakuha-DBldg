package io.github.codedumper.view;

import io.github.codedumper.model.*;
import io.github.codedumper.controller.*;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;

@SuppressWarnings("deprecation")
public class GameViewPanel extends JPanel implements Observer {
    private TimerPanel timerPanel;
    private JPanel currentPanel;
    private GameModel model;
    private GameController controller;

    public GameViewPanel(GameModel model, GameController controller) {
        this.setLayout(null);
        this.setSize(600, 800);
        this.model = model;
        this.controller = controller;
        this.model.addObserver(this); // Observerに登録

        //タイマーの下にパネルを画面いっぱいに表示する。
        this.currentPanel = PanelFactory.createPanel(model.getCurrentState(), model, controller);
        this.add(currentPanel);

        //TODO タイマーパネルの位置指定を直接書いているので保守性が低いかもしれない
        //タイマーパネルを左上に追加する
        this.timerPanel = new TimerPanel(this.model.getTimeRemaining());
        this.timerPanel.setBounds(0,0,100,50);
        this.add(timerPanel);

        
        System.out.println("GameViewPanelのコンストラクタの実行が終了しました");
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof GameModel) {
            // 残り時間を更新
            if("TIME_CHANGE".equals(arg)){
                if(this.timerPanel == null){
                    this.timerPanel = new TimerPanel(this.model.getTimeRemaining());
                }else{
                    this.timerPanel.updateTime(this.model.getTimeRemaining());
                }
            }else if("STATE_CHANGE".equals(arg)){
                // 状態に応じてパネルを切り替え

                if(model.getCurrentState() == Event.STATE_END){
                    System.exit(0);
                }

                remove(currentPanel);
                currentPanel = PanelFactory.createPanel(model.getCurrentState(), model, controller);
                add(currentPanel);
                revalidate();
                repaint();
            }
            
        }
    }
}