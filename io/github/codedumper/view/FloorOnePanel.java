package io.github.codedumper.view;
import java.awt.event.ActionEvent;

import io.github.codedumper.controller.GameController;
import io.github.codedumper.model.Event;

public class FloorOnePanel extends BasePanel{
    public FloorOnePanel(GameController controller){
        super(controller);
        this.createButton(Event.STATE_1F_BOMB, new ButtonProperties(100, 100, 100, 100));
        this.createButton(Event.STATE_1F_ROOM, new ButtonProperties(200, 200, 100, 100));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
    }
}
