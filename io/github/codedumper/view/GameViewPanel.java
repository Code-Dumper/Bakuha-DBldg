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
    private GameController controller;

    public GameViewPanel(GameController controller) {
        this.setLayout(null);
        this.setSize(600, 800);
        this.controller = controller;
        this.controller.getModel().addObserver(this); // Observerに登録

        //タイマーの下にパネルを画面いっぱいに表示する。
        this.currentPanel = PanelFactory.createPanel(controller.getModel().getCurrentState(), controller);

        //TODO タイマーパネルの位置指定を直接書いているので保守性が低いかもしれない
        //タイマーパネルを左上に追加する
        this.timerPanel = new TimerPanel(controller.getModel().getTimeRemaining(), controller);
        this.timerPanel.setBounds(0,0,100,50);
        this.add(timerPanel);
        this.add(currentPanel);

        
        System.out.println("GameViewPanelのコンストラクタの実行が終了しました");
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof GameModel) {
            // 残り時間を更新
            if("TIME_CHANGE".equals(arg)){
                if(this.timerPanel == null){
                    this.timerPanel = new TimerPanel(this.controller.getModel().getTimeRemaining(), controller);
                }else{
                    this.timerPanel.updateTime(controller.getModel().getTimeRemaining());
                }
            }else if("STATE_CHANGE".equals(arg)){
                // 状態に応じてパネルを切り替え
                remove(currentPanel);
                currentPanel = PanelFactory.createPanel(controller.getModel().getCurrentState(), controller);
                this.timerPanel.updateTime(controller.getModel().getTimeRemaining());
                add(currentPanel);
                revalidate();
                repaint();
            }
            
        }
    }
}