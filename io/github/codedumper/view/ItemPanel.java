package io.github.codedumper.view;

import java.awt.event.*;
import javax.swing.*;

import io.github.codedumper.view.ButtonProperties;

public class ItemPanel extends JPanel implements ActionListener {

    /**
     * JLayeredPaneは、コンポーネントを階層的に管理するパネルです。
     * 背景やボタンの重なりを制御するために使用されます。
     */
    private JLayeredPane ItemlayeredPanel;

    /**
     * コンストラクタ。
     * JLayeredPaneを初期化してItemPanelに追加します。
     * 
     */
    public ItemPanel() {
        this.setLayout(null); // ItemPanelのレイアウトを絶対位置指定レイアウトに
        this.setSize(600, 800); //ItemPanelのサイズを横600×縦800に

        ItemlayeredPanel = new JLayeredPane();
        ItemlayeredPanel.setLayout(null); // JLayeredPaneのレイアウトを絶対位置指定レイアウトに
        ItemlayeredPanel.setSize(600, 800); // パネルのサイズをItemPanelと同じ大きさにする
        ItemlayeredPanel.setVisible(true); //パネルを可視化する

        this.add(ItemlayeredPanel); // ItemPanelにJLayeredPaneを追加
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
    public void setItemBackground(String path, int layerNumber) {
        try{
            // リソースから画像を取得
            ImageIcon imageIcon = new ImageIcon(this.getClass().getClassLoader().getResource(path));
            JLabel background = new JLabel(imageIcon);

            // 背景画像のサイズと位置を設定
            background.setBounds(0, 0, 600, 800);

            // 背景をマウスイベント対象外に設定
            background.addMouseListener(new java.awt.event.MouseAdapter() {});

            // 背景をJLayeredPane内でボタンより下層に配置
            ItemlayeredPanel.add(background, Integer.valueOf(layerNumber));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
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
        //Panelが増えてもボタンが埋もれないようにひとまず10層に配置
        ItemlayeredPanel.add(button, Integer.valueOf(10));

        // ボタンにアクションリスナーを追加
        button.addActionListener(this);
    }

    /**
     * パネル自身を削除するためのボタンを作成する。
     */
    public void createDeleteButton() {
        JButton button = new JButton("戻る");
        // 自身を削除するためのボタンを右下に配置
        button.setBounds(500, 0, 100, 50);
        button.setActionCommand("delete");
        // ボタンをJLayeredPaneの最上層に配置
        ItemlayeredPanel.add(button, Integer.valueOf(10));
        // ボタンにアクションリスナーを追加
        button.addActionListener(this);
    }

    /**
     * 小道具を表示している現在のPanelを削除する。
     * または小道具にズームインするパネルを作成する。
     * 
     * @param e ボタンのアクションイベント。
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // アクションコマンド（アイテム名）を取得
        String item = e.getActionCommand();
        System.out.println(item);
        switch (item) {
            case "delete":
                JLayeredPane RemovelayeredPanel = (JLayeredPane) this.getParent();
                RemovelayeredPanel.remove(this);
                RemovelayeredPanel.revalidate();
                RemovelayeredPanel.repaint();
                break;
            default:
                JPanel currentItemPanel = ItemPanelFactory.createItemPanel(item);
                if(currentItemPanel != null) {
                    // 新しく作成したパネルをItemlayeredPanelに追加
                    ItemlayeredPanel.add(currentItemPanel, Integer.valueOf(20));
                    // 再描画
                    ItemlayeredPanel.revalidate();
                    ItemlayeredPanel.repaint();
                }
        }
    }
}
