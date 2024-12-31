package io.github.codedumper.view;
import javax.swing.*;

import io.github.codedumper.controller.GameController;
import io.github.codedumper.model.Event;
import io.github.codedumper.model.GameModel;


/**
 * 状態に対応するPanelを作成するUtilのclass.
 * 使い方の例:
 * (Viewの中でパネルを切り替える際に)
 * currentPanel = PanelFactory.createPanel(model.getCurrentState());
 * ...
 */
class PanelFactory{
    public static JPanel createPanel(Event event, GameModel model, GameController controller){
        JPanel productPanel;
        switch(event){
            case STATE_TITLE:
                productPanel = new TitlePanel(controller);
            case STATE_LOBBY:
                productPanel = new LobbyPanel(controller);
            /*
            case STATE_END:
                return new EndPanel(controller);
            case STATE_1F:
                return new FloorOnePanel(controller);
            case STATE_2F:
                return new FloorTwoPanel(controller);
            case STATE_3F:
                return new FloorThreePanel(controller);
            case STATE_4F:
                return new FloorFourPanel(controller);
            case STATE_GAMEOVER:
                return new GameOverPanel(controller);
            */
            case STATE_MINIGAME:
                productPanel = new PlanarityPanel(model, controller);
            default:
                productPanel = new TitlePanel(controller);
        }
        productPanel.setBounds(0,0,600,800);
        return productPanel;
    }
}