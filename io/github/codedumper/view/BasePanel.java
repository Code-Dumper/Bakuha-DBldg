package io.github.codedumper.view;

import io.github.codedumper.controller.*;
import io.github.codedumper.model.Event;
import java.awt.event.*;
import javax.swing.*;

/**
 * それぞれのPanelに共通な基本的な機能を持った親クラスBasePanel。
 * このクラスを継承して各状態のパネルが作成されることが期待される。
 * JLayeredPaneにより重なりを実現しており、背景が最上層1層、ボタンが最下層0層に配置される。
 * 現行の仕様ではコントローラーにボタンの処理を委任する必要があるため、コンストラクタの中でコントローラを指定する必要がある。
 * BasePanelは背景のセット、buttonの作成&貼り付け、状態遷移のためのメソッドを持ち、子クラスはこの性質を持たなければならない。
 */

public class BasePanel extends JPanel implements ActionListener{
    /*コンポーネントを階層的に配置するpanel
    ボタンの上に画像を配置してボタンを見えなくする*/
    private JLayeredPane panel;
    protected GameController controller;
    public BasePanel(GameController controller) {
        this.setLayout(null); //親パネルのレイアウト無効化
        panel = new JLayeredPane();
        panel.setLayout(null);
        panel.setSize(600, 800);
        panel.setVisible(true);
        this.add(panel);
    }

    /**
     * 背景画像をセットするメソッド。
     * @param path pathにはio/github/codedumper/viewの中のファイルが指定されるはずである。
     */
    public void setBackground(String path) {
        //画像のパスを取得する処理
        ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource(path));
        JLabel background = new JLabel(imageIcon);

        background.setBounds(0, 0, 600, 800);

        /*背景をマウスイベント対象外にする処理
        この処理がない場合クリックが背景画像に対して反応してしまいボタンが動作しない*/
        background.addMouseListener(new java.awt.event.MouseAdapter() {});

        //背景をpanelの最上層に配置
        panel.add(background, Integer.valueOf(1));
    }

    
    //ボタンをButtonPropertiesにより指示された位置に配置し、クリックした際の遷移先をEventにする処理。
    public void createButton(Event event, ButtonProperties layout) {
            JButton button = new JButton();
            //ボタンの位置指定
            button.setBounds(   layout.getX(), 
                                layout.getY(), 
                                layout.getWidth(), 
                                layout.getHeight()
                            );
            //eventはEnumなのでtoStringが実装されているはず
            button.setActionCommand(event.toString());
            //ボタンをpanelの最下層に配置
            panel.add(button, Integer.valueOf(0));
            button.addActionListener(this);
    }

    //遷移先をcontrollerに渡す
    public void actionPerformed(ActionEvent e) {
        //getActionCommand()はStringを返す
        String state = e.getActionCommand();
        //StringをEventに変換
        Event destination = Event.valueOf(state);
        controller.transition(destination);
    }
}