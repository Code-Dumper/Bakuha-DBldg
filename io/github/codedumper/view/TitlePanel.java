package io.github.codedumper.view;

import io.github.codedumper.controller.*;
import io.github.codedumper.model.Event;
import java.awt.event.*;

/* 背景画像の指定、ボタンの名前とサイズと位置指定*/

public class TitlePanel extends BasePanel{

    public TitlePanel(GameController controller) {
        super(controller);
        //背景画像をset
        this.setBackground("io/github/codedumper/view/d.jpg", 0);
        this.setBackground("io/github/codedumper/view/title.jpg", 1);
        //ボタンをset
        this.createButton(Event.STATE_LOBBY, new ButtonProperties(200, 300, 200, 100));
        this.createButton(Event.STATE_END, new ButtonProperties(200, 500, 200, 100));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
    }
}