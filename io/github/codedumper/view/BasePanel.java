package io.github.codedumper.view;

import io.github.codedumper.controller.*;
import io.github.codedumper.model.Event;
import java.awt.event.*;
import javax.swing.*;

/**
 * BasePanelは、各状態の画面を構成する基本的なパネルの親クラスです。
 * このクラスは、背景画像のセットアップやボタンの作成、
 * 状態遷移のための仕組みを提供します。
 * 
 * <p>特に以下の特徴があります:
 * <ul>
 *   <li>JLayeredPaneを用いた背景とボタンの重なり管理</li>
 *   <li>GameControllerとの連携を容易にするための構造</li>
 *   <li>背景画像がボタンの動作を妨げないような工夫</li>
 * </ul>
 * 
 * <p>このクラスは直接使用されることは想定されておらず、
 * サブクラス化されて特定の画面（状態）を構成するために利用されます。
 */
public class BasePanel extends JPanel implements ActionListener {

    /**
     * JLayeredPaneは、コンポーネントを階層的に管理するパネルです。
     * 背景やボタンの重なりを制御するために使用されます。
     */
    private JLayeredPane layeredPanel;

    /**
     * 状態遷移を管理するGameController。
     * BasePanelの動作は、このコントローラと連携します。
     */
    protected GameController controller;

    /**
     * コンストラクタ。
     * GameControllerを受け取り、JLayeredPaneを初期化してBasePanelに追加します。
     * 
     * @param controller ボタンの状態遷移を管理するためのGameController。
     */
    public BasePanel(GameController controller) {
        this.setLayout(null); // BasePanelのレイアウトを絶対位置指定レイアウトに
        this.setSize(600, 800); //BasePanelのサイズを横600×縦800に
        this.controller = controller;

        layeredPanel = new JLayeredPane();
        layeredPanel.setLayout(null); // LayeredPaneのレイアウトを絶対位置指定レイアウトに
        layeredPanel.setSize(600, 800); // パネルのサイズをBasePanelと同じ大きさにする
        layeredPanel.setVisible(true); //パネルを可視化する

        this.add(layeredPanel); // BasePanelにJLayeredPaneを追加
    }

    /**
     * 指定されたパスの画像を背景として設定します。
     * 背景画像がボタンの操作を妨げないように、背景をマウスイベント対象外に設定します。
     * 
     * @param path 背景画像のファイルパス。
     *             このパスは、"io/github/codedumper/view/d1.png"のようにクラスパスのルートから記載される必要があります。
     *      
     * 
     */
    public void setBackground(String path, int layerNumber) {
        try{
            // リソースから画像を取得
            ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource(path));
            JLabel background = new JLabel(imageIcon);

            // 背景画像のサイズと位置を設定
            background.setBounds(0, 0, 600, 800);

            // 背景をマウスイベント対象外に設定
            background.addMouseListener(new java.awt.event.MouseAdapter() {});

            // 背景をJLayeredPane内でボタンより下層に配置
            layeredPanel.add(background, Integer.valueOf(layerNumber));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * ボタンを指定された位置とサイズで作成し、
     * クリック時の遷移先を指定します。
     * 
     * @param event クリックされた際の状態遷移先を表すEvent。
     * @param layout ボタンの位置とサイズを指定するButtonProperties。
     */
    public void createButton(Event event, ButtonProperties layout) {
        JButton button = new JButton();

        // ボタンの位置とサイズを設定
        button.setBounds(
            layout.getX(),
            layout.getY(),
            layout.getWidth(),
            layout.getHeight()
        );

        // 状態遷移先を文字列として設定
        button.setActionCommand(event.toString());
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);


        // ボタンをJLayeredPaneの最上層に配置
        //Panelが増えてもボタンが埋もれないようにひとまず10層に配置
        layeredPanel.add(button, Integer.valueOf(10));

        // ボタンにアクションリスナーを追加
        button.addActionListener(this);
    }

    /**
     * ボタンがクリックされた際に呼び出されるメソッド。
     * アクションコマンドから状態を取得し、
     * GameControllerに遷移先を通知します。
     * 
     * @param e ボタンのアクションイベント。
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // アクションコマンド（状態名）を取得
        String state = e.getActionCommand();
        System.out.println("state");

        // アクションコマンドをEventに変換
        Event destination = Event.valueOf(state);

        // GameControllerに遷移先を通知
        controller.transition(destination);
    }
}
