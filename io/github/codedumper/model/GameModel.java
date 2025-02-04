package io.github.codedumper.model;

import javax.swing.*;

import io.github.codedumper.model.bomb.BombManager;
import io.github.codedumper.model.planarity.*;

import java.util.*;
import java.awt.Point;

//IGameModelを具体的に実装するクラス
//爆弾の数は4つ
//状態についてはEvent.javaに記載
//解除コードはConstant.javaに記載
@SuppressWarnings("deprecation")
public class GameModel extends Observable implements IGameModel{
    private static final int INITIAL_TIME = 3600;
    
    private       State currentState; //現在のゲーム状態
    private final GameTimer timer; //ゲームで共通のタイマー
    private final BombManager bombManager; //爆弾管理
    private final StateMachine stateMachine; //状態管理
    private final GraphManager graphManager; //ミニゲームのグラフ管理
    public  final DataManager dataManager;

    //残り時間をINITIAL_TIME(3600s)、初期状態をタイトル状態
    public GameModel(){
        currentState = State.STATE_TITLE;
        timer = new GameTimer(INITIAL_TIME, this, this);
        dataManager = new DataManager(60);
        bombManager = new BombManager(this);
        stateMachine = new StateMachine();
        graphManager = new GraphManager(this);
        timer.start();
    }

    //残り時間の通知
    public final synchronized double getTimeRemaining(){
        return timer.getRemainingTime();
    }
    //時間変化時の更新処理
    @Override
    public final void onTimeChange(double newTime){
        this.notifyTimeChange();
    }
    //時間切れの時の処理
    @Override
    public final void onTimeOut(){
        this.setCurrentState(State.STATE_GAMEOVER);
    }

    //状態遷移
    public final synchronized void setCurrentState(State event){
        //ゲームを終了する
        if(event == State.STATE_END){ System.exit(0);}
        currentState = stateMachine.getNextState(currentState, event);
        notifyStateChange();
    }

    //現状態の取得
     public final synchronized State getCurrentState(){
        return currentState;
    }
    //グラフ操作系の処理
    public final List<Point> getNodes(){
        return graphManager.getNodes();
    }

    public final void moveNode(int index, Point newPosition){
        graphManager.moveNode(index, newPosition);
    }
    
    public final boolean isPuzzleSolved(){
        return graphManager.isGameSolved();
    }

    public final void recreatePuzzle(){
        graphManager.recreatePuzzle();
    }

    public final List<Edge> getEdges(){
        return graphManager.getEdges();
    }

    public final List<Edge> getIntersectingEdges(){
        return graphManager.getIntersectingEdges();
    }
    //爆弾のコード入力用の処理
    public final void inputCode(int input){
        bombManager.inputCodeTo(currentState, input);
    }

    public final void resetCurrentCode(){
        bombManager.resetCurrentCode(currentState);
    }

    public final int getCurrentCode(){
        return this.bombManager.getBombCodeOf(currentState);
    }

    //eに合わせて爆弾を取得し、その解除を試みる
    public final boolean disarmBomb(){
        return this.bombManager.disarmBomb(currentState);
    }

    public final boolean areAllBombsDisarmed(){
        return bombManager.areAllBombsDisarmed();
    }
    //通知系の処理
    //Observerへ時間変化の通知
    private void notifyTimeChange(){
        //ラムダ式を使っている
        SwingUtilities.invokeLater(() -> {
            setChanged();
            notifyObservers("TIME_CHANGE");
        });
    }

    //Observerへ状態変化の通知
    private final void notifyStateChange(){
        SwingUtilities.invokeLater(() ->{
                setChanged(); 
                //タイトルに戻ったら時間を回復させる(この時、timer.stop()がすでに呼ばれていて、タイマーは動かない。)
                if(currentState.equals(State.STATE_TITLE)){
                    timer.setRemainingTime(INITIAL_TIME);
                }
                notifyObservers("STATE_CHANGE");
            });
    }
}

