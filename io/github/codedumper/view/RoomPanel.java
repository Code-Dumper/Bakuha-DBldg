package io.github.codedumper.view;

import io.github.codedumper.controller.*;
import io.github.codedumper.model.Event;
import java.awt.event.*;
import javax.swing.*;

public class RoomPanel extends FandamentalPanel {

    protected GameController controller;

    private FandamentalPanel RoomlayeredPanel;

    private int layerNumber;


    public RoomPanel() {
        RoomlayeredPanel = new FandamentalPanel();
        this.add(RoomlayeredPanel);
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
                JLayeredPane RemovelayeredPanel = (JLayeredPane) this.getParent();
                RemovelayeredPanel.remove(this);
                RemovelayeredPanel.revalidate();
                RemovelayeredPanel.repaint();
                break;

            default:
                JPanel currentItemPanel = ItemPanelFactory.createItemPanel(item);
                if(currentItemPanel != null) {
                    layerNumber = super.layerNumber;

                    //デバック用
                    System.out.println("追加したPanel の層: " + layerNumber);

                    // 新しく作成したパネルをRoomlayeredPanelに追加
                    RoomlayeredPanel.add(currentItemPanel, Integer.valueOf(layerNumber++));
                    // 再描画
                    RoomlayeredPanel.revalidate();
                    RoomlayeredPanel.repaint();
                }
        }
    }
}
