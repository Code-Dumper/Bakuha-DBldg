package io.github.codedumper.view;

import io.github.codedumper.controller.*;
import io.github.codedumper.model.Event;
import java.awt.event.*;
import io.github.codedumper.controller.GameController;

public class FloorFourPanel extends BasePanel{
    public FloorFourPanel(GameController controller){
        super(controller);
        this.setBackground("io/github/codedumper/view/Fourfloorroby.jpg");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
    }
}
