package io.github.codedumper.controller;

import java.awt.event.MouseEvent;

import io.github.codedumper.view.GameViewPanel;
import io.github.codedumper.model.*;

public interface IGameController {
    //GameViewPanelをControllerに登録するメソッド。
    public void addView(GameViewPanel view);

    //状態遷移のメソッド。stateをモデルに送信し、モデル側でtransition処理を行うためのメソッド。
    public void transition(State state);

    //マウスリスナ関連のメソッド。
    public void mousePressed(MouseEvent e);
    public void mouseDragged(MouseEvent e);
    public void mouseReleased(MouseEvent e);

    //整数一桁のinputをモデルに対して送るメソッド。
    public void inputCodeToCurrentStateBomb(int input);

    //現在の状態をモデル側で取得し、その状態に対応する爆弾のコードを初期化するメソッド。
    public void resetCodeOfCurrentStateBomb();

    //現在の状態の爆弾に対して、モデルが保持している爆弾に入力されたコードを返すメソッド。
    //例えば、モデル側が1Fの爆弾に対してユーザーが以前入力した12という値を保持している時、このメソッドは12を整数値で返す。
    public int getCodeOfCurrentStateBomb();

    //現在の状態の爆弾をモデル側が管理しているコードにより解除するメソッド。
    public boolean disarmCurrentStateBombByCurrentCode();
    //爆弾が全て解除されたかを返すメソッド。
    public boolean areAllBombsDisarmed();

    //モデルの管理する状態に対応する爆弾が解除されているかどうかを表すbooleanを返すメソッド。
    public boolean isDisarmedCurrentFloorBomb();
}
