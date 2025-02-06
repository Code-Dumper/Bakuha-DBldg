package io.github.codedumper.view;

import io.github.codedumper.controller.*;
import io.github.codedumper.model.State;

import java.awt.Color;
import java.awt.event.*;

public class GameOverPanel extends BasePanel{

    public GameOverPanel(GameController controller) {
        super(controller);
        //ボタンをset
        this.setBackground("io/github/codedumper/view/image-GameOver-Image.jpg", 0);
        this.createButton(State.STATE_TITLE, new ButtonProperties(230, 540, 140, 30));
        this.addLabel("D棟は爆破され、あなたは重症の怪我を負った。", 150, 350, 600, 50, Color.WHITE);
        this.addLabel("事件がなぜ起こったのか、それは誰も知らない。", 150, 370, 600, 50, Color.WHITE);
        System.out.println("GameOverPanel successfully created");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
    }
}