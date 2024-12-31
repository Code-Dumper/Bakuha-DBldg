package io.github.codedumper.view;

import io.github.codedumper.controller.*;
import io.github.codedumper.model.Event;
import java.awt.event.*;

/* 背景画像の指定、ボタンの名前とサイズと位置指定*/

public class LobbyPanel extends BasePanel{

    public LobbyPanel(GameController controller) {
        super(controller);
        //背景画像をset
        this.setBackground("io/github/codedumper/view/LobbyPicture.png", 1);

        //ボタンをset
        //TODO この実装だと同じ位置にボタンを追加している。
        createButton(Event.STATE_1F, new ButtonProperties(0, 0, 100, 100));
        createButton(Event.STATE_2F, new ButtonProperties(0, 0, 100, 100));
        createButton(Event.STATE_3F, new ButtonProperties(0, 0, 100, 100));
        createButton(Event.STATE_4F, new ButtonProperties(0, 0, 100, 100));
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
    }
}