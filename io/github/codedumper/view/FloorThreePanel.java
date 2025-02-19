package io.github.codedumper.view;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import io.github.codedumper.controller.GameController;
import io.github.codedumper.model.State;


//このクラスは3Fの中での状態遷移と3Fの画像とボタンの表示を担うクラスです。
//このクラスはFundamentalPanelを継承し、changeSubStateをオーバーライドしています。
//3Fには9つの状態が定義されており、ロビー、謁見部屋、びっけんべや、それぞれ入ったところと入り口、赤外線、赤外線部屋、スペクトル、光速度に対応します。
//SUBSTATEはFundamentalPanelの実装に合わせ、int型として定義しており、この実装は2025/01/30時点での仕様に準拠しています。
//画像ファイルはviewパッケージの下に位置していることが仮定されています。
//このクラスはスレッドセーフではありません。
public class FloorThreePanel extends FundamentalPanel{

    //private final int SUBSTATE_INITIAL = 1 FundamentalPanelから継承している
    private final int SUBSTATE_B = 2; //びっけん部屋入り口
    private final int SUBSTATE_ROOM_B = 3; //びっけん部屋
    private final int SUBSTATE_INFRARED = 4; //赤外線
    private final int SUBSTATE_INFRARED_ROOM = 5; //赤外線部屋
    private final int SUBSTATE_LIGHTSPEED = 6; //光速度部屋
    private final int SUBSTATE_3FBOMB = 7; //3階爆弾
    private final int SUBSTATE_INFRARED_NOTE = 8; //赤外線ノート
    private final int SUBSTATE_INFRARED_SLIDE = 9; //赤外線スライド
    private final int SUBSTATE_INFRARED_PC = 10; //赤外線PC

   

    //整数とSUBSTATEを対応させるための配列
    private final int[] SUBSTATE = new int[] {
        0,
        SUBSTATE_INITIAL,
        SUBSTATE_B,
        SUBSTATE_ROOM_B,
        SUBSTATE_INFRARED,
        SUBSTATE_INFRARED_ROOM,
        SUBSTATE_LIGHTSPEED,
        SUBSTATE_3FBOMB,
        SUBSTATE_INFRARED_NOTE,
        SUBSTATE_INFRARED_SLIDE,
        SUBSTATE_INFRARED_PC
    };
    
    public FloorThreePanel(GameController controller){
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

            //初期状態の時、上に謁見部屋、右にびっけんべや
            case SUBSTATE_INITIAL:
                imageLabel = createJLabelWithImage("image-ThreeFloor-Lobby.jpg",0,0,600,800);
                buttonToRight = createDirectionalButton(SUBSTATE_B,"LOBBYRIGHT");
                buttonToRear = createStateChangeDirectionalButton(State.STATE_LOBBY, "LOBBYREAR");
                JButton bumbButton = BumbcreateButtonWithImage(SUBSTATE_3FBOMB, "image-Bomb.jpg", 8, 490, 150, 100);
                addButton(bumbButton, LAYER_UTIL_FIRST);
                this.addLabel(imageLabel, LAYER_FIGURE_FIRST);
                
            break;

            

            //びっけん部屋入り口の時、上にROOM、左に謁見部屋、下にロビー(INITIAL)
            case SUBSTATE_B:
                imageLabel = createJLabelWithImage("image-ThreeFloor-B.jpg", 0,0,600,800);
                buttonToFront = createDirectionalButton(SUBSTATE_ROOM_B, "FRONT");
                buttonToRear = createDirectionalButton(SUBSTATE_INITIAL, "REAR");
                addLabel(imageLabel, LAYER_FIGURE_FIRST);
            break;

           
            
            //びっけんべやの時、上に赤外線、右に赤外線部屋、下にびっけんべや入り口
            case SUBSTATE_ROOM_B:
                imageLabel = createJLabelWithImage("image-ThreeFloor-Room-B.jpg",0,0,600,800);
                buttonToFront = createDirectionalButton(SUBSTATE_INFRARED, "FRONT");
                buttonToRight = createDirectionalButton(SUBSTATE_INFRARED_ROOM, "RIGHT");
                buttonToRear = createDirectionalButton(SUBSTATE_B, "REAR");
                addLabel(imageLabel, LAYER_FIGURE_FIRST);
            break;

            //赤外線のとき、右に赤外線部屋、下にびっけんべや
            case SUBSTATE_INFRARED:
                imageLabel = createJLabelWithImage("image-ThreeFloor-Infrared.jpg", 0,0,600,800);
                buttonToRight = createDirectionalButton(SUBSTATE_INFRARED_ROOM, "RIGHT");
                buttonToRear = createDirectionalButton(SUBSTATE_ROOM_B, "REAR");
                JButton infrarednote = createButtonWithoutImage(SUBSTATE_INFRARED_NOTE, 60, 580, 150, 80);
                JButton infraredslide = createButtonWithoutImage(SUBSTATE_INFRARED_SLIDE, 240, 70, 185, 230);
                addButton(infraredslide,LAYER_UTIL_FIRST);
                addButton(infrarednote,LAYER_UTIL_FIRST);
                addLabel(imageLabel, LAYER_FIGURE_FIRST);
            break;

            case SUBSTATE_INFRARED_ROOM:
                imageLabel = createJLabelWithImage("image-ThreeFloor-Infrared-Room.jpg", 0,0,600,800);
                buttonToLeft = createDirectionalButton(SUBSTATE_INFRARED, "LEFT");
                buttonToRear = createDirectionalButton(SUBSTATE_ROOM_B, "REAR");
                JButton infraredpc = createButtonWithoutImage(SUBSTATE_INFRARED_PC, 340, 105, 115, 70);
                addButton(infraredpc,LAYER_UTIL_FIRST);
                addLabel(imageLabel, LAYER_FIGURE_FIRST);
            break;
            
            case SUBSTATE_3FBOMB:
                createNumberButtonPanel("image-Bumb-Detail2.jpg");
                buttonToRear = createDirectionalButton(SUBSTATE_INITIAL, "BOMBREAR");
                
            break;

            
            case SUBSTATE_INFRARED_NOTE:
            imageLabel = createJLabelWithImage("image-ThreeFloor-Infrared-Note.jpg", 0, 0, 600, 800);
            buttonToRear = createDirectionalButton(SUBSTATE_INFRARED, "REAR");
            addLabel(imageLabel,LAYER_FIGURE_FIRST);
            break;

            case SUBSTATE_INFRARED_SLIDE:
            imageLabel = createJLabelWithImage("image-ThreeFloor-Infrared-Slide.jpg", 0, 0, 600, 800);
            buttonToRear = createDirectionalButton(SUBSTATE_INFRARED, "REAR");
            addLabel(imageLabel,LAYER_FIGURE_FIRST);
            break;

            case SUBSTATE_INFRARED_PC:
            imageLabel = createJLabelWithImage("image-ThreeFloor-Infrared-PC.jpg", 0, 0, 600, 800);
            buttonToRear = createDirectionalButton(SUBSTATE_INFRARED_ROOM, "REAR");
            addLabel(imageLabel,LAYER_FIGURE_FIRST);
            break;

            /*case SUBSTATE_RADIATION_WHITEBOARD:
            imageLabel = createJLabelWithImage("image-TwoFloor-Radiation-Whiteboard.jpg", 0, 0, 600, 800);
            buttonToRear = createDirectionalButton(SUBSTATE_RADIATION, "REAR");
            addLabel(imageLabel,LAYER_FIGURE_FIRST);
            break;

            case SUBSTATE_PHOTOELECTRIC_NOTE:
            imageLabel = createJLabelWithImage("image-TwoFloor-Photoelectric-Note.jpg", 0, 0, 600, 800);
            buttonToRear = createDirectionalButton(SUBSTATE_PHOTOELECTRIC, "REAR");
            addLabel(imageLabel,LAYER_FIGURE_FIRST);
            break;

            case SUBSTATE_PHOTOELECTRIC_WHITEBOARD:
            imageLabel = createJLabelWithImage("image-TwoFloor-Photoelectric-Whiteboard.jpg", 0, 0, 600, 800);
            buttonToRear = createDirectionalButton(SUBSTATE_PHOTOELECTRIC, "REAR");
            addLabel(imageLabel,LAYER_FIGURE_FIRST);
            break;

            case SUBSTATE_EQUIPOTENTIAL_NOTE:
            imageLabel = createJLabelWithImage("image-TwoFloor-Equipotential-Note.jpg", 0, 0, 600, 800);
            buttonToRear = createDirectionalButton(SUBSTATE_EQUIPOTENTIAL, "REAR");
            addLabel(imageLabel,LAYER_FIGURE_FIRST);
            break;

            case SUBSTATE_EQUIPOTENTIAL_WHITEBOARD:
            imageLabel = createJLabelWithImage("image-TwoFloor-Equipotential-Whiteboard.jpg", 0, 0, 600, 800);
            buttonToRear = createDirectionalButton(SUBSTATE_EQUIPOTENTIAL, "REAR");
            addLabel(imageLabel,LAYER_FIGURE_FIRST);
            break;*/

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
                if(number_SUBSTATE == 9 && controller.isDisarmedCurrentFloorBomb()) {
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
                JOptionPane.showMessageDialog(this, "3階の爆弾の解除に成功した!");
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
