/* 背景画像の指定、ボタンの数と名前とサイズと位置指定*/

package io.github.codedumper.view;

import java.awt.*;
import javax.swing.*;

public class TitlePanel extends BasePanel implements ActionListener {
    private BasePanel basepanel;

    public TitlePanel() {
        basepanel = new BasePanel();

        //背景画像をset
        basepanel.setBackground(/*画像のpathを引数として渡す*/);

        //ボタンをset
        int BUTTON_NUMBER = 2; //ボタンの個数
        String state[] = new String[BUTTON_NUMBER]; //ボタンの名前
        int locate[][] = new int[BUTTON_NUMBER][2]; //ボタンの位置
        int size[][] = new int[BUTTON_NUMBER][2]; //ボタンのサイズ
        
        //1つめのボタンの名前、位置、サイズ
        state[0] = START; //ラベル
        locate[0][0] = 0; //x座標
        locate[0][1] = 0; //y座標
        size[0][0] = 100; //width
        size[0][1] = 100; //height

        //2つめのボタンの名前、位置、サイズ
        state[1] = END; //ラベル
        locate[1][0] = 0; //x座標
        locate[1][1] = 50; //y座標
        size[1][0] = 100; //width
        size[1][1] = 100; //height

        for(int i = 0; i < BUTTON_NUMBER; i++) {
            basepanel.createButton(state[i], locate[i][0], locate[i][1], size[i][0], size[i][1]);
        }
    }

    @override
    public void actionPerformed(ActionEvent e) {
        BasePanel.actionPerformed(e);
    }
}