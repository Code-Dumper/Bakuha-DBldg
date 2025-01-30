package io.github.codedumper.view;

import io.github.codedumper.controller.*;
import io.github.codedumper.model.State;
import java.awt.event.*;

public class GameOverPanel extends BasePanel{

    public GameOverPanel(GameController controller) {
        super(controller);
        //ボタンをset
        this.createButton(State.STATE_END, new ButtonProperties(200, 600, 200, 100));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
    }
}