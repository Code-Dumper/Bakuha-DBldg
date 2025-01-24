package io.github.codedumper.view;

import java.awt.event.*;

/* 背景画像の指定、ボタンの名前とサイズと位置指定*/

public class RoomTwoPanel extends ItemPanel {

    public RoomTwoPanel() {
        //背景画像をset
        this.setItemBackground("io/github/codedumper/view/Twofloor1.jpg", 1);

        this.createItemButton("eatora", new ButtonProperties(150, 20, 100, 150));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
    }
}