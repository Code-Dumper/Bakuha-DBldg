package io.github.codedumper.view;

import io.github.codedumper.controller.GameController;
import java.awt.event.*;


public class FloorThreePanel extends RoomPanel{
    public FloorThreePanel(GameController controller){
        super(controller);

        this.setBackground("io/github/codedumper/view/Threefloorroby.jpg");

        //this.createItemButton("3F_ROOM", new ButtonProperties(250, 100, 200, 600));
        this.createToLobbyButton();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
    }   
}
