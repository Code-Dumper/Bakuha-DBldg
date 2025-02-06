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
public class TwoFloorPanel extends FundamentalPanel{

    //private final int SUBSTATE_INITIAL = 1 FundamentalPanelから継承している
    private final int SUBSTATE_ROOM = 2; //ドア入ったとこ
    private final int SUBSTATE_AIRTRACK = 3; //エアトラックの部屋
    private final int SUBSTATE_RADIATION = 4; //放射線の部屋
    private final int SUBSTATE_PHOTOELECTRIC = 5; //光電効果の部屋
    private final int SUBSTATE_EQUIPOTENTIAL = 6; //等電位
    private final int SUBSTATE_2FBOMB = 7;
    private final int SUBSTATE_AIRTRACK_NOTE = 8; //エアトラノート
    private final int SUBSTATE_AIRTRACK_WHITEBOARD = 9; //エアトラホワイトボード
    private final int SUBSTATE_EQUIPOTENTIAL_NOTE = 10; //等電位線ノート
    private final int SUBSTATE_EQUIPOTENTIAL_WHITEBOARD = 11; //等電位線ホワイトボード
    private final int SUBSTATE_PHOTOELECTRIC_NOTE = 12; //光電効果ノート
    private final int SUBSTATE_PHOTOELECTRIC_WHITEBOARD = 13; //光電効果ホワイトボード
    private final int SUBSTATE_RADIATION_NOTE = 14; //放射線ノート
    private final int SUBSTATE_RADIATION_WHITEBOARD = 15; //放射線ホワイトボード
    
    public TwoFloorPanel(GameController controller){
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

            //初期状態の時、上に部屋、下に全体ロビー
            case SUBSTATE_INITIAL:
                imageLabel = createJLabelWithImage("image-TwoFloor-Lobby.jpg",0,0,600,800);
                buttonToFront = createDirectionalButton(SUBSTATE_ROOM, "LOBBYFRONT");
                buttonToRear = createStateChangeDirectionalButton(State.STATE_LOBBY, "LOBBYREAR");
                JButton bumbButton = BumbcreateButtonWithImage(SUBSTATE_2FBOMB, "image-Bumb.jpg", 50, 490, 150, 100);
                addButton(bumbButton, LAYER_UTIL_FIRST);
                this.addLabel(imageLabel, LAYER_FIGURE_FIRST);
                
            break;

            //部屋の時、上に等電位、右に光電、下にロビー(INITIAL)
            case SUBSTATE_ROOM:
                imageLabel = createJLabelWithImage("image-TwoFloor-Room.jpg", 0,0,600,800);
                buttonToFront = createDirectionalButton(SUBSTATE_EQUIPOTENTIAL, "FRONT");
                buttonToRight = createDirectionalButton(SUBSTATE_PHOTOELECTRIC, "RIGHT");
                buttonToRear = createDirectionalButton(SUBSTATE_INITIAL, "REAR");
                addLabel(imageLabel, LAYER_FIGURE_FIRST);
            break;

            //エアトラックの時、右に放射、左に等電位
            case SUBSTATE_AIRTRACK:
                imageLabel = createJLabelWithImage("image-TwoFloor-AirTrack.jpg", 0,0,600,800);
                buttonToRight = createDirectionalButton(SUBSTATE_RADIATION, "RIGHT");
                buttonToLeft = createDirectionalButton(SUBSTATE_EQUIPOTENTIAL, "LEFT");
                JButton AirtrackNoteButton = createButtonWithoutImage(SUBSTATE_AIRTRACK_NOTE,240,500,50,60);
                JButton AirtrackWhitebordButton = createButtonWithoutImage(SUBSTATE_AIRTRACK_WHITEBOARD,0,30,100,300);
                addButton(AirtrackNoteButton, LAYER_UTIL_FIRST);
                addButton(AirtrackWhitebordButton, LAYER_UTIL_FIRST);
                addLabel(imageLabel, LAYER_FIGURE_FIRST);
                
            break;
            
            //放射の時、右に光電、下にエアトラ
            case SUBSTATE_RADIATION:
                imageLabel = createJLabelWithImage("image-TwoFloor-Radiation.jpg",0,0,600,800);
                buttonToRight = createDirectionalButton(SUBSTATE_PHOTOELECTRIC, "RIGHT");
                buttonToRear = createDirectionalButton(SUBSTATE_AIRTRACK, "REAR");
                JButton RadiationNoteButton = createButtonWithoutImage(SUBSTATE_RADIATION_NOTE,240,500,50,60);
                JButton RadiationWhitebordButton = createButtonWithoutImage(SUBSTATE_RADIATION_WHITEBOARD,270,20,300,120);
                addButton(RadiationNoteButton, LAYER_UTIL_FIRST);
                addButton(RadiationWhitebordButton, LAYER_UTIL_FIRST);
                addLabel(imageLabel, LAYER_FIGURE_FIRST);
            break;

            //光電の時、右に放射、左に部屋
            case SUBSTATE_PHOTOELECTRIC:
                imageLabel = createJLabelWithImage("image-TwoFloor-Photoelectric.jpg", 0,0,600,800);
                buttonToRight = createDirectionalButton(SUBSTATE_RADIATION, "RIGHT");
                buttonToRear = createDirectionalButton(SUBSTATE_ROOM, "LEFT");
                JButton PhotoelectricNoteButton = createButtonWithoutImage(SUBSTATE_PHOTOELECTRIC_NOTE,240,500,50,60);
                JButton PhotoelectricWhitebordButton = createButtonWithoutImage(SUBSTATE_PHOTOELECTRIC_WHITEBOARD,80,30,440,210);
                addButton(PhotoelectricNoteButton, LAYER_UTIL_FIRST);
                addButton(PhotoelectricWhitebordButton, LAYER_UTIL_FIRST);
                addLabel(imageLabel, LAYER_FIGURE_FIRST);
            break;

            //等電位のとき、右にエアトラ、下に部屋
            case SUBSTATE_EQUIPOTENTIAL:
                imageLabel = createJLabelWithImage("image-TwoFloor-Equipotential.jpg", 0,0,600,800);
                buttonToRight = createDirectionalButton(SUBSTATE_AIRTRACK, "RIGHT");
                buttonToRear = createDirectionalButton(SUBSTATE_ROOM, "REAR");
                JButton EquipotentialNoteButton = createButtonWithoutImage(SUBSTATE_EQUIPOTENTIAL_NOTE,240,620,80,60);
                JButton EquipotentialWhitebordButton = createButtonWithoutImage(SUBSTATE_EQUIPOTENTIAL_WHITEBOARD,240,30,290,120);
                addButton(EquipotentialNoteButton, LAYER_UTIL_FIRST);
                addButton(EquipotentialWhitebordButton, LAYER_UTIL_FIRST);
                addLabel(imageLabel, LAYER_FIGURE_FIRST);
            break;
            
            case SUBSTATE_2FBOMB:
                //番号確認、番号入力用のコンポーネントを表示
                createNumberButtonPanel("image-Bumb-Detail2.jpg");
                buttonToRear = createDirectionalButton(SUBSTATE_INITIAL, "BOMBREAR");
                
            break;

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
        switch(actionCommand){
            case "1":
                changeSubState(SUBSTATE_INITIAL);
            break;

            case "2": //SUBSTATE_ROOM
                changeSubState(SUBSTATE_ROOM);
            break;

            case "3": //SUBSTATE_AIRTRACK
                changeSubState(SUBSTATE_AIRTRACK);
            break;

            case "4": //SUBSTATE_RADIATION
                changeSubState(SUBSTATE_RADIATION);
            break;

            case "5": //SUBSTATE_PHOTOELECTRIC
                changeSubState(SUBSTATE_PHOTOELECTRIC);
            break;

            case "6": //SUBSTATE_EQUIPOTENTIAL
                changeSubState(SUBSTATE_EQUIPOTENTIAL);
            break;
            
            case "7":
                if(controller.isDisarmedCurrentFloorBomb()) {
                    JOptionPane.showMessageDialog(this, "すでに解除されている...。");
                }
                else{
                    changeSubState(SUBSTATE_2FBOMB);
                }
            break;

            case "8":
                changeSubState(SUBSTATE_AIRTRACK_NOTE);
            break;

            case "9":
                changeSubState(SUBSTATE_AIRTRACK_WHITEBOARD);
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

            //爆弾解除のためのボタン入力
            case "Number_1":
                controller.inputCodeToCurrentStateBomb(1);
                displayLabel.setText(String.valueOf(controller.getCodeOfCurrentStateBomb()));
            break;

            case "Number_2":
                controller.inputCodeToCurrentStateBomb(2);
                displayLabel.setText(String.valueOf(controller.getCodeOfCurrentStateBomb()));
            break;

            case "Number_3":
                controller.inputCodeToCurrentStateBomb(3);
                displayLabel.setText(String.valueOf(controller.getCodeOfCurrentStateBomb()));
            break;

            case "Number_4":
                controller.inputCodeToCurrentStateBomb(4);
                displayLabel.setText(String.valueOf(controller.getCodeOfCurrentStateBomb()));
            break;

            case "Number_5":
                controller.inputCodeToCurrentStateBomb(5);
                displayLabel.setText(String.valueOf(controller.getCodeOfCurrentStateBomb()));
            break;

            case "Number_6":
                controller.inputCodeToCurrentStateBomb(6);
                displayLabel.setText(String.valueOf(controller.getCodeOfCurrentStateBomb()));
            break;

            case "Number_7":
                controller.inputCodeToCurrentStateBomb(7);
                displayLabel.setText(String.valueOf(controller.getCodeOfCurrentStateBomb()));
            break;

            case "Number_8":
                controller.inputCodeToCurrentStateBomb(8);
                displayLabel.setText(String.valueOf(controller.getCodeOfCurrentStateBomb()));
            break;

            case "Number_9":
                controller.inputCodeToCurrentStateBomb(9);
                displayLabel.setText(String.valueOf(controller.getCodeOfCurrentStateBomb()));
            break;

            case "Number_0":
                controller.inputCodeToCurrentStateBomb(0);
                displayLabel.setText(String.valueOf(controller.getCodeOfCurrentStateBomb()));
            break;

            case "Clear":
                controller.resetCodeOfCurrentStateBomb();
                displayLabel.setText("");
            break;

            case "Enter":
                if(controller.disarmCurrentStateBombByCurrentCode()) {
                    displayLabel.setText("Correct!");
                    JOptionPane.showMessageDialog(this, "2階の爆弾の解除に成功した!");
                    changeSubState(SUBSTATE_INITIAL);
                }
                else {
                    displayLabel.setText("Incorrect");
                    controller.resetCodeOfCurrentStateBomb();
                }
            break;

        }
    }
}
