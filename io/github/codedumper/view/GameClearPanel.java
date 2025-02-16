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
        this.addLabel("全ての階の爆弾を解除した！", 150, 350, 600, 50, Color.WHITE);
        System.out.println("GameClearPanel successfully created");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
    }
}