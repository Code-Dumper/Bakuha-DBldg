package io.github.codedumper.view;

import io.github.codedumper.controller.*;
import io.github.codedumper.model.Event;
import java.awt.event.*;

/* 背景画像の指定、ボタンの名前とサイズと位置指定*/

public class FloorFourPanel extends BasePanel{

    public FloorFourPanel(GameController controller) {
        super(controller);
        //背景画像をset
        this.setBackground("io/github/codedumper/view/floorFourPicture.png", 1);

        //ボタンをset
        createButton(Event.STATE_LOBBY, new ButtonProperties(300, 100, 300, 600));
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
    }
}