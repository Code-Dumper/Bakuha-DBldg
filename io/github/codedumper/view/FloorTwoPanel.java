package io.github.codedumper.view;
import java.awt.event.ActionEvent;

import io.github.codedumper.controller.GameController;
import io.github.codedumper.model.Event;

public class FloorTwoPanel extends BasePanel{
    public FloorTwoPanel(GameController controller){
        super(controller);
        this.createButton(Event.STATE_2F_BOMB, new ButtonProperties(100, 100, 100, 100));
        this.createButton(Event.STATE_2F_ROOM, new ButtonProperties(200, 200, 100, 100));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
    }
}
