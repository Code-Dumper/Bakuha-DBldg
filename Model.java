import javax.swing.*;
import java.util.*;
import java.util.Timer;




/**
 * ゲームのモデルを管理するクラス。
 * - 現在の状態 (currentState)
 * - 残り時間 (remainTime)
 * を保持し、ゲームロジックの中心となる。
 * 
 * 主な機能:
 * 1. 現在の状態(State)の管理
 * 2. 残り時間のカウントダウンと通知
 * 3. 状態変更や時間変更の際にObserverに通知を行う
 * 
 * 備考:
 * - Observerパターンを利用し、ビュー（パネルなど）に変更を通知する。
 */

@SuppressWarnings("deprecation")
class GameModel extends Observable implements GameTimer.TimerListener{
    private static final int INITIAL_TIME = 3600;
    
    private Event currentState; //現在のゲーム状態
    private GameTimer timer; //ゲームで共通のタイマー
    private int remainTime; //残り時間
    private BombManager bombManager;
    private StateMachine stateMachine;

    /**
     * コンストラクタ。初期状態としてタイトル画面を設定。残り時間を初期化し、タイマーを開始する。
     */
    public GameModel(){
        currentState = Event.STATE_TITLE;
        remainTime = INITIAL_TIME;
        timer = new GameTimer(INITIAL_TIME, this);
        bombManager = new BombManager();
        stateMachine = new StateMachine();

        timer.start();
    }

    public void defuseBomb( int index, String inputcode ){
        boolean success = bombManager.defuseBomb(index, inputcode);
        if(success){
            //TODO 解除成功時のViewへの通知
        }else{
            //TODO 解除失敗時のViewへの通知
        }

        if(bombManager.areAllBombsDefused()){
            setCurrentState(Event.STATE_GAMECLEAR);
            //TODO ゲームクリア時のViewへの通知
        }
    }

    //残り時間の通知
    public synchronized int getTimeRemaining(){
        return remainTime;
    }

    //状態遷移
    public synchronized void setCurrentState(Event event){
        Event nextState = stateMachine.getNextState(currentState, event);
        if(nextState != currentState){
            this.currentState = nextState;
            //状態が変わったことをObserverに知らせる。
            notifyStateChange();
        }
    }
    //現状態の取得
     public synchronized Event getCurrentState(){
        return currentState;
    }
    //時間変化時の更新処理
    @Override
    public void onTimeChange(int newTime){
        remainTime = newTime;
        notifyTimeChange();
    }
    //時間切れの時の処理
    @Override
    public void onTimeOut(){
        setCurrentState(Event.STATE_GAMEOVER);
    }

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

