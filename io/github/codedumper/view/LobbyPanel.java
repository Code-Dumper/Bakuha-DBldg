package io.github.codedumper.view;

import io.github.codedumper.controller.*;
import io.github.codedumper.model.Event;
import java.awt.event.*;

/* 背景画像の指定、ボタンの名前とサイズと位置指定*/

public class LobbyPanel extends BasePanel{

    public LobbyPanel(GameController controller) {
        super(controller);
        //背景画像をset
        this.setBackground("io/github/codedumper/view/Onefloorroby.jpg");
        this.setBackground("io/github/codedumper/view/Information.jpg");

        //ボタンをset
        createButton(Event.STATE_4F, new ButtonProperties(200, 360, 200, 40));
        createButton(Event.STATE_3F, new ButtonProperties(200, 420, 200, 40));
        createButton(Event.STATE_2F, new ButtonProperties(200, 480, 200, 40));
        createButton(Event.STATE_1F, new ButtonProperties(200, 540, 200, 40));
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
    }
}