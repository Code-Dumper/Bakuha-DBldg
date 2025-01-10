package io.github.codedumper.view;
import java.awt.event.ActionEvent;

import io.github.codedumper.controller.*;
import io.github.codedumper.model.Event;
import java.awt.event.*;
import io.github.codedumper.controller.GameController;


public class FloorOnePanel extends BasePanel{
    public FloorOnePanel(GameController controller){
        super(controller);
        this.setBackground("io/github/codedumper/view/Onefloorroby.jpg");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
    }
    
}
