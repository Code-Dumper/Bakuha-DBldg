package io.github.codedumper.view;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

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
                buttonToFront = createDirectionalButton(SUBSTATE_ROOM, "FRONT");
                buttonToRear = createStateChangeDirectionalButton(State.STATE_LOBBY, "REAR");
                JButton bumbButton = createButtonWithImage(SUBSTATE_2FBOMB, "image-Bumb.jpg", 25, 400, 200, 200);
                addButton(bumbButton, LAYER_UTIL_FIRST);
                this.addLabel(imageLabel, LAYER_FIGURE_FIRST);
                
            break;

            //エアトラックの時、右に放射、左に等電位
            case SUBSTATE_AIRTRACK:
                imageLabel = createJLabelWithImage("image-TwoFloor-AirTrack.jpg", 0,0,600,800);
                buttonToRight = createDirectionalButton(SUBSTATE_RADIATION, "RIGHT");
                buttonToLeft = createDirectionalButton(SUBSTATE_EQUIPOTENTIAL, "LEFT");
                addLabel(imageLabel, LAYER_FIGURE_FIRST);
                
            break;

            //部屋の時、上に等電位、右に光電、下にロビー(INITIAL)
            case SUBSTATE_ROOM:
                imageLabel = createJLabelWithImage("image-TwoFloor-Room.jpg", 0,0,600,800);
                buttonToFront = createDirectionalButton(SUBSTATE_EQUIPOTENTIAL, "FRONT");
                buttonToRight = createDirectionalButton(SUBSTATE_PHOTOELECTRIC, "RIGHT");
                buttonToRear = createDirectionalButton(SUBSTATE_INITIAL, "REAR");
                addLabel(imageLabel, LAYER_FIGURE_FIRST);
            break;

            //放射の時、右に光電、下にエアトラ
            case SUBSTATE_RADIATION:
                imageLabel = createJLabelWithImage("image-TwoFloor-Radiation.jpg",0,0,600,800);
                buttonToRight = createDirectionalButton(SUBSTATE_PHOTOELECTRIC, "RIGHT");
                buttonToRear = createDirectionalButton(SUBSTATE_AIRTRACK, "REAR");
                addLabel(imageLabel, LAYER_FIGURE_FIRST);
            break;

            //光電の時、右に放射、左に部屋
            case SUBSTATE_PHOTOELECTRIC:
                imageLabel = createJLabelWithImage("image-TwoFloor-Photoelectric.jpg", 0,0,600,800);
                buttonToRight = createDirectionalButton(SUBSTATE_RADIATION, "RIGHT");
                buttonToRear = createDirectionalButton(SUBSTATE_ROOM, "REAR");
                addLabel(imageLabel, LAYER_FIGURE_FIRST);
            break;

            //等電位のとき、右にエアトラ、下に部屋
            case SUBSTATE_EQUIPOTENTIAL:
                imageLabel = createJLabelWithImage("image-TwoFloor-Equipotential.jpg", 0,0,600,800);
                buttonToRight = createDirectionalButton(SUBSTATE_AIRTRACK, "RIGHT");
                buttonToRear = createDirectionalButton(SUBSTATE_ROOM, "REAR");
                addLabel(imageLabel, LAYER_FIGURE_FIRST);
            break;
            //TODO 完成してない
            case SUBSTATE_2FBOMB:
                imageLabel = createJLabelWithImage("image-TwoFloor-Lobby.jpg", 0, 0, 600, 800);
                buttonToRear = createDirectionalButton(SUBSTATE_INITIAL, "REAR");
                //番号確認、番号入力用のコンポーネントを表示
                //
                createNumberDisplayLabel();
                createNumberButtonPanel();
                addLabel(imageLabel, LAYER_FIGURE_FIRST);
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
                changeSubState(SUBSTATE_2FBOMB);
            break;

            case "STATE_LOBBY":
                controller.transition(State.STATE_LOBBY);
            break;

            //爆弾解除の数字入力ボタンからの呼び出し
            case "8":
                if (inputNumbers.length() < 4) {
                    inputNumbers.append(8);
                    displayLabel.setText(inputNumbers.toString());
                }
            break;

            case "Clear":
                inputNumbers.setLength(0);
                displayLabel.setText("");
            break;
        }
    }
}
