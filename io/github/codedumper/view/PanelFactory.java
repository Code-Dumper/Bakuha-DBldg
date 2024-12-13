package io.github.codedumper.view;
import javax.swing.*;

import io.github.codedumper.model.Event;


/**
 * 状態に対応するPanelを作成するUtilのclass.
 * 使い方の例:
 * (Viewの中でパネルを切り替える際に)
 * currentPanel = PanelFactory.createPanel(model.getCurrentState());
 * ...
 */
class PanelFactory{
    public static JPanel createPanel(Event event){
        switch(event){
            case STATE_TITLE:
                return new TitlePanel();
            case STATE_LOBBY:
                return new LobbyPanel();
            case STATE_END:
                return new EndPanel();
            case STATE_1F:
                return new FloorOnePanel();
            case STATE_2F:
                return new FloorTwoPanel();
            case STATE_3F:
                return new FloorThreePanel();
            case STATE_4F:
                return new FloorFourPanel();
            case STATE_GAMEOVER:
                return new GameOverPanel();
            default:
                return new TitlePanel();
        }
    }
}