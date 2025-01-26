package io.github.codedumper.view;

import javax.swing.*;

import io.github.codedumper.controller.GameController;

class ItemPanelFactory {
    public static JPanel createItemPanel(String item, GameController controller) {
        JPanel currentItemPanel;
        switch(item) {
            case "2F_ROOM":
                currentItemPanel = new RoomTwoPanel(controller);
                break;
            case "eatora":
                currentItemPanel = new eatoraPanel(controller);
                break;
            default:
                currentItemPanel = null;
        }
        currentItemPanel.setBounds(0,0,600,800);
        return currentItemPanel;
    }
}