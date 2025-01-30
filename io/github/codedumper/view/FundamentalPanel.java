package io.github.codedumper.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import io.github.codedumper.controller.GameController;
import io.github.codedumper.model.State;

//FundamentalPanelは1JLayeredPaneをComponentとして持つJPanelである。
//1から5層は画像用のレイヤ、6層から10層はボタン用のレイヤである。
//このクラスはサブクラス化されて用いられるべきクラスである。
//機能として次の4つを最低限持つ。
//1 画像のないButtonの生成
//2 画像付きボタンの生成
//3 背景つきJLabelの生成
//4 JLayeredPane上の要素の全削除とsubStateに対応したパネルへの切り替え
//swing自体がスレッドセーフでないためこのクラスはスレッドセーフではない。
public abstract class FundamentalPanel extends JPanel implements ActionListener{
    //追加するレイヤー。
    protected final int LAYER_FIGURE_FIRST = 1;
    protected final int LAYER_FIGURE_SECOND = 2;
    protected final int LAYER_FIGURE_THIRD = 3;
    protected final int LAYER_FIGURE_FOURTH = 4;
    protected final int LAYER_FIGURE_FIFTH = 5;
    protected final int LAYER_UTIL_FIRST = 6;
    protected final int LAYER_UTIL_SECOND = 7;
    protected final int LAYER_UTIL_THIRD = 8;
    protected final int LAYER_UTIL_FOURTH = 9;
    protected final int LAYER_UTIL_FIFTH = 10;

    //パネルの中で管理する状態。
    protected final int SUBSTATE_INITIAL = 1;
    
    //矢印ボタンの位置座標。x,y,width,heightの順。
    protected final Integer[] INFO_LEFT_BUTTON = {400, 600, 100, 100};
    protected final Integer[] INFO_RIGHT_BUTTON = {600, 600, 100, 100};
    protected final Integer[] INFO_FRONT_BUTTON = {500, 500, 100, 100};
    protected final Integer[] INFO_REAR_BUTTON = {500, 700, 100, 100};

    //画像ファイルの位置するディレクトリ。
    protected final String CLASSPATH = "io/github/codedumper/view/";

    protected JLayeredPane layeredPane;
    protected GameController controller;
    protected int currentSubState;
    protected JButton buttonToFront;
    protected JButton buttonToRight;
    protected JButton buttonToLeft;
    protected JButton buttonToRear;

    //コンストラクタ
    public FundamentalPanel(GameController controller){
        this.setLayout(null);
        this.setSize(600, 800);
        layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        layeredPane.setVisible(true);
        this.add(layeredPane);
        this.controller = controller;
        this.currentSubState = SUBSTATE_INITIAL;
        changeSubState(currentSubState);
    }

    protected void changeSubState(int subState){
        //layeredPane上のコンポーネントを全て削除する
        layeredPane.removeAll();
        this.revalidate();
        this.repaint();

        //subStateに対応したボタンと画像を読み込む
        switch(subState){
            default:
                buttonToFront = createDirectionalButton(SUBSTATE_INITIAL,"FRONT");
                buttonToLeft = createDirectionalButton(SUBSTATE_INITIAL,"LEFT");
                buttonToRight = createDirectionalButton(SUBSTATE_INITIAL,"RIGHT");
                buttonToRear = createDirectionalButton(SUBSTATE_INITIAL,"REAR");
                layeredPane.add(createJLabelWithImage("title.jpg",0,0,600,800), LAYER_FIGURE_FIRST);
                layeredPane.add(buttonToFront, LAYER_UTIL_FIRST);
                layeredPane.add(buttonToLeft, LAYER_UTIL_FIRST);
                layeredPane.add(buttonToRight, LAYER_UTIL_FIRST);
                layeredPane.add(buttonToRear, LAYER_UTIL_FIRST);
            break;
        }

    }

    //矢印ボタンを作成する

    protected JButton createDirectionalButton(Integer subState, String direction){
        JButton directionalButton;
        switch(direction){
            case "FRONT":
            directionalButton = createButtonWithoutImage(subState, INFO_FRONT_BUTTON[0], INFO_FRONT_BUTTON[1], INFO_FRONT_BUTTON[2], INFO_FRONT_BUTTON[3]);
            break;
            case "LEFT":
            directionalButton = createButtonWithoutImage(subState, INFO_LEFT_BUTTON[0], INFO_LEFT_BUTTON[1], INFO_LEFT_BUTTON[2], INFO_LEFT_BUTTON[3]);
            break;
            case "RIGHT":
            directionalButton = createButtonWithoutImage(subState, INFO_RIGHT_BUTTON[0], INFO_RIGHT_BUTTON[1], INFO_RIGHT_BUTTON[2], INFO_RIGHT_BUTTON[3]);
            break;
            case "REAR":
            directionalButton = createButtonWithoutImage(subState, INFO_REAR_BUTTON[0], INFO_REAR_BUTTON[1], INFO_REAR_BUTTON[2], INFO_REAR_BUTTON[3]);
            break;
            default:
            throw new IllegalArgumentException();
        }
        return directionalButton;
    }
    //状態を変える矢印ボタンを作成する
    protected JButton createStateChangeDirectionalButton(State state, String direction){
        JButton directionalButton;
        switch(direction){
            case "FRONT":
            directionalButton = createButtonWithoutImage(state, INFO_FRONT_BUTTON[0], INFO_FRONT_BUTTON[1], INFO_FRONT_BUTTON[2], INFO_FRONT_BUTTON[3]);
            break;
            case "LEFT":
            directionalButton = createButtonWithoutImage(state, INFO_LEFT_BUTTON[0], INFO_LEFT_BUTTON[1], INFO_LEFT_BUTTON[2], INFO_LEFT_BUTTON[3]);
            break;
            case "RIGHT":
            directionalButton = createButtonWithoutImage(state, INFO_RIGHT_BUTTON[0], INFO_RIGHT_BUTTON[1], INFO_RIGHT_BUTTON[2], INFO_RIGHT_BUTTON[3]);
            break;
            case "REAR":
            directionalButton = createButtonWithoutImage(state, INFO_REAR_BUTTON[0], INFO_REAR_BUTTON[1], INFO_REAR_BUTTON[2], INFO_REAR_BUTTON[3]);
            break;
            default:
            throw new IllegalArgumentException();
        }
        return directionalButton;
    }

    //位置x,y大きさwidth,height、subStateを内包したJButtonを作成する。
    protected JButton createButtonWithoutImage(Integer subState, int x, int y, int width, int height){
        JButton buttonWithoutImage = new JButton();
        buttonWithoutImage.setBounds(x, y, width, height);
        buttonWithoutImage.setActionCommand(Integer.toString(subState));
        return buttonWithoutImage;

    }

    protected JButton createButtonWithoutImage(State state, int x, int y, int width, int height){
        JButton buttonWithoutImage = new JButton();
        buttonWithoutImage.setBounds(x, y, width, height);
        buttonWithoutImage.setActionCommand(state.toString());
        return buttonWithoutImage;

    }

    //位置x,y大きさwidth,height、subStateを内包したJButtonを作成する。
    //もしイメージが読み込めなかった場合、イメージなしのJButtonを作成する。
    //imagePathは同ディレクトリ(view内)の画像ファイルを指定する。
    protected JButton createButtonWithImage(Integer subState, String imagePath, int x, int y, int width, int height){
        JButton buttonWithImage = new JButton();
        buttonWithImage.setBounds(x,y,width,height);
        buttonWithImage.setActionCommand(Integer.toString(subState));
        try{
            ImageIcon imageIcon = new ImageIcon(this.getClass().getClassLoader().getResource(CLASSPATH+imagePath));
            buttonWithImage.setIcon(imageIcon);
            return buttonWithImage;
        }catch(Exception e){
            e.printStackTrace();
            return new JButton("Failed to load image");
        }

    }
    
    protected JLabel createJLabelWithImage(String imagePath, int x, int y, int width, int height){
        JLabel jLabelWithImage = new JLabel();
        try{
            // リソースから画像を取得
            ImageIcon imageIcon = new ImageIcon(this.getClass().getClassLoader().getResource(imagePath));
            jLabelWithImage.setIcon(imageIcon);
            jLabelWithImage.setBounds(x,y,width,height);
            return jLabelWithImage;
        }catch(Exception e){
            e.printStackTrace();
            return jLabelWithImage;
        }

    }

    protected void addLabel(JLabel labelToAdd, int layer){
        layeredPane.add(labelToAdd, layer);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch(actionCommand){
            case "SUBSTATE_INITIAL":
                changeSubState(SUBSTATE_INITIAL);
            break;
            case "STATE_LOBBY":
                controller.transition(State.valueOf(actionCommand));
            break;
        }
    }
}   
