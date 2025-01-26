package io.github.codedumper.view;

import io.github.codedumper.model.Event;
import java.awt.event.*;
import javax.swing.*;

/**
 * FandamentalPanelは、作成される全てのパネルが持つ性質をまとめた
 * クラスです。
 * このクラスは、背景画像のセットアップやボタンの作成の仕組みを
 * 提供します。
 * 
 * <p>特に以下の特徴があります:
 * <ul>
 *   <li>JLayeredPaneを用いた背景とボタンの重なり管理</li>
 *   <li>背景画像がボタンの動作を妨げないような工夫</li>
 * </ul>
 * 
 * <p>このクラスは直接使用されることは想定されておらず、
 * サブクラス化されて特定の画面（状態）を構成するために利用されます。
 */
public class FandamentalPanel extends JPanel implements ActionListener {

    /**
     * JLayeredPaneは、コンポーネントを階層的に管理するパネルです。
     * 背景やボタンの重なりを制御するために使用されます。
     */
    private JLayeredPane layeredPanel;

    /**
     * それぞれのパネルに配置するコンポーネントの階層を指定する。
     * 背景をsetする際にそのメソッド内でインクリメントして1階層ずつ
     * 上層に配置する。
     * 全てのボタンは同じ層に配置される。
     */
    protected int layerNumber = 0;

    /**
     * コンストラクタ。
     * JLayeredPaneを初期化してFandamentalPanelに追加します。
     * 
     * 
     */
    public FandamentalPanel() {
        this.setLayout(null); // FandamentalPanelのレイアウトを絶対位置指定レイアウトに
        this.setSize(600, 800); //FandamentalPanelのサイズを横600×縦800に

        layeredPanel = new JLayeredPane();
        layeredPanel.setLayout(null); // LayeredPaneのレイアウトを絶対位置指定レイアウトに
        layeredPanel.setSize(600, 800); // パネルのサイズをFandamentalPanelと同じ大きさにする
        layeredPanel.setVisible(true); //パネルを可視化する

        this.add(layeredPanel); // FandamentalPanelにJLayeredPaneを追加

        /**複数のパネル間でlayerNumberの値が引き継がれないようにコンストラクタ
         * を呼び出したときに初期化
         */
        layerNumber = 0 ;
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
    public void setBackground(String path) {
        try{
            // リソースから画像を取得
            ImageIcon imageIcon = new ImageIcon(this.getClass().getClassLoader().getResource(path));
            JLabel background = new JLabel(imageIcon);

            // 背景画像のサイズと位置を設定
            background.setBounds(0, 0, 600, 800);

            // 背景をマウスイベント対象外に設定
            background.addMouseListener(new java.awt.event.MouseAdapter() {});

            // 背景をJLayeredPane内でボタンより下層に配置
            layeredPanel.add(background, Integer.valueOf(layerNumber++));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * BasePanelで利用されるべきボタン作成を作成する。
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


        /** 
         * ボタンをJLayeredPaneの最上層に配置
         * createButtonメソッドを利用するTitlePanel、LobbyPanelは0層と
         * 1層に画像が貼り付けられているので最上層の2層に配置
         * 
         */

        layeredPanel.add(button, Integer.valueOf(layerNumber));

        // ボタンにアクションリスナーを追加
        button.addActionListener(this);
    }

    /**
     * RoomPanelで利用されるべきボタンを作成する。
     * ボタンを指定された位置とサイズで作成し、
     * クリック時の遷移先を指定します。
     * 
     * @param layout ボタンの位置とサイズを指定するButtonProperties。
     */
    public void createItemButton(String item, ButtonProperties layout) {
        JButton button = new JButton();

        // ボタンの位置とサイズを設定
        button.setBounds(
            layout.getX(),
            layout.getY(),
            layout.getWidth(),
            layout.getHeight()
        );

        // 状態遷移先を文字列として設定
        button.setActionCommand(item);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);

        // ボタンをJLayeredPaneの最上層に配置
        layeredPanel.add(button, Integer.valueOf(layerNumber));

        // ボタンにアクションリスナーを追加
        button.addActionListener(this);
    }

     /**
     * パネル自身を削除するためのボタンを作成する。
     */
    public void createDeleteButton() {
        JButton button = new JButton("戻る");
        // 自身を削除するためのボタンを右上に配置
        button.setBounds(0, 600, 600, 200);
        button.setActionCommand("delete");
        // ボタンをJLayeredPaneの最上層に配置
        layeredPanel.add(button, Integer.valueOf(layerNumber));
        // ボタンにアクションリスナーを追加
        button.addActionListener(this);
    }

    /**
     * FloorOne,FloorTwo,FloorThree,FloorFourPanelで使用される
     * Lobbyへ状態遷移するためのボタンを作成。
     */
    public void createToLobbyButton() {
        JButton button = new JButton("ロビーへ");
        // 自身を削除するためのボタンを右上に配置
        button.setBounds(0, 600, 600, 200);
        button.setActionCommand("lobby");
        // ボタンをJLayeredPaneの最上層に配置
        layeredPanel.add(button, Integer.valueOf(layerNumber));
        // ボタンにアクションリスナーを追加
        button.addActionListener(this);
    }

    /**
     * ボタンがクリックされた際に呼び出されるメソッド。
     * 
     * @param e ボタンのアクションイベント。
     */
    @Override
    public void actionPerformed(ActionEvent e) {};
}
