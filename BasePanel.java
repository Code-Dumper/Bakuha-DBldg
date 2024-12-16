package io.github.codedumper.view;

import io.github.codedumper.model.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

/*それぞれのPanelの基本的な機能持った親クラス
このクラスを継承してそれぞれのPanelを作成する
背景のセット、buttonの作成&貼り付け、状態遷移*/

public class BasePanel extends JPanel implements ActionListener{
    /*コンポーネントを階層的に配置するpanel
    ボタンの上に画像を配置してボタンを見えなくする*/
    private JLayeredPane panel;

    public BasePanel() {
        setLayout(null); //親パネルのレイアウト無効化
        panel = new JLayeredPane();
        panel.setLayout(null);
        panel.setSize(600, 800);
        panel.setVisible(true);
        this.add(panel);
    }

    //背景画像のset
    public void setBackground(String path/*画像のclasspath*/) {
        //画像のクラスパスを取得
        ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource(path));
        JLabel background = new JLabel(imageIcon);

        background.setBounds(0, 0, 600, 800);

        /*背景をマウスイベント対象外にする
        これがないとクリックが背景画像に対して反応してしまいボタンが動作しない*/
        background.addMouseListener(new java.awt.event.MouseAdapter() {});

        //背景をpanelの最上層に配置
        panel.add(background, Integer.valueOf(1));
    }

    
    //ボタンの作成、配置、貼り付け
    public void createButton(Event event, ButtonLayout layout) {
            JButton button = new JButton();
            //ボタンの位置指定
            button.setBounds(layout.getX(), layout.getY(), layout.getWidth(), layout.getHeight());
            button.setActionCommand(event);
            //ボタンをpanelの最下層に配置
            panel.add(button, Integer.valueOf(0));
            button.addActionListener(this);
    }

    //遷移先をcontrollerに渡す
    public void actionPerformed(ActionEvent e) {
        //getActionCommand()はStringを返す
        String state = e.getActionCommand();
        //StringをEventに変換
        Event destination = Event.valueOf(command);
        GameController.transition(destination);
    }
}