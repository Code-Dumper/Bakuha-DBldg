package io.github.codedumper.view;

import io.github.codedumper.controller.*;
import io.github.codedumper.model.State;
import java.awt.event.*;
import io.github.codedumper.controller.GameController;

public class EndPanel extends BasePanel{
    public EndPanel(GameController controller){
        super(controller);
        this.setBackground("io/github/codedumper/view/EndPicture.jpg",0);
        createButton(State.STATE_TITLE,new ButtonProperties(200,600, 100, 100));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
    }
}
