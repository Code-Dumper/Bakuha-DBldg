package io.github.codedumper.view;

import io.github.codedumper.model.*;
import java.awt.*;
import javax.swing.*;

/* 背景画像の指定、ボタンの名前とサイズと位置指定*/

public class TitlePanel extends BasePanel implements ButtonLayout, ActionListener {

    public TitlePanel() {

        //背景画像をset
        setBackground(getClass().getClassLoader().getResource("/*画像のクラスパス*/").getPath());

        //ボタンをset
        createButton(STATE_TITLE, new ButtonLayout(0, 0, 100, 100));
        createButton(STATE_END, new ButtonLayout(0, 50, 100, 100));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
    }
}