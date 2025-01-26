package io.github.codedumper.view;

import java.awt.event.*;

/* 背景画像の指定、ボタンの名前とサイズと位置指定*/

public class RoomTwoPanel extends RoomPanel {

    public RoomTwoPanel() {
        //背景画像をset
        this.setBackground("io/github/codedumper/view/Twofloor1.jpg");

        this.createItemButton("eatora", new ButtonProperties(150, 20, 100, 150));
        this.createDeleteButton();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
    }
}