package io.github.codedumper.view;

import javax.swing.*;

class ItemPanelFactory {
    public static JPanel createItemPanel(String item) {
        JPanel currentItemPanel;
        switch(item) {
            case "2F_ROOM":
                currentItemPanel = new RoomTwoPanel();
                break;
            case "eatora":
                currentItemPanel = new eatoraPanel();
                break;
            default:
                currentItemPanel = null;
        }
        currentItemPanel.setBounds(0,0,600,800);
        return currentItemPanel;
    }
}