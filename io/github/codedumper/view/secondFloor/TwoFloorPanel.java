package io.github.codedumper.view.secondFloor;

import io.github.codedumper.controller.GameController;
import io.github.codedumper.model.State;
import io.github.codedumper.view.FundamentalPanel;

public class TwoFloorPanel extends FundamentalPanel{

    private final int SUBSTATE_ROOM = 2;
    private final int SUBSTATE_AIRTRACK = 3;
    private final int SUBSTATE_RADIATION = 4;
    //private final int SUBSTATE_INITIAL = 1 FundamentalPanelから継承している
    private final int SUBSTATE_ROOM = 2; //ドア入ったとこ
    private final int SUBSTATE_AIRTRACK = 3; //エアトラックの部屋
    private final int SUBSTATE_RADIATION = 4; //放射線の部屋
    private final int SUBSTATE_PHOTOELECTRIC = 5; //光電効果の部屋
    private final int SUBSTATE_EQUIPOTENTIAL = 6; //等電位

    public TwoFloorPanel(GameController controller){
        super(controller);
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
            default:
                //画像の貼り付けと矢印ボタンの作成と追加
                buttonToFront = createDirectionalButton(SUBSTATE_AIRTRACK,"FRONT");
                buttonToLeft = createDirectionalButton(SUBSTATE_ROOM,"LEFT");
                buttonToRight = createDirectionalButton(SUBSTATE_RADIATION,"RIGHT");
                buttonToRear = createStateChangeDirectionalButton(State.STATE_LOBBY,"REAR");
                layeredPane.add(createJLabelWithImage("image-TwoFloor-Lobby.jpg",0,0,600,800), LAYER_FIGURE_FIRST);
                layeredPane.add(buttonToFront, LAYER_UTIL_FIRST);
                layeredPane.add(buttonToLeft, LAYER_UTIL_FIRST);
                layeredPane.add(buttonToRight, LAYER_UTIL_FIRST);
                layeredPane.add(buttonToRear, LAYER_UTIL_FIRST);

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

    }
}
