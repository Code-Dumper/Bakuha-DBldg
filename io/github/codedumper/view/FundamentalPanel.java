package io.github.codedumper.view;

import io.github.codedumper.controller.GameController;
import io.github.codedumper.model.Event;

import java.awt.Component;
import java.awt.event.*;
import javax.swing.*;

/**
 * FundamentalPanelは、作成される全てのパネルが持つ性質をまとめたJPanelを継承する
 * クラスであり、RoomPanelおよびBasePanelの親クラスです。
 * このクラスは、背景画像のセットアップやボタンの作成の仕組みを
 * 提供します。このパネル上にはJLayeredPaneのコンポーネントが追加されており、この「上」に各要素が表示されます。
 * このクラスはサブクラス化されて特定の画面（状態）を構成するために利用されます。
 */
public class FundamentalPanel extends JPanel implements ActionListener {

    //パネルの重ね合わせを表現するための変数
    protected JLayeredPane layeredPanel;

    /**
     * それぞれのパネルに配置するコンポーネントの階層を指定するための変数。0以上の値をとる
     * 背景をsetする際にそのメソッド内でインクリメントして1階層ずつ
     * 上層に配置する。
     * 全てのボタンは同じ層に配置される。
     */
    protected int layerNumber;
    protected GameController controller;


    public FundamentalPanel(GameController controller) {
        
        //このPanelを絶対位置指定レイアウトとし、サイズをw600h800にする
        this.setLayout(null);
        this.setSize(600, 800);
        layeredPanel = new JLayeredPane();
        layeredPanel.setLayout(null); // LayeredPaneのレイアウトを絶対位置指定レイアウトに
        layeredPanel.setSize(600, 800); // パネルのサイズをFandamentalPanelと同じ大きさにする
        layeredPanel.setVisible(true); //パネルを可視化する

        this.add(layeredPanel); // FandamentalPanelにJLayeredPaneを追加
        this.addMouseListener(new MouseAdapter(){});
        this.addMouseMotionListener(new MouseMotionAdapter() {});
        this.controller = controller;
        layerNumber = 1;
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

            // 背景をJLayeredPane内でボタンより下層に配置
            System.out.println("glayer;"+layerNumber);
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
        System.out.println("blayer:"+layerNumber);
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
        System.out.println("blayer;"+layerNumber);
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
        System.out.println("blayer:"+layerNumber);
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
    //引数に指定したpanelToStackをLayeredPaneの最上層に追加する関数
    public void stackPanel(JPanel panelToStack){
        System.out.println("Hash:"+panelToStack.hashCode());
        layeredPanel.add(panelToStack, Integer.valueOf(++layerNumber));
        layeredPanel.revalidate();
        layeredPanel.repaint();
    }
    //最上層に存在するパネルを削除する関数
    public void deleteTopPanelOf(JPanel jpanel) {
        if (layerNumber > 0) {
            jpanel.getParent().remove(this);

            }
            layerNumber--; // レイヤー番号を更新
            layeredPanel.revalidate();
            layeredPanel.repaint();
    }
    
    /**
     * ボタンがクリックされた際に呼び出されるメソッド。
     * 
     * @param e ボタンのアクションイベント。
     */
    @Override
    public void actionPerformed(ActionEvent e){};
}
