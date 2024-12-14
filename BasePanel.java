/*それぞれのPanelの基本的な機能持った親クラス
このクラスを継承してそれぞれのPanelを作成する
背景のセット、buttonの作成&貼り付け、状態遷移*/

package io.github.codedumper.view;

import io.githu.codedumper.model.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class BasePanel extends JPanel implements ActionListener{
    //コンポーネントを階層的に配置するpanel
    //ボタンの上に画像を配置してボタンを見えなくする
    private JLayeredPane panel;

    public BasePanel() {
        panel = new JLayeredPane();
        panel.setLayout(null);
        panel.setSize(600, 800);
        panel.setVisible(true);
        this.add(panel);
    }

    //背景画像のset
    public void setBackground(String path/*画像のpath*/) {
        JLabel background = new JLabel(new ImageIcon(path));
        background.setBounds(0, 0, 600, 800);
        //背景をマウスイベント対象外にする
        //これがないとクリックが背景画像に対して反応してしまいボタンが動作しない
        background.addMouseListener(new java.awt.event.MouseAdapter() {});
        //背景をpanelの最上層に配置
        panel.add(background, Integer.valueOf(1));
    }

    
    //ボタンの作成、配置、貼り付け
    public void createButton(String state, int x, int y, int width, int height) {
            JButton button = new JButton(state);
            //ボタンの位置指定
            button.setBounds(x, y, width, height);
            button.setActionCommand("state");
            //ボタンをpanelの最下層に配置
            panel.add(button, Integer.valueOf(0));
            button.addActionListener(this);
    }

    //遷移先をcontrollerに渡す
    public void actionPerformed(ActionEvent e) {
        String es = e.getActionCommand();
        switch(es) {
            case "START":
                GameController.transition(STATE_LOBBY);
            case "END":
                GameController.transition(STATE_GAMEOVER);
            case "1F":
                GameController.transition(STATE_FloorOnePanel);
            case "2F":
                GameController.transition(STATE_FloorTwoPanel);
            case "3F":
                GameController.transition(STATE_FloorThreePanel);
            case "4F":
                GameController.transition(STATE_FloorFourPanel);
            default:
                GameController.transition(STATE_TITLE);
        }
    }
}