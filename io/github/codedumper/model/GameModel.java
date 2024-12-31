package io.github.codedumper.model;

import javax.swing.*;

import io.github.codedumper.model.bomb.BombManager;
import io.github.codedumper.model.planarity.*;

import java.util.*;
import java.awt.Point;




/**
 * GameModelは、MVCアーキテクチャにおけるModelの役割を担うクラスです。
 * ゲームの現在の状態を{@link StateMachine}を用いたイベント遷移で管理し、
 * ゲーム内のタイマーや爆弾の状態を統括します。
 * <p>
 * このクラスの主な機能は以下の通りです：
 * <ul>
 *   <li>ゲームの残り時間の管理および取得</li>
 *   <li>ゲーム状態の遷移および確認</li>
 *   <li>爆弾解除の処理</li>
 * </ul>
 * <p>
 * このクラスが生成されると、以下の初期化が行われます：
 * <ul>
 *   <li>{@link BombManager}および{@link StateMachine}のインスタンス生成</li>
 *   <li>初期状態としてタイトル画面を設定</li>
 *   <li>残り時間を3600秒（1時間）に設定</li>
 *   <li>{@link GameTimer}インスタンスの生成およびタイマーの開始</li>
 *   <li>{@link GraphManager}インスタンスの生成</li>
 * </ul>
 * <p>
 * GameModelは、時間や状態の変化を監視するObserverに通知する機能を提供します。
 */


@SuppressWarnings("deprecation")
public class GameModel extends Observable implements GameTimer.TimerListener{
    private static final int INITIAL_TIME = 3600;
    
    private Event currentState; //現在のゲーム状態
    private GameTimer timer; //ゲームで共通のタイマー
    private double remainTime; //残り時間
    private BombManager bombManager; //爆弾管理
    private StateMachine stateMachine; //状態管理
    private GraphManager graphManager; //ミニゲームのグラフ管理
    private int key[]; //ユーザの入力した爆弾解除のコード

    //残り時間をINITIAL_TIME(3600s)、初期状態をタイトル状態
    public GameModel(){
        currentState = Event.STATE_TITLE;
        remainTime = INITIAL_TIME;
        timer = new GameTimer(INITIAL_TIME, this);
        bombManager = new BombManager(this);
        stateMachine = new StateMachine();
        graphManager = new GraphManager(this);
        key = new int[5];
        timer.start();
    }

    //残り時間の通知
    public synchronized double getTimeRemaining(){
        return remainTime;
    }
    //時間変化時の更新処理
    @Override
    public void onTimeChange(double newTime){
        remainTime = newTime;
        notifyTimeChange();
    }
    //時間切れの時の処理
    @Override
    public void onTimeOut(){
        setCurrentState(Event.STATE_GAMEOVER);
    }

    //状態遷移
    public synchronized void setCurrentState(Event event){
        if(event == Event.STATE_END){
            System.exit(0);
        }
        currentState = stateMachine.getNextState(currentState, event);
        notifyStateChange();
    }

    //現状態の取得
     public synchronized Event getCurrentState(){
        return currentState;
    }
    //グラフ操作系の処理
    public List<Point> getNodes(){
        return graphManager.getNodes();
    }

    public void moveNode(int index, Point newPosition){
        graphManager.moveNode(index, newPosition);
    }
    
    public boolean isPuzzleSolved(){
        return graphManager.isGameSolved();
    }

    public void recreatePuzzle(){
        graphManager.recreatePuzzle();
    }

    public List<Edge> getEdges(){
        return graphManager.getEdges();
    }

    public List<Edge> getIntersectingEdges(){
        return graphManager.getIntersectingEdges();
    }
    //爆弾のコード入力用の処理
    public void inputCode(int input){
        int keyIndex;
        switch(currentState){
            case STATE_1F_BOMB:
                keyIndex = 1;
                break;
            case STATE_2F_BOMB:
                keyIndex = 2;
                break;
            case STATE_3F_BOMB:
                keyIndex = 3;
                break;
            case STATE_4F_BOMB:
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

    public void resetCode(){
        int keyIndex;
        switch(currentState){
            case STATE_1F_BOMB:
                keyIndex = 1;
                break;
            case STATE_2F_BOMB:
                keyIndex = 2;
                break;
            case STATE_3F_BOMB:
                keyIndex = 3;
                break;
            case STATE_4F_BOMB:
                keyIndex = 4;
                break;
            default:
                keyIndex = 0;
        }
        key[keyIndex] = 0;
    }

    public int getcurrentCode(){
        int keyIndex;
        switch(currentState){
            case STATE_1F_BOMB:
                keyIndex = 1;
                break;
            case STATE_2F_BOMB:
                keyIndex = 2;
                break;
            case STATE_3F_BOMB:
                keyIndex = 3;
                break;
            case STATE_4F_BOMB:
                keyIndex = 4;
                break;
            default:
                keyIndex = 0;
        }
        return key[keyIndex];
    }

    //eに合わせて爆弾を取得し、その解除を試みる
    public void disarmBomb(Event e){
        switch(e){
            case STATE_1F_BOMB: 
                bombManager.disarmBomb(1, key[1]);
                break;
            case STATE_2F_BOMB: 
                bombManager.disarmBomb(2, key[2]);
                break;
            case STATE_3F_BOMB:
                bombManager.disarmBomb(3,key[3]);
                break;
            case STATE_4F_BOMB:
                bombManager.disarmBomb(4,key[4]);
                break;
            default:

        }
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
    private void notifyStateChange(){
        SwingUtilities.invokeLater(() ->{
                setChanged(); 
                notifyObservers("STATE_CHANGE");
            });
    }
}

