import java.util.Observable;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;



 //モデルのインターフェース。必要かは知らない。
interface ModelInterface{
    //現状態の取得
    State getCurrentState();
    //残り時間の取得
    int getTimeRemaining();
    //現状態の設定
    void setCurrentState(Event event);
    //タイマーのスタート
    void startGlobalTimer();
    //タイマーのストップ
    void stopTimer();
}


/**
 * ゲームのモデルを管理するクラス。
 * - 現在の状態 (currentState)
 * - 残り時間(remainTIme)
 * を保持し、タイマーの制御や状態遷移を行う。
 * 
 * このモデルはObserverパターンを利用していて、状態や時間の変更が発生した際にObserverへ通知する。
 * 通知は時間変更と残り時間の変更でメッセージが分かれており、Panelに反映する際に明確なコーディングが可能。
 * TODO タイマーが煩雑なので、そこだけクラス分けできるといいかもしれない
 */

@SuppressWarnings("deprecation")
class GameModel extends Observable implements ModelInterface{
    private static final int INITIAL_TIME = 3600;
    
    private State currentState; //現在のゲーム状態
    private Timer timer; //ゲームで共通のタイマー
    private int remainTime; //残り時間

    /**
     * コンストラクタ。初期状態としてタイトル画面を設定。残り時間を初期化し、タイマーを開始する。
     */
    public GameModel(){
        currentState = new TitleState();
        remainTime = INITIAL_TIME;
        startGlobalTimer();
    }

    /**
     * 現在の状態を取得
     * @return モデルが管理している現在のStateオブジェクト
     */

    @Override
    public synchronized State getCurrentState(){
        return currentState;
    }

    /**
     * 現在の残り時間を取得
     * @return 残り時間(秒)
     */
    @Override
    public synchronized int getTimeRemaining(){
        return remainTime;
    }

    /**
     * 指定されたイベントに応じて状態を変更する。
     * 状態が変更された場合、Observerに通知を行う。
     * @param event 状態遷移を指示するイベント
     */
    @Override
    public synchronized void setCurrentState(Event event){
        State nextState = currentState.transitionTo(event);
        if(nextState != currentState){
            this.currentState = nextState;
            //状態が変わったことをObserverに知らせる。
            notifyStateChange();
        }
    }

    /**
     * グローバルタイマーを開始。
     * 1秒ごとにカウントダウンを行い、時間切れの際はゲームオーバー状態に強制的に遷移させる。
     */
    public synchronized void startGlobalTimer(){
        timer = new Timer(true);//デーモンスレッド
        timer.scheduleAtFixedRate(
            new TimerTask(){
                @Override
                public void run(){
                    synchronized (GameModel.this){
                        //Title画面の時はカウントダウンしない。
                        if (! (currentState instanceof TitleState)){
                            if(remainTime > 0){
                                remainTime--;
                                setChanged();
                                notifyTimeChange();
                            }else{
                                //時間切れでゲームオーバーへ
                                setCurrentState(Event.STATE_GAMEOVER);
                                stopTimer();
                            }
                        }   
                    }

                }
            },
            0,1000); //1秒ごとに実行
    }
    
    /**
     * タイマーを停止させる。
     */
    @Override
    public synchronized void stopTimer(){
        if(timer != null){
            timer.cancel();
            timer.purge();
        }
    }
    /**
     * 時間変更をObserverに通知
     */
    private void notifyTimeChange(){
        SwingUtilities.invokeLater(() -> {
            setChanged();
            notifyObservers("TIME_CHANGE");
        });
    }

    /**
     * 状態変更をObserverに通知
     */
    private void notifyStateChange(){
        SwingUtilities.invokeLater(() ->{
                setChanged(); 
                notifyObservers("STATE_CHANGE");
            });
    }
}




