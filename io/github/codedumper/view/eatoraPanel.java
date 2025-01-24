package io.github.codedumper.view;

import java.awt.event.*;

/* 背景画像の指定、ボタンの名前とサイズと位置指定*/

public class eatoraPanel extends ItemPanel {

    public eatoraPanel() {
        //背景画像をset
        this.setItemBackground("io/github/codedumper/view/Twoeatora.jpg", 1);
        //削除ボタンをset
        this.createDeleteButton();

        //エアトラに関する小道具にzoominするためのボタン
        //this.createItemButton(Zoom_eatora, new ButtonProperties(500, 0, 100, 100));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
    }
}