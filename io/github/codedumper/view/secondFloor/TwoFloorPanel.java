package io.github.codedumper.view.secondFloor;

import io.github.codedumper.controller.GameController;
import io.github.codedumper.model.State;
import io.github.codedumper.view.FundamentalPanel;

public class TwoFloorPanel extends FundamentalPanel{

    private final int SUBSTATE_ROOM = 2;
    private final int SUBSTATE_AIRTRACK = 3;
    private final int SUBSTATE_RADIATION = 4;

    public TwoFloorPanel(GameController controller){
        super(controller);
    }

    @Override
    protected void changeSubState(int subState){
        //layeredPane上のコンポーネントを全て削除する
        layeredPane.removeAll();
        this.revalidate();
        this.repaint();

        //subStateに対応したボタンと画像を読み込む
        switch(subState){
            case SUBSTATE_AIRTRACK:
            //TODO エアトラの場面でのボタンの状態と画像を追加する
            break;
            case SUBSTATE_ROOM:
            //TODO 部屋の状態と画像を追加する
            break;
            case SUBSTATE_RADIATION:
            //TODO 放射線の部屋の状態と画像を追加する
            break;
            default:
                buttonToFront = createDirectionalButton(SUBSTATE_AIRTRACK,"FRONT");
                buttonToLeft = createDirectionalButton(SUBSTATE_ROOM,"LEFT");
                buttonToRight = createDirectionalButton(SUBSTATE_RADIATION,"RIGHT");
                buttonToRear = createStateChangeDirectionalButton(State.STATE_LOBBY,"REAR");
                layeredPane.add(createJLabelWithImage("image-TwoFloor-Lobby.jpg",0,0,600,800), LAYER_FIGURE_FIRST);
                layeredPane.add(buttonToFront, LAYER_UTIL_FIRST);
                layeredPane.add(buttonToLeft, LAYER_UTIL_FIRST);
                layeredPane.add(buttonToRight, LAYER_UTIL_FIRST);
                layeredPane.add(buttonToRear, LAYER_UTIL_FIRST);
            break;
        }

    }
}
