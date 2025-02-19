package io.github.codedumper.view;

import io.github.codedumper.controller.*;
import io.github.codedumper.model.State;

import java.awt.Color;
import java.awt.event.*;

public class GameClearPanel extends BasePanel{

    public GameClearPanel(GameController controller) {
        super(controller);
        //ボタンをset
        this.setBackground("io/github/codedumper/view/image-GameClear-Image.jpg", 0);
        this.createButton(State.STATE_TITLE, new ButtonProperties(230, 540, 140, 30));
        this.addLabel("D棟を爆破しようとする陰謀は未然に防がれた。", 150, 350, 600, 50, Color.WHITE);
        this.addLabel("だが、D棟爆破を企む電通大生の正体は、未だ明らかになっていない。", 150, 370, 600, 50, Color.WHITE);
        System.out.println("GameClearPanel successfully created");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
    }
}