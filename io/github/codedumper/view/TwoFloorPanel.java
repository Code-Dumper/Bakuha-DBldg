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
    private final int SUBSTATE_2FBOMB_HINT = 16; // 2Fの爆弾解除のためのヒント

    //整数とSUBSTATEを対応させるための配列
    private final int[] SUBSTATE = new int[] {
        0,
        SUBSTATE_INITIAL,
        SUBSTATE_ROOM,
        SUBSTATE_AIRTRACK,
        SUBSTATE_RADIATION,
        SUBSTATE_PHOTOELECTRIC,
        SUBSTATE_EQUIPOTENTIAL,
        SUBSTATE_2FBOMB,
        SUBSTATE_AIRTRACK_NOTE,
        SUBSTATE_AIRTRACK_WHITEBOARD,
        SUBSTATE_EQUIPOTENTIAL_NOTE,
        SUBSTATE_EQUIPOTENTIAL_WHITEBOARD,
        SUBSTATE_PHOTOELECTRIC_NOTE,
        SUBSTATE_PHOTOELECTRIC_WHITEBOARD,
        SUBSTATE_RADIATION_NOTE,
        SUBSTATE_RADIATION_WHITEBOARD,
        SUBSTATE_2FBOMB_HINT
    };
    
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
        buttonToFront = buttonToLeft = buttonToRear = buttonToRight = bombHintButton = null;
        //subStateに対応したボタンと画像を読み込む
        switch(subState){

            //初期状態の時、上に部屋、下に全体ロビー
            case SUBSTATE_INITIAL:
                imageLabel = createJLabelWithImage("image-TwoFloor-Lobby.jpg",0,0,600,800);
                buttonToFront = createDirectionalButton(SUBSTATE_ROOM, "LOBBYFRONT");
                buttonToRear = createStateChangeDirectionalButton(State.STATE_LOBBY, "LOBBYREAR");
                bombHintButton = createDirectionalButton(SUBSTATE_2FBOMB_HINT, "BOMBHINT");
                JButton bombButton = BumbcreateButtonWithImage(SUBSTATE_2FBOMB, "image-Bomb.jpg", 50, 490, 150, 100);
                addButton(bombButton, LAYER_UTIL_FIRST);
                addButton(bombHintButton,LAYER_UTIL_FIRST);
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
                JButton RadiationNoteButton = createButtonWithoutImage(SUBSTATE_RADIATION_NOTE,140,570,175,100);
                JButton RadiationWhitebordButton = createButtonWithoutImage(SUBSTATE_RADIATION_WHITEBOARD,240,20,300,120);
                addButton(RadiationNoteButton, LAYER_UTIL_FIRST);
                addButton(RadiationWhitebordButton, LAYER_UTIL_FIRST);
                addLabel(imageLabel, LAYER_FIGURE_FIRST);
            break;

            //光電の時、右に放射、左に部屋
            case SUBSTATE_PHOTOELECTRIC:
                imageLabel = createJLabelWithImage("image-TwoFloor-Photoelectric.jpg", 0,0,600,800);
                buttonToRight = createDirectionalButton(SUBSTATE_RADIATION, "RIGHT");
                buttonToRear = createDirectionalButton(SUBSTATE_ROOM, "LEFT");
                JButton PhotoelectricNoteButton = createButtonWithoutImage(SUBSTATE_PHOTOELECTRIC_NOTE,120,600,200,200);
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

            case SUBSTATE_2FBOMB_HINT:
                imageLabel = createJLabelWithImage("image-TwoFloor-BombHint.jpg", 0, 0, 600, 800);
                buttonToRear = createDirectionalButton(SUBSTATE_INITIAL, "BOMBREAR");
                addLabel(imageLabel,LAYER_FIGURE_FIRST);
            break;

            case SUBSTATE_AIRTRACK_NOTE:
                imageLabel = createJLabelWithImage("image-TwoFloor-AirTrack-Note.jpg", 0, 0, 600, 800);
                buttonToRear = createDirectionalButton(SUBSTATE_AIRTRACK, "REAR");
                addLabel(imageLabel,LAYER_FIGURE_FIRST);
            break;

            case SUBSTATE_AIRTRACK_WHITEBOARD:
                imageLabel = createJLabelWithImage("image-TwoFloor-AirTrack-Whiteboard.jpg", 0, 0, 600, 800);
                buttonToRear = createDirectionalButton(SUBSTATE_AIRTRACK, "ITEMREAR");
                addLabel(imageLabel,LAYER_FIGURE_FIRST);
            break;

            case SUBSTATE_RADIATION_NOTE:
                imageLabel = createJLabelWithImage("image-TwoFloor-Radiation-Note.jpg", 0, 0, 600, 800);
                buttonToRear = createDirectionalButton(SUBSTATE_RADIATION, "REAR");
                addLabel(imageLabel,LAYER_FIGURE_FIRST);
            break;

            case SUBSTATE_RADIATION_WHITEBOARD:
                imageLabel = createJLabelWithImage("image-TwoFloor-Radiation-Whiteboard.jpg", 0, 0, 600, 800);
                buttonToRear = createDirectionalButton(SUBSTATE_RADIATION, "ITEMREAR");
                addLabel(imageLabel,LAYER_FIGURE_FIRST);
            break;

            case SUBSTATE_PHOTOELECTRIC_NOTE:
                imageLabel = createJLabelWithImage("image-TwoFloor-Photoelectric-Note.jpg", 0, 0, 600, 800);
                buttonToRear = createDirectionalButton(SUBSTATE_PHOTOELECTRIC, "REAR");
                addLabel(imageLabel,LAYER_FIGURE_FIRST);
            break;

            case SUBSTATE_PHOTOELECTRIC_WHITEBOARD:
                imageLabel = createJLabelWithImage("image-TwoFloor-Photoelectric-Whiteboard.jpg", 0, 0, 600, 800);
                buttonToRear = createDirectionalButton(SUBSTATE_PHOTOELECTRIC, "ITEMREAR");
                addLabel(imageLabel,LAYER_FIGURE_FIRST);
            break;

            case SUBSTATE_EQUIPOTENTIAL_NOTE:
                imageLabel = createJLabelWithImage("image-TwoFloor-Equipotential-Note.jpg", 0, 0, 600, 800);
                buttonToRear = createDirectionalButton(SUBSTATE_EQUIPOTENTIAL, "REAR");
                addLabel(imageLabel,LAYER_FIGURE_FIRST);
            break;

            case SUBSTATE_EQUIPOTENTIAL_WHITEBOARD:
                imageLabel = createJLabelWithImage("image-TwoFloor-Equipotential-Whiteboard.jpg", 0, 0, 600, 800);
                buttonToRear = createDirectionalButton(SUBSTATE_EQUIPOTENTIAL, "ITEMREAR");
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
                if(number_SUBSTATE == 7 && controller.isDisarmedCurrentFloorBomb()) {
                    JOptionPane.showMessageDialog(this, "すでに解除されている。他の階へ行こう。");
                } else {
                    changeSubState(SUBSTATE[number_SUBSTATE]);
                }
            }
        } catch (NumberFormatException ex) {
            //SUBSTATE以外のcase
        }


        //爆弾解除のため入力を処理するコード
        if (actionCommand.startsWith("Number_")) {
            int number = Integer.parseInt(actionCommand.substring(7));
            controller.inputCodeToCurrentStateBomb(number);
            displayLabel.setText(String.valueOf(controller.getCodeOfCurrentStateBomb()));
            return;
        }else if(actionCommand.equals("Enter")){
            if(controller.disarmCurrentStateBombByCurrentCode()) {
                displayLabel.setText("Correct!");
                JOptionPane.showMessageDialog(this, "2階の爆弾の解除に成功した!");
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
