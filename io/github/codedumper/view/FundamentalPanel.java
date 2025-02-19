package io.github.codedumper.view;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.Image;
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
    protected final Integer LAYER_FIGURE_FIRST = 1;
    protected final Integer LAYER_FIGURE_SECOND = 2;
    protected final Integer LAYER_FIGURE_THIRD = 3;
    protected final Integer LAYER_FIGURE_FOURTH = 4;
    protected final Integer LAYER_FIGURE_FIFTH = 5;
    protected final Integer LAYER_UTIL_FIRST = 6;
    protected final Integer LAYER_UTIL_SECOND = 7;
    protected final Integer LAYER_UTIL_THIRD = 8;
    protected final Integer LAYER_UTIL_FOURTH = 9;
    protected final Integer LAYER_UTIL_FIFTH = 10;

    //パネルの中で管理する状態。
    protected final int SUBSTATE_INITIAL = 1;
    
    //矢印ボタンの位置座標。x,y,width,heightの順。
    protected final Integer[] INFO_LEFT_BUTTON = {0, 375, 50, 50};
    protected final Integer[] INFO_RIGHT_BUTTON = {100, 375, 50, 50};
    protected final Integer[] INFO_FRONT_BUTTON = {50, 325, 50, 50};
    protected final Integer[] INFO_REAR_BUTTON = {50, 425, 50, 50};

    protected final Integer[] BOMB_REAR_BUTTON = {550,0,50,50};
    protected final Integer[] LOBBY_REAR_BUTTON = {325,650,50,50};
    protected final Integer[] LOBBY_FRONT_BUTTON = {325,550,50,50};
    protected final Integer[] LOBBY_LEFT_BUTTON = {275, 600, 50, 50};
    protected final Integer[] LOBBY_RIGHT_BUTTON = {375, 600, 50, 50};
    protected final Integer[] ITEM_REAR_BUTTON = {275, 500, 50, 50};
    protected final Integer[] BOMB_HINT_BUTTON = {30,250,180,240};

    //画像ファイルの位置するディレクトリ。
    protected final String CLASSPATH = "io/github/codedumper/view/";

    protected JLayeredPane layeredPane;
    protected GameController controller;
    protected int currentSubState;
    protected JButton buttonToFront;
    protected JButton buttonToRight;
    protected JButton buttonToLeft;
    protected JButton buttonToRear;
    protected JButton bombHintButton;


    //入力した文字を4つまで表示する
    protected JLabel displayLabel;
    //ボタンを12個配置するパネル
    protected JPanel NumberButtonPanel;


    //コンストラクタ
    public FundamentalPanel(GameController controller){
        this.setLayout(null);
        this.setSize(600, 800);
        //layeredPaneの設定
        layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        layeredPane.setBounds(0,0,600,800);
        layeredPane.setVisible(true);
        //FundamentalClassの変数の設定
        this.controller = controller;
        this.currentSubState = SUBSTATE_INITIAL;
        //画面描画
        changeSubState(currentSubState);
        System.out.println("Successfully initializing FundamentalPanel.");
        this.add(layeredPane);
        //ボタンの反映
        updateButton();
    }
    //このクラスはパネルのサブ状態を反映させるように実装する
    abstract protected void changeSubState(int subState);

    //ボタンを更新する
    protected void updateButton(){
        if(buttonToFront != null){
            layeredPane.add(buttonToFront, LAYER_UTIL_SECOND);}
        if(buttonToLeft != null){
            layeredPane.add(buttonToLeft, LAYER_UTIL_SECOND);}
        if(buttonToRear != null){
            layeredPane.add(buttonToRear, LAYER_UTIL_SECOND);}
        if(buttonToRight != null){
            layeredPane.add(buttonToRight, LAYER_UTIL_SECOND);}
            if(bombHintButton != null){
                layeredPane.add(bombHintButton,LAYER_UTIL_SECOND);
            }
        layeredPane.revalidate();
        layeredPane.repaint();
    }

    //矢印ボタンを作成する
    protected JButton createDirectionalButton(Integer subState, String direction){
        JButton directionalButton;
        String imagePath;
        switch(direction){
            case "FRONT":
            imagePath = "image-Direction-Front.jpg";
            directionalButton = createButtonWithImage(subState, imagePath, INFO_FRONT_BUTTON[0], INFO_FRONT_BUTTON[1], INFO_FRONT_BUTTON[2], INFO_FRONT_BUTTON[3]);
            break;
            case "LEFT":
            imagePath = "image-Direction-Left.jpg";
            directionalButton = createButtonWithImage(subState, imagePath,INFO_LEFT_BUTTON[0], INFO_LEFT_BUTTON[1], INFO_LEFT_BUTTON[2], INFO_LEFT_BUTTON[3]);
            break;
            case "RIGHT":
            imagePath = "image-Direction-Right.jpg";
            directionalButton = createButtonWithImage(subState, imagePath, INFO_RIGHT_BUTTON[0], INFO_RIGHT_BUTTON[1], INFO_RIGHT_BUTTON[2], INFO_RIGHT_BUTTON[3]);
            break;
            case "REAR":
            imagePath = "image-Direction-Rear.jpg";
            directionalButton = createButtonWithImage(subState, imagePath, INFO_REAR_BUTTON[0], INFO_REAR_BUTTON[1], INFO_REAR_BUTTON[2], INFO_REAR_BUTTON[3]);
            break;
            case "BOMBREAR":
            imagePath = "image-Direction-Rear.jpg";
            directionalButton = createButtonWithImage(subState, imagePath, BOMB_REAR_BUTTON[0], BOMB_REAR_BUTTON[1], BOMB_REAR_BUTTON[2], BOMB_REAR_BUTTON[3]);
            break;
            case"LOBBYFRONT":
            imagePath = "image-Direction-Front.jpg";
            directionalButton = createButtonWithImage(subState, imagePath, LOBBY_FRONT_BUTTON[0], LOBBY_FRONT_BUTTON[1], LOBBY_FRONT_BUTTON[2], LOBBY_FRONT_BUTTON[3]);
            break;
            case"LOBBYREAR":
            imagePath = "image-Direction-Rear.jpg";
            directionalButton = createButtonWithImage(subState, imagePath, LOBBY_REAR_BUTTON[0], LOBBY_REAR_BUTTON[1], LOBBY_REAR_BUTTON[2], LOBBY_REAR_BUTTON[3]);
            break;
            case "LOBBYLEFT":
            imagePath = "image-Direction-Left.jpg";
            directionalButton = createButtonWithImage(subState, imagePath,LOBBY_LEFT_BUTTON[0], LOBBY_LEFT_BUTTON[1], LOBBY_LEFT_BUTTON[2], LOBBY_LEFT_BUTTON[3]);
            break;
            case "LOBBYRIGHT":
            imagePath = "image-Direction-Right.jpg";
            directionalButton = createButtonWithImage(subState, imagePath,LOBBY_RIGHT_BUTTON[0], LOBBY_RIGHT_BUTTON[1], LOBBY_RIGHT_BUTTON[2], LOBBY_RIGHT_BUTTON[3]);
            break;
            case "ITEMREAR":
            imagePath = "image-Direction-Rear.jpg";
            directionalButton = createButtonWithImage(subState, imagePath,ITEM_REAR_BUTTON[0], ITEM_REAR_BUTTON[1], ITEM_REAR_BUTTON[2], ITEM_REAR_BUTTON[3]);
            break;
            case "BOMBHINT":
            imagePath = "image-TwoFloor-BombHint.jpg";
            directionalButton = BombHintcreateButtonWithImage(subState, imagePath, BOMB_HINT_BUTTON[0], BOMB_HINT_BUTTON[1], BOMB_HINT_BUTTON[2], BOMB_HINT_BUTTON[3]);
            break;
            default:

            throw new IllegalArgumentException();
        }
        directionalButton.setOpaque(false);
        directionalButton.setContentAreaFilled(false);
        directionalButton.setBorderPainted(false);
        return directionalButton;
    }
    //状態を変える矢印ボタンを作成する
    protected JButton createStateChangeDirectionalButton(State state, String direction){
        JButton directionalButton;
        String imagePath;
        switch(direction){
            case "FRONT":
            imagePath = "image-Direction-Front.jpg";
            directionalButton = createButtonWithImage(state.ordinal(), imagePath, INFO_FRONT_BUTTON[0], INFO_FRONT_BUTTON[1], INFO_FRONT_BUTTON[2], INFO_FRONT_BUTTON[3]);
            break;
            case "LEFT":
            imagePath = "image-Direction-Left.jpg";
            directionalButton = createButtonWithImage(state.ordinal(),imagePath, INFO_LEFT_BUTTON[0], INFO_LEFT_BUTTON[1], INFO_LEFT_BUTTON[2], INFO_LEFT_BUTTON[3]);
            break;
            case "RIGHT":
            imagePath = "image-Direction-Right.jpg";
            directionalButton = createButtonWithImage(state.ordinal(),imagePath, INFO_RIGHT_BUTTON[0], INFO_RIGHT_BUTTON[1], INFO_RIGHT_BUTTON[2], INFO_RIGHT_BUTTON[3]);
            break;
            case "REAR":
            imagePath = "image-Direction-Rear.jpg";
            directionalButton = createButtonWithImage(state.ordinal(),imagePath, INFO_REAR_BUTTON[0], INFO_REAR_BUTTON[1], INFO_REAR_BUTTON[2], INFO_REAR_BUTTON[3]);
            break;
            case "BOMBREAR":
            imagePath = "image-Direction-Rear.jpg";
            directionalButton = createButtonWithImage(state.ordinal(), imagePath, BOMB_REAR_BUTTON[0], BOMB_REAR_BUTTON[1], BOMB_REAR_BUTTON[2], BOMB_REAR_BUTTON[3]);
            break;
            case"LOBBYFRONT":
            imagePath = "image-Direction-Front.jpg";
            directionalButton = createButtonWithImage(state.ordinal(), imagePath, LOBBY_FRONT_BUTTON[0], LOBBY_FRONT_BUTTON[1], LOBBY_FRONT_BUTTON[2], LOBBY_FRONT_BUTTON[3]);
            break;
            case"LOBBYREAR":
            imagePath = "image-Direction-Rear.jpg";
            directionalButton = createButtonWithImage(state, imagePath, LOBBY_REAR_BUTTON[0], LOBBY_REAR_BUTTON[1], LOBBY_REAR_BUTTON[2], LOBBY_REAR_BUTTON[3]);
            break;
            case "ROBBYLEFT":
            imagePath = "image-Direction-Left.jpg";
            directionalButton = createButtonWithImage(state.ordinal(), imagePath,LOBBY_LEFT_BUTTON[0], LOBBY_LEFT_BUTTON[1], LOBBY_LEFT_BUTTON[2], LOBBY_LEFT_BUTTON[3]);
            break;
            case "ROBBYRIGHT":
            imagePath = "image-Direction-Right.jpg";
            directionalButton = createButtonWithImage(state.ordinal(), imagePath,LOBBY_RIGHT_BUTTON[0], LOBBY_RIGHT_BUTTON[1], LOBBY_RIGHT_BUTTON[2], LOBBY_RIGHT_BUTTON[3]);
            break;
            default:
            throw new IllegalArgumentException();
        }
        System.out.println("button");
        directionalButton.setOpaque(false);
        directionalButton.setContentAreaFilled(false);
        directionalButton.setBorderPainted(false);

        return directionalButton;
    }


    //位置x,y大きさwidth,height、subStateを内包したJButtonを作成する。
    protected JButton createButtonWithoutImage(Integer subState, int x, int y, int width, int height){
        JButton buttonWithoutImage = new JButton();
        buttonWithoutImage.setBounds(x, y, width, height);
        buttonWithoutImage.setActionCommand(Integer.toString(subState));
        buttonWithoutImage.addActionListener(this);
        buttonWithoutImage.setOpaque(false);
        buttonWithoutImage.setContentAreaFilled(false);
        buttonWithoutImage.setBorderPainted(false);
        return buttonWithoutImage;

    }

    protected JButton createButtonWithoutImage(State state, int x, int y, int width, int height){
        JButton buttonWithoutImage = new JButton();
        buttonWithoutImage.setBounds(x, y, width, height);
        buttonWithoutImage.setActionCommand(state.toString());
        buttonWithoutImage.addActionListener(this);
        return buttonWithoutImage;

    }

    //位置x,y大きさwidth,height、subStateを内包したJButtonを作成する。
    //もしイメージが読み込めなかった場合、イメージなしのJButtonを作成する。
    //imagePathは同ディレクトリ(view内)の画像ファイルを指定する。
    protected JButton createButtonWithImage(Integer subState, String imagePath, int x, int y, int width, int height){
        JButton buttonWithImage = new JButton();
        buttonWithImage.setBounds(x,y,width,height);
        buttonWithImage.setActionCommand(Integer.toString(subState));
        buttonWithImage.addActionListener(this);
        try{
            ImageIcon imageIcon = new ImageIcon(this.getClass().getClassLoader().getResource(CLASSPATH+imagePath));
            Image scaledIcon = imageIcon.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
            imageIcon = new ImageIcon(scaledIcon); 
            buttonWithImage.setIcon(imageIcon);
            return buttonWithImage;
        }catch(Exception e){
            e.printStackTrace();
            return new JButton("Failed to load image");
        }

    }

    protected JButton createButtonWithImage(State state, String imagePath, int x, int y, int width, int height){
        JButton buttonWithImage = new JButton();
        buttonWithImage.setBounds(x,y,width,height);
        buttonWithImage.setActionCommand(state.toString());
        buttonWithImage.addActionListener(this);
        try{
            ImageIcon imageIcon = new ImageIcon(this.getClass().getClassLoader().getResource(CLASSPATH+imagePath));
            Image scaledIcon = imageIcon.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
            imageIcon = new ImageIcon(scaledIcon); 
            buttonWithImage.setIcon(imageIcon);
            return buttonWithImage;
        }catch(Exception e){
            e.printStackTrace();
            return new JButton("Failed to load image");
        }

    }

    protected JButton BumbcreateButtonWithImage(Integer subState, String imagePath, int x, int y, int width, int height){
        JButton buttonWithImage = new JButton();
        buttonWithImage.setBounds(x,y,width,height);
        buttonWithImage.setActionCommand(Integer.toString(subState));
        buttonWithImage.addActionListener(this);
        try{
            ImageIcon imageIcon = new ImageIcon(this.getClass().getClassLoader().getResource(CLASSPATH+imagePath));
            Image scaledIcon = imageIcon.getImage().getScaledInstance(150,100,Image.SCALE_DEFAULT);
            imageIcon = new ImageIcon(scaledIcon); 
            buttonWithImage.setIcon(imageIcon);
            return buttonWithImage;
        }catch(Exception e){
            e.printStackTrace();
            return new JButton("Failed to load image");
        }

    }

    protected JButton BombHintcreateButtonWithImage(Integer subState, String imagePath, int x, int y, int width, int height){
        JButton buttonWithImage = new JButton();
        buttonWithImage.setBounds(x,y,width,height);
        buttonWithImage.setActionCommand(Integer.toString(subState));
        buttonWithImage.addActionListener(this);
        try{
            ImageIcon imageIcon = new ImageIcon(this.getClass().getClassLoader().getResource(CLASSPATH+imagePath));
            Image scaledIcon = imageIcon.getImage().getScaledInstance(180,240,Image.SCALE_DEFAULT);
            imageIcon = new ImageIcon(scaledIcon); 
            buttonWithImage.setIcon(imageIcon);
            return buttonWithImage;
        }catch(Exception e){
            e.printStackTrace();
            return new JButton("Failed to load image");
        }

    }

    protected JLabel createJLabelWithImage(String imagePath, int x, int y, int width, int height){
        JLabel jLabelWithImage = new JLabel();
        System.out.println(this.getClass().getClassLoader().getResource(CLASSPATH + imagePath));
        try{
            // リソースから画像を取得
            ImageIcon imageIcon = new ImageIcon(this.getClass().getClassLoader().getResource(CLASSPATH + imagePath));
            Image scaledIcon = imageIcon.getImage().getScaledInstance(600,800,Image.SCALE_DEFAULT);
            imageIcon = new ImageIcon(scaledIcon); 
            jLabelWithImage.setIcon(imageIcon);
            jLabelWithImage.setIcon(imageIcon);
            jLabelWithImage.setBounds(x,y,width,height);
            return jLabelWithImage;
        }catch(Exception e){
            System.out.println("No such file exists or file path may be wrong.");
            e.printStackTrace();
            return jLabelWithImage;
        }

    }

    protected void addLabel(JLabel labelToAdd, int layer){
        layeredPane.add(labelToAdd, layer);
        layeredPane.revalidate();
        layeredPane.repaint();
    }

    protected void addButton(JButton buttonToAdd, int layer){
        layeredPane.add(buttonToAdd, layer);
        layeredPane.revalidate();
        layeredPane.repaint();
    }

    //数字入力を表示するラベル
    protected JLabel createNumberDisplayLabel() {
        displayLabel = new JLabel("", SwingConstants.CENTER);
        displayLabel.setBounds(33, 75, 532, 122);
        displayLabel.setFont(controller.getFont("DS-DIGI.TTF", 90));
        displayLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        layeredPane.add(displayLabel, LAYER_UTIL_SECOND);
        return displayLabel;
    }

    //数字入力用のボタンを配置するパネル
    protected JPanel createNumberButtonPanel(String imagePath) {
        // パネルにボタンを配置 
        NumberButtonPanel = new JPanel();
        NumberButtonPanel.setBounds(0,0,600,800);
        NumberButtonPanel.setLayout(null);
        
        JLabel bomb = createJLabelWithImage(imagePath, 0, 0, 600, 800);
        NumberButtonPanel.add(bomb);
        createNumberDisplayLabel();
        
        //ボタンに数字またはEnter,Clearを与えて作成
        createNumberButton("Number_7",33,197,177,118);
        createNumberButton("Number_8",210,197,187,118);
        createNumberButton("Number_9",397,197,170,118);
        createNumberButton("Number_4",33,315,177,128);
        createNumberButton("Number_5",210,315,187,128);
        createNumberButton("Number_6",397,315,170,128);
        createNumberButton("Number_1",33,443,177,147);
        createNumberButton("Number_2",210,443,187,147);
        createNumberButton("Number_3",397,443,170,147);
        createNumberButton("Enter",33,590,177,130);
        createNumberButton("Number_0",210,590,187,130);
        createNumberButton("Clear",397,590,170,130);

        
        
        layeredPane.add(NumberButtonPanel, LAYER_UTIL_FIRST);
        layeredPane.revalidate();
        layeredPane.repaint();

        return NumberButtonPanel;
    }

    //数字入力用のボタンを作成、NumberButtonPanelに貼り付け
    public void createNumberButton(String number,int x,int y, int width, int height) {
        JButton numberButton = new JButton();
        numberButton.setBounds(x,y,width,height);
        numberButton.setActionCommand(number);
        numberButton.addActionListener(this);
        numberButton.setOpaque(false);
        numberButton.setContentAreaFilled(false);
        numberButton.setBorderPainted(false);
        NumberButtonPanel.add(numberButton);
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
