import javax.swing.*;
import java.util.*;




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
 * </ul>
 * <p>
 * GameModelは、時間や状態の変化を監視するObserverに通知する機能を提供します。
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
        notifyBombResultState(success);
        if(bombManager.areAllBombsDefused()){
            setCurrentState(Event.STATE_GAMECLEAR);
            notifyStateChange();
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
    private void notifyBombResultState(boolean isDefused){
        SwingUtilities.invokeLater(() ->{
            setChanged();
            if(isDefused == true){
                notifyObservers("BOMB_DEFUSED");
            }else{
                notifyObservers("BOMB_IS_NOT_DEFUSED");
            }
        });
    }
}

