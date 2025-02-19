package io.github.codedumper.view;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import io.github.codedumper.controller.GameController;
import io.github.codedumper.model.State;


//このクラスは4Fの中での状態遷移と4Fの画像とボタンの表示を担うクラスです。
//このクラスはFundamentalPanelを継承し、changeSubStateをオーバーライドしています。
//4Fには5つの状態が定義されており、ロビー、部屋、分子量、比色、反応速度に対応します。
//SUBSTATEはFundamentalPanelの実装に合わせ、int型として定義しており、この実装は2025/01/30時点での仕様に準拠しています。
//画像ファイルはviewパッケージの下に位置していることが仮定されています。
//このクラスはスレッドセーフではありません。
public class FloorFourPanel extends FundamentalPanel{

    //private final int SUBSTATE_INITIAL = 1 FundamentalPanelから継承している
    private final int SUBSTATE_ROOM = 2; //部屋
    private final int SUBSTATE_MOLECULAR = 3; //分子量
    private final int SUBSTATE_COLORRATION = 4; //比色
    private final int SUBSTATE_REACTIONSPEED = 5; //反応速度
    private final int SUBSTATE_4FBOMB = 6; //4階爆弾
    private final int SUBSTATE_MOLECULAR_NOTE = 7; //分子量ノート
    private final int SUBSTATE_MOLECULAR_SLIDE = 8; //分子量スライド
    private final int SUBSTATE_COLORRATION_NOTE = 9; //比色ノート
    private final int SUBSTATE_COLORRATION_SLIDE = 10; //比色スライド
    private final int SUBSTATE_REACTIONSPEED_NOTE = 11; //反応速度ノート
    private final int SUBSTATE_REACTIONSPEED_SLIDE = 12; //反応速度スライド


    //整数とSUBSTATEを対応させるための配列
    private final int[] SUBSTATE = new int[] {
        0,
        SUBSTATE_INITIAL,
        SUBSTATE_ROOM,
        SUBSTATE_MOLECULAR,
        SUBSTATE_COLORRATION,
        SUBSTATE_REACTIONSPEAD,
        SUBSTATE_4FBOMB,
    };
    
    public FloorFourPanel(GameController controller){
        super(controller);
        changeSubState(currentSubState);
    }

    @Override
    protected void changeSubState(int subState){
        //layeredPane上のコンポーネントを全て削除する
        layeredPane.removeAll();
        this.revalidate();
        this.repaint();
        this.currentSubState = subState;
        JLabel imageLabel;
        //TODO 禁忌的なnull手法
        buttonToFront = buttonToLeft = buttonToRear = buttonToRight = null;
        //subStateに対応したボタンと画像を読み込む
        switch(subState){

            //初期状態の時、上に実験部屋、右に全体ロビー
            case SUBSTATE_INITIAL:
                imageLabel = createJLabelWithImage("image-FourFloor-Lobby.jpg",0,0,600,800);
                buttonToFront = createDirectionalButton(SUBSTATE_ROOM, "LOBBYFRONT");
                buttonToRear = createStateChangeDirectionalButton(State.STATE_LOBBY, "LOBBYREAR");
                JButton bumbButton = BumbcreateButtonWithImage(SUBSTATE_4FBOMB, "image-Bomb.jpg", 8, 490, 150, 100);
                addButton(bumbButton, LAYER_UTIL_FIRST);
                this.addLabel(imageLabel, LAYER_FIGURE_FIRST);
                
            break;

            //へやの時、上に反応速度、右に分子量、下にロビー(INITIAL)
            case SUBSTATE_ROOM:
                imageLabel = createJLabelWithImage("image-FourFloor-Room.jpg", 0,0,600,800);
                buttonToFront = createDirectionalButton(SUBSTATE_REACTIONSPEED, "FRONT");
                buttonToRight = createDirectionalButton(SUBSTATE_MOLECULAR, "RIGHT");
                buttonToRear = createDirectionalButton(SUBSTATE_INITIAL, "REAR");
                addLabel(imageLabel, LAYER_FIGURE_FIRST);
                
            break;

            //分子量の時、上に部屋、左に比色、下に反応速度
            case SUBSTATE_MOLECULAR:
                imageLabel = createJLabelWithImage("image-FourFloor-Molecular.jpg", 0,0,600,800);
                buttonToFront = createDirectionalButton(SUBSTATE_ROOM, "FRONT");
                buttonToLeft = createDirectionalButton(SUBSTATE_COLORRATION, "LEFT");
                buttonToRear = createDirectionalButton(SUBSTATE_REACTIONSPEED, "REAR");
                JButton molecularnote = createButtonWithoutImage(SUBSTATE_MOLECULAR_NOTE, 200, 640, 110, 50);
                JButton molecularslide = createButtonWithoutImage(SUBSTATE_MOLECULAR_SLIDE, 0, 100, 310, 350);
                addButton(molecularnote, LAYER_UTIL_FIRST);
                addButton(molecularslide,LAYER_UTIL_FIRST);
                
                addLabel(imageLabel, LAYER_FIGURE_FIRST);
            break;

            //反応速度の時、右に比色、下に分子量、左に部屋
                case SUBSTATE_REACTIONSPEED:
                imageLabel = createJLabelWithImage("image-FourFloor-Reactionspeed.jpg", 0,0,600,800);
                buttonToRight = createDirectionalButton(SUBSTATE_COLORRATION, "RIGHT");
                buttonToRear = createDirectionalButton(SUBSTATE_MOLECULAR, "REAR");
                buttonToLeft = createDirectionalButton(SUBSTATE_ROOM, "REAR");
                JButton reactionspeednote = createButtonWithoutImage(SUBSTATE_REACTIONSPEED_NOTE, 150, 680, 230, 120);
                JButton reactionspeedslide = createButtonWithoutImage(SUBSTATE_REACTIONSPEED_SLIDE, 300, 0, 300, 300);
                addButton(reactionspeednote,LAYER_UTIL_FIRST);
                addButton(reactionspeedslide, LAYER_UTIL_FIRST);
                addLabel(imageLabel, LAYER_FIGURE_FIRST);
            break;
            
            //比色の時、右に分子量、下に反応速度
            case SUBSTATE_COLORRATION:
                imageLabel = createJLabelWithImage("image-FourFloor-Colorration.jpg",0,0,600,800);
                buttonToRight = createDirectionalButton(SUBSTATE_MOLECULAR, "RIGHT");

                buttonToRear = createDirectionalButton(SUBSTATE_REACTIONSPEED, "REAR");
                JButton colorrationnote = createButtonWithoutImage(SUBSTATE_COLORRATION_NOTE, 170, 590, 200, 120);
                JButton colorrationslide = createButtonWithoutImage(SUBSTATE_COLORRATION_SLIDE, 220, 20, 220, 170);
                addButton(colorrationnote,LAYER_UTIL_FIRST);
                addButton(colorrationslide, LAYER_UTIL_FIRST);
                addLabel(imageLabel, LAYER_FIGURE_FIRST);
            break;

            case SUBSTATE_4FBOMB:
                createNumberButtonPanel("image-Bumb-Detail2.jpg");
                buttonToRear = createDirectionalButton(SUBSTATE_INITIAL, "BUMBREAR");
                
            break;

            case SUBSTATE_MOLECULAR_NOTE:
                imageLabel = createJLabelWithImage("image-FourFloor-Molecular-Note.jpg", 0,0,600,800);
                buttonToRear = createDirectionalButton(SUBSTATE_MOLECULAR, "ITEMREAR");
                addLabel(imageLabel, LAYER_FIGURE_FIRST);
            break;

            case SUBSTATE_MOLECULAR_SLIDE:
                imageLabel = createJLabelWithImage("image-FourFloor-Molecular-Slide.jpg", 0,0,600,800);
                buttonToRear = createDirectionalButton(SUBSTATE_MOLECULAR, "ITEMREAR");
                addLabel(imageLabel, LAYER_FIGURE_FIRST);
            break;
            
            
            case SUBSTATE_COLORRATION_NOTE:
            imageLabel = createJLabelWithImage("image-FourFloor-Colorration-Note.jpg", 0, 0, 600, 800);
            buttonToRear = createDirectionalButton(SUBSTATE_COLORRATION, "ITEMREAR");
            addLabel(imageLabel,LAYER_FIGURE_FIRST);
            break;

            case SUBSTATE_COLORRATION_SLIDE:
            imageLabel = createJLabelWithImage("image-FourFloor-Colorration-Slide.jpg", 0, 0, 600, 800);
            buttonToRear = createDirectionalButton(SUBSTATE_COLORRATION, "ITEMREAR");
            addLabel(imageLabel,LAYER_FIGURE_FIRST);
            break;

            case SUBSTATE_REACTIONSPEED_NOTE:
            imageLabel = createJLabelWithImage("image-FourFloor-Reactionspeed-Note.jpg", 0, 0, 600, 800);
            buttonToRear = createDirectionalButton(SUBSTATE_REACTIONSPEED, "ITEMREAR");
            addLabel(imageLabel,LAYER_FIGURE_FIRST);
            break;

            case SUBSTATE_REACTIONSPEED_SLIDE:
            imageLabel = createJLabelWithImage("image-FourFloor-Reactionspeed-Slide.jpg", 0, 0, 600, 800);
            buttonToRear = createDirectionalButton(SUBSTATE_REACTIONSPEED, "ITEMREAR");
            addLabel(imageLabel,LAYER_FIGURE_FIRST);
            break;

            
            default:
                throw new IllegalArgumentException("No Such SUBSTATE defined\n.");
        }
        System.out.println("Succenssfully change substate to " + subState);
        updateButton();
        this.revalidate();
        this.repaint();
    }

    
    @Override
    public void actionPerformed(ActionEvent e){
        String actionCommand = e.getActionCommand();
        System.out.println("Event is" + e);

        //SUBSTATE
        try {
            int number_SUBSTATE = Integer.parseInt(actionCommand);
            if (number_SUBSTATE > 0 && number_SUBSTATE < SUBSTATE.length) {
                if(number_SUBSTATE == 6 && controller.isDisarmedCurrentFloorBomb()) {
                    JOptionPane.showMessageDialog(this, "すでに解除されている。他の階へ行こう。");
                } else {
                    changeSubState(SUBSTATE[number_SUBSTATE]);
                }
            }
        } catch (NumberFormatException ex) {
            //SUBSTATE以外のcase
        }

        //爆弾解除のための数字入力
        //爆弾解除のためのボタンであれば、必ずactionCommandはNumber_から始まるのでその区別を行う
        if (actionCommand.startsWith("Number_")) {
            int number = Integer.parseInt(actionCommand.substring(7));
            controller.inputCodeToCurrentStateBomb(number);
            displayLabel.setText(String.valueOf(controller.getCodeOfCurrentStateBomb()));
            return;
        }else if(actionCommand.equals("Enter")){
            if(controller.disarmCurrentStateBombByCurrentCode()) {
                displayLabel.setText("Correct!");
                JOptionPane.showMessageDialog(this, "4階の爆弾の解除に成功した!");
                changeSubState(SUBSTATE_INITIAL);
                if(controller.areAllBombsDisarmed()) {
                    JOptionPane.showMessageDialog(this, "全ての階の爆弾を解除した!");
                    controller.transition(State.STATE_GAMECLEAR);
                }
            }
            else {
                displayLabel.setText("Incorrect");
                controller.resetCodeOfCurrentStateBomb();
            }
        }else if(actionCommand.equals("Clear")){
            controller.resetCodeOfCurrentStateBomb();
            displayLabel.setText("");
        }

        //それ以外の状態遷移を処理する
        switch(actionCommand){

            case "STATE_LOBBY":
                controller.transition(State.STATE_LOBBY);
            break;
        }
    }
}
