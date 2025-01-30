package io.github.codedumper.view;

import io.github.codedumper.controller.*;
import io.github.codedumper.model.State;
import java.awt.event.*;

/* 背景画像の指定、ボタンの名前とサイズと位置指定*/

public class TitlePanel extends BasePanel{

    public TitlePanel(GameController controller) {
        super(controller);
        //背景画像をset
        this.setBackground("io/github/codedumper/view/image-Title-Dbldg.jpg", 0);
        this.setBackground("io/github/codedumper/view/image-Title-Image.jpg", 1);
        //ボタンをset
        this.createButton(State.STATE_LOBBY, new ButtonProperties(250, 360, 100, 50));
        this.createButton(State.STATE_END, new ButtonProperties(250, 500, 100, 50));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
    }
}