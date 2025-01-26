package io.github.codedumper.view;

import io.github.codedumper.controller.*;
import io.github.codedumper.model.Event;
import java.awt.event.*;
import javax.swing.*;

public class RoomPanel extends FundamentalPanel {

    public RoomPanel(GameController controller) {
        super(controller);
    }
    /**
     * 小道具を表示している現在のPanelを削除する。
     * または小道具にズームインするパネルを作成する。
     * 
     * @param e ボタンのアクションイベント。
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // アクションコマンド（アイテム名）を取得
        String item = e.getActionCommand();
        System.out.println(item);
        switch (item) {
            case "lobby":
                controller.transition(Event.STATE_LOBBY);
                break;

            case "delete":
                this.deleteTopPanelOf(this);
                break;

            default:
                JPanel currentItemPanel = ItemPanelFactory.createItemPanel(item, controller);
                if(currentItemPanel != null) {
                    this.stackPanel(currentItemPanel);            
                }
                break;
        }
    }
}
