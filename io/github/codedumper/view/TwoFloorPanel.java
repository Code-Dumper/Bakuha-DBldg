package io.github.codedumper.view;

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
        JLabel imageLabel;
        //subStateに対応したボタンと画像を読み込む
        switch(subState){
            //初期状態の時、上に部屋、下に全体ロビー
            case SUBSTATE_INITIAL:
                imageLabel = createJLabelWithImage("image-TwoFloor-Lobby.jpg",0,0,600,800);
                buttonToFront = createDirectionalButton(SUBSTATE_ROOM, "FRONT");
                buttonToRear = createStateChangeDirectionalButton(State.STATE_LOBBY, "REAR");
                updateButton();
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

            default:
                throw new IllegalArgumentException("No Such SUBSTATE defined\n.");
        }
        System.out.println("Succenssfully change substate to " + subState);
        this.revalidate();
        this.repaint();
    }
}
