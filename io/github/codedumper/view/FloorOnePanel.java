package io.github.codedumper.view;

import io.github.codedumper.controller.GameController;
import java.awt.event.*;

public class FloorOnePanel extends RoomPanel{
    public FloorOnePanel(GameController controller){
        super(controller);

        this.setBackground("io/github/codedumper/view/Onefloorroby.jpg");

        //this.createItemButton("1F_ROOM", new ButtonProperties(250, 100, 200, 600));
        this.createToLobbyButton();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
    }
    
}
