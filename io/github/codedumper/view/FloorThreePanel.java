package io.github.codedumper.view;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import io.github.codedumper.controller.GameController;
import io.github.codedumper.model.State;


//このクラスは2Fの中での状態遷移と2Fの画像とボタンの表示を担うクラスです。
//このクラスはFundamentalPanelを継承し、changeSubStateをオーバーライドしています。
//2Fには6つの状態が定義されており、ロビー、ロビーから入った場所、エアトラック、放射線、光電効果、等電位の部屋に対応します。
//SUBSTATEはFundamentalPanelの実装に合わせ、int型として定義しており、この実装は2025/01/30時点での仕様に準拠しています。
//画像ファイルはviewパッケージの下に位置していることが仮定されています。
//このクラスはスレッドセーフではありません。
public class FloorThreePanel extends FundamentalPanel{

    //private final int SUBSTATE_INITIAL = 1 FundamentalPanelから継承している
    private final int SUBSTATE_A = 2; //びっけんべや入り口
    private final int SUBSTATE_B = 3; //謁見部屋入り口
    private final int SUBSTATE_ROOM_A = 4; //びっけんべや
    private final int SUBSTATE_ROOM_B = 5; //謁見部屋
    private final int SUBSTATE_INFRARED = 6; //赤外線
    private final int SUBSTATE_INFRARED_ROOM = 7; //赤外線部屋
    private final int SUBSTATE_LIGHTSPEED = 8; //光速度部屋
    private final int SUBSTATE_3FBOMB = 9; //3階爆弾
    private final int SUBSTATE_EQUIPOTENTIAL_NOTE = 10; //等電位線ノート
    private final int SUBSTATE_EQUIPOTENTIAL_WHITEBOARD = 11; //等電位線ホワイトボード
    private final int SUBSTATE_PHOTOELECTRIC_NOTE = 12; //光電効果ノート
    private final int SUBSTATE_PHOTOELECTRIC_WHITEBOARD = 13; //光電効果ホワイトボード
    private final int SUBSTATE_RADIATION_NOTE = 14; //放射線ノート
    private final int SUBSTATE_RADIATION_WHITEBOARD = 15; //放射線ホワイトボード
    
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
                buttonToFront = createDirectionalButton(SUBSTATE_A, "LOBBYFRONT");
                buttonToRight = createDirectionalButton(SUBSTATE_B,"LOBBYRIGHT");
                buttonToRear = createStateChangeDirectionalButton(State.STATE_LOBBY, "LOBBYREAR");
                JButton bumbButton = BumbcreateButtonWithImage(SUBSTATE_3FBOMB, "image-Bomb.jpg", 8, 490, 150, 100);
                addButton(bumbButton, LAYER_UTIL_FIRST);
                this.addLabel(imageLabel, LAYER_FIGURE_FIRST);
                
            break;

            //謁見部屋前の時、上に部屋、右にびっけんべや、下に3階
            case SUBSTATE_A:
                imageLabel = createJLabelWithImage("image-TwoFloor-AirTrack.jpg", 0,0,600,800);
                buttonToFront = createDirectionalButton(SUBSTATE_ROOM_A, "FRONT");
                buttonToRight = createDirectionalButton(SUBSTATE_B, "RIGHT");
                buttonToRear = createDirectionalButton(SUBSTATE_INITIAL, "REAR");
                addLabel(imageLabel, LAYER_FIGURE_FIRST);
                
            break;

            //びっけん部屋入り口の時、上にROOM、左に謁見部屋、下にロビー(INITIAL)
            case SUBSTATE_B:
                imageLabel = createJLabelWithImage("image-ThreeFloor-B.jpg", 0,0,600,800);
                buttonToFront = createDirectionalButton(SUBSTATE_ROOM_B, "FRONT");
                buttonToRight = createDirectionalButton(SUBSTATE_ROOM_A, "LEFT");
                buttonToRear = createDirectionalButton(SUBSTATE_INITIAL, "REAR");
                addLabel(imageLabel, LAYER_FIGURE_FIRST);
            break;

            //謁見べやの時、上に光速度、下に謁見部屋入り口
            case SUBSTATE_ROOM_A:
                imageLabel = createJLabelWithImage("image-TwoFloor-Photoelectric.jpg", 0,0,600,800);
                buttonToFront = createDirectionalButton(SUBSTATE_LIGHTSPEED, "FRONT");
                buttonToRear = createDirectionalButton(SUBSTATE_A, "REAR");
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
                addLabel(imageLabel, LAYER_FIGURE_FIRST);
            break;

            case SUBSTATE_INFRARED_ROOM:
                imageLabel = createJLabelWithImage("image-ThreeFloor-Infrared-Room.jpg", 0,0,600,800);
                buttonToRight = createDirectionalButton(SUBSTATE_INFRARED_ROOM, "RIGHT");
                buttonToRear = createDirectionalButton(SUBSTATE_ROOM_B, "REAR");
                addLabel(imageLabel, LAYER_FIGURE_FIRST);
            break;
            
            case SUBSTATE_3FBOMB:
                createNumberButtonPanel("image-Bumb-Detail2.jpg");
                buttonToRear = createDirectionalButton(SUBSTATE_INITIAL, "BUMBREAR");
                
            break;

            /* 
            case SUBSTATE_AIRTRACK_NOTE:
            imageLabel = createJLabelWithImage("image-TwoFloor-Airtrack-Note.jpg", 0, 0, 600, 800);
            buttonToRear = createDirectionalButton(SUBSTATE_AIRTRACK, "REAR");
            addLabel(imageLabel,LAYER_FIGURE_FIRST);
            break;

            case SUBSTATE_AIRTRACK_WHITEBOARD:
            imageLabel = createJLabelWithImage("image-TwoFloor-Airtrack-Whiteboard.jpg", 0, 0, 600, 800);
            buttonToRear = createDirectionalButton(SUBSTATE_AIRTRACK, "REAR");
            addLabel(imageLabel,LAYER_FIGURE_FIRST);
            break;

            case SUBSTATE_RADIATION_NOTE:
            imageLabel = createJLabelWithImage("image-TwoFloor-Radiation-Note.jpg", 0, 0, 600, 800);
            buttonToRear = createDirectionalButton(SUBSTATE_RADIATION, "REAR");
            addLabel(imageLabel,LAYER_FIGURE_FIRST);
            break;

            case SUBSTATE_RADIATION_WHITEBOARD:
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
            }
            else {
                displayLabel.setText("Incorrect");
                controller.resetCodeOfCurrentStateBomb();
            }
        }else if(actionCommand.equals("Clear")){
            controller.resetCodeOfCurrentStateBomb();
            displayLabel.setText("");
        }

        //状態遷移のための入力
        switch(actionCommand){
            case "1":
                changeSubState(SUBSTATE_INITIAL);
            break;

            case "2": //SUBSTATE_A
                changeSubState(SUBSTATE_A);
            break;

            case "3": //SUBSTATE_B
                changeSubState(SUBSTATE_B);
            break;

            case "4": //SUBSTATE_ROOM_A
                changeSubState(SUBSTATE_ROOM_A);
            break;

            case "5": //SUBSTATE_ROOM_B
                changeSubState(SUBSTATE_ROOM_B);
            break;

            case "6": //SUBSTATE_INFRARED
                changeSubState(SUBSTATE_INFRARED);
            break;
            
            case "7":
                changeSubState(SUBSTATE_INFRARED_ROOM);
            break;

            case "8":
                changeSubState(SUBSTATE_LIGHTSPEED);
            break;

            case "9":
                if(!controller.isDisarmedCurrentFloorBomb()){
                    changeSubState(SUBSTATE_3FBOMB);
                }else{
                    JOptionPane.showMessageDialog(this, "すでに解除されている。他の階へ行こう");
                }
            break;

            case "10":
                changeSubState(SUBSTATE_EQUIPOTENTIAL_NOTE);
            break;

            case "11":
                changeSubState(SUBSTATE_EQUIPOTENTIAL_WHITEBOARD);
            break;

            case "12":
                changeSubState(SUBSTATE_PHOTOELECTRIC_NOTE);
            break;

            case "13":
                changeSubState(SUBSTATE_PHOTOELECTRIC_WHITEBOARD);
            break;

            case "14":
                changeSubState(SUBSTATE_RADIATION_NOTE);
            break;

            case "15":
                changeSubState(SUBSTATE_RADIATION_WHITEBOARD);
            break;

            case "STATE_LOBBY":
                controller.transition(State.STATE_LOBBY);
            break;

        }
    }
}
