package io.github.codedumper.view;

import io.github.codedumper.controller.*;
import io.github.codedumper.model.Event;
import java.awt.event.*;

/* 背景画像の指定、ボタンの名前とサイズと位置指定*/

public class TitlePanel extends BasePanel{

    public TitlePanel(GameController controller) {
        super(controller);
        //背景画像をset
        this.setBackground("io/github/codedumper/view/TitlePicture.png");
        //ボタンをset
        createButton(Event.STATE_TITLE, new ButtonProperties(0, 0, 100, 100));
        createButton(Event.STATE_END, new ButtonProperties(0, 50, 100, 100));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
    }
}