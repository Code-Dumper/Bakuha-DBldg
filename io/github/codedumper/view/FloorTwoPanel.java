package io.github.codedumper.view;
import java.awt.event.ActionEvent;

import io.github.codedumper.controller.*;
import io.github.codedumper.model.Event;
import java.awt.event.*;
import io.github.codedumper.controller.GameController;

public class FloorTwoPanel extends BasePanel{
    public FloorTwoPanel(GameController controller){
        super(controller);
        this.setBackground("io/github/codedumper/view/TwoFloorroby.jpg",0);

        this.createButton(Event.STATE_2F_ROOM, new ButtonProperties(250, 100, 200, 600));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
    }  
    
}
