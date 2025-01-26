package io.github.codedumper.view;

import io.github.codedumper.controller.*;
import io.github.codedumper.model.Event;
import java.awt.event.*;

/**
 * BasePanelは、各状態の画面を構成する基本的なパネルの親クラスです。
 * このクラスは状態遷移のための仕組みを提供します。
 * 
 * <p>特に以下の特徴があります:
 * <ul>
 *   <li>GameControllerとの連携を容易にするための構造</li>
 * </ul>
 * 
 * <p>このクラスは直接使用されることは想定されておらず、
 * サブクラス化されて特定の画面（状態）を構成するために利用されます。
 */
public class BasePanel extends FundamentalPanel {

    /**
     * 
     * @param controller ボタンの状態遷移を管理するためのGameController。
     */
    public BasePanel(GameController controller) {
        super(controller);
    }
   
    /**
     * ボタンがクリックされた際に呼び出されるメソッド。
     * アクションコマンドから状態を取得し、
     * GameControllerに遷移先を通知します。
     * 
     * @param e ボタンのアクションイベント。
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // アクションコマンド（状態名）を取得
        String state = e.getActionCommand();
        System.out.println(state);

        // アクションコマンドをEventに変換
        Event destination = Event.valueOf(state);

        // GameControllerに遷移先を通知
        controller.transition(destination);
    }
}
