/* 背景画像の指定、ボタンの数とラベルとサイズと位置指定*/

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
        int BUTTON_NUMBER = 4; //ボタンの個数
        String state[] = new String[BUTTON_NUMBER]; //ボタンの名前
        int locate[][] = new int[BUTTON_NUMBER][2]; //ボタンの位置
        int size[][] = new int[BUTTON_NUMBER][2]; //ボタンのサイズ
        
        //1つめのボタンの名前、位置、サイズ
        state[0] = 1F; //名前
        locate[0][0] = 0; //x座標
        locate[0][1] = 0; //y座標
        size[0][0] = 100; //width
        size[0][1] = 100; //height

        //2つめのボタンの名前、位置、サイズ
        state[1] = 2F; //名前
        locate[1][0] = 0; //x座標
        locate[1][1] = 0; //y座標
        size[1][0] = 100; //width
        size[1][1] = 100; //height

        //3つめのボタンの名前、位置、サイズ
        state[2] = 3F; //名前
        locate[2][0] = 0; //x座標
        locate[2][1] = 0; //y座標
        size[2][0] = 100; //width
        size[2][1] = 100; //height

        //4つめのボタンの名前、位置、サイズ
        state[3] = 4F; //名前
        locate[3][0] = 0; //x座標
        locate[3][1] = 0; //y座標
        size[3][0] = 100; //width
        size[3][1] = 100; //height

        for(int i = 0; i < BUTTON_NUMBER; i++) {
            basepanel.createButton(state[i], locate[i][0], locate[i][1], size[i][0], size[i][1]);
        }
    }

    @override
    public void actionPerformed(ActionEvent e) {
        BasePanel.actionPerformed(e);
    }
}