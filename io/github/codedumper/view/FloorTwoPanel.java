package io.github.codedumper.view;

import io.github.codedumper.controller.GameController;
import java.awt.event.*;

public class FloorTwoPanel extends RoomPanel{
    public FloorTwoPanel(GameController controller){

        this.controller = controller;

        this.setBackground("io/github/codedumper/view/TwoFloorroby.jpg");

        this.createItemButton("2F_ROOM", new ButtonProperties(250, 100, 200, 500));
        this.createToLobbyButton();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
    }  
}
