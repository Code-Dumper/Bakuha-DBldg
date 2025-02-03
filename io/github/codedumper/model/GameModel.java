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
    
    private State currentState; //現在のゲーム状態
    private GameTimer timer; //ゲームで共通のタイマー
    private double remainTime; //残り時間
    private BombManager bombManager; //爆弾管理
    private StateMachine stateMachine; //状態管理
    private GraphManager graphManager; //ミニゲームのグラフ管理
    private int key[]; //ユーザの入力した爆弾解除のコード

    //残り時間をINITIAL_TIME(3600s)、初期状態をタイトル状態
    public GameModel(){
        currentState = State.STATE_TITLE;
        remainTime = INITIAL_TIME;
        timer = new GameTimer(INITIAL_TIME, this, this);
        bombManager = new BombManager(this);
        stateMachine = new StateMachine();
        graphManager = new GraphManager(this);
        key = new int[5];
        timer.start();
    }

    //残り時間の通知
    public final synchronized double getTimeRemaining(){
        return remainTime;
    }
    //時間変化時の更新処理
    @Override
    public final void onTimeChange(double newTime){
        remainTime = newTime;
        notifyTimeChange();
    }
    //時間切れの時の処理
    @Override
    public final void onTimeOut(){
        setCurrentState(State.STATE_GAMEOVER);
    }

    //状態遷移
    public final synchronized void setCurrentState(State event){
        if(event == State.STATE_END){
            System.exit(0);
        }
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
        int keyIndex;
        switch(currentState){
            case STATE_1F:
                keyIndex = 1;
                break;
            case STATE_2F:
                keyIndex = 2;
                break;
            case STATE_3F:
                keyIndex = 3;
                break;
            case STATE_4F:
                keyIndex = 4;
                break;
            default:
                keyIndex = 0;
        }
        //4桁以上入力されていたら以降の入力は破棄する
        if(key[keyIndex] >= 9000) return;
        //何らかの要因で予定していた状態以外から入力が入ったら破棄する
        if(keyIndex == 0) return;
        key[keyIndex] = key[keyIndex] * 10 + input;
        
    }

    public final void resetCode(){
        int keyIndex;
        switch(currentState){
            case STATE_1F:
                keyIndex = 1;
                break;
            case STATE_2F:
                keyIndex = 2;
                break;
            case STATE_3F:
                keyIndex = 3;
                break;
            case STATE_4F:
                keyIndex = 4;
                break;
            default:
                keyIndex = 0;
        }
        key[keyIndex] = 0;
    }

    public final int getCurrentCode(){
        int keyIndex;
        switch(currentState){
            case STATE_1F:
                keyIndex = 1;
                break;
            case STATE_2F:
                keyIndex = 2;
                break;
            case STATE_3F:
                keyIndex = 3;
                break;
            case STATE_4F:
                keyIndex = 4;
                break;
            default:
                keyIndex = 0;
        }
        return key[keyIndex];
    }

    //eに合わせて爆弾を取得し、その解除を試みる
    public final boolean disarmBomb(){
        switch(currentState){
            case STATE_1F: 
                return bombManager.disarmBomb(1, key[1]);
            case STATE_2F: 
                return bombManager.disarmBomb(2, key[2]);
            case STATE_3F:
                return bombManager.disarmBomb(3,key[3]);
            case STATE_4F:
                return bombManager.disarmBomb(4,key[4]);
            default:
                return false;
            }
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
                notifyObservers("STATE_CHANGE");
            });
    }
}

