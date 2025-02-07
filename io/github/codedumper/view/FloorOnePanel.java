package io.github.codedumper.view;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

import io.github.codedumper.controller.GameController;
import io.github.codedumper.model.State;


//このクラスは1Fの中での状態遷移と1Fの画像とボタンの表示を担うクラスです。
//このクラスはFundamentalPanelを継承し、changeSubStateをオーバーライドしています。
//1Fには2つの状態が定義されており、ロビー、部屋に対応します。
//SUBSTATEはFundamentalPanelの実装に合わせ、int型として定義しており、この実装は2025/01/30時点での仕様に準拠しています。
//画像ファイルはviewパッケージの下に位置していることが仮定されています。
//このクラスはスレッドセーフではありません。
public class FloorOnePanel extends FundamentalPanel{

    //private final int SUBSTATE_INITIAL = 1 FundamentalPanelから継承している
    private final int SUBSTATE_1FROOM = 2; //部屋
    private final int SUBSTATE_1FBOX = 3; //1F爆弾
    
    public FloorOnePanel(GameController controller){
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
        switch(subState){
            //初期状態の時、後ろへ進むボタンはロビーへ戻るボタンで、前へ進むボタンは部屋へ移るボタンである。
            case SUBSTATE_INITIAL:
                imageLabel = createJLabelWithImage("image-OneFloor-Lobby.jpg", 0, 0, 600, 800);
                buttonToFront = createDirectionalButton(SUBSTATE_1FROOM, "LOBBYFRONT");
                buttonToRear = createStateChangeDirectionalButton(State.STATE_LOBBY, "LOBBYREAR");
                //現在、ボックスがないため爆弾で代用している
                JButton boxButton = createButtonWithImage(State.STATE_MINIGAME, "image-Bumb.jpg", 0, 490, 150, 100);
                addButton(boxButton, LAYER_UTIL_SECOND);
                this.addLabel(imageLabel, LAYER_FIGURE_FIRST);
            break;

        }
        System.out.println("Succenssfully change substate to " + subState);
        updateButton();
        this.revalidate();
        this.repaint();
    }

    
    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        switch(command){
            case "STATE_LOBBY":
                controller.transition(State.STATE_LOBBY);
            break;
            default:
                changeSubState(Integer.parseInt(command));
            break;

        }
    }
}
