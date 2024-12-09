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
    
    private State currentState; //現在のゲーム状態
    private GameTimer timer; //ゲームで共通のタイマー
    private int remainTime; //残り時間
    private BombManager bombManager;

    /**
     * コンストラクタ。初期状態としてタイトル画面を設定。残り時間を初期化し、タイマーを開始する。
     */
    public GameModel(){
        currentState = new TitleState();
        remainTime = INITIAL_TIME;
        timer = new GameTimer(INITIAL_TIME, this);
        bombManager = new BombManager();
        //仮で爆弾を追加しておく。
        //TODO 爆弾の仕様が明確に決まったらその実現のためにコードを書く
        bombManager.addBomb(new Bomb(new CodeDisarmStrategy("299792458 m")));
        bombManager.addBomb(new Bomb(new CodeDisarmStrategy("赤方偏移")));
        bombManager.addBomb(new Bomb(new CodeDisarmStrategy("粘性率と非熱")));
        bombManager.addBomb(new Bomb(new CodeDisarmStrategy("再履修")));

        timer.start();
    }

    public void defuseBomb( int index, String inputcode ){
        boolean success = bombManager.defuseBomb(index, inputcode);
        if(success){
            //TODO ここのメッセージもパネルで表示する?
            System.out.println("解除に成功しました");
        }else{
            System.out.println("解除に失敗しました");
        }

        if(bombManager.areAllBombsDefused()){
            setCurrentState(Event.STATE_GAMECLEAR);
            System.out.println("全ての爆弾が解除されました");
        }
    }
    /**
     * 現在の状態を取得
     * @return モデルが管理している現在のStateオブジェクト
     */

    public synchronized State getCurrentState(){
        return currentState;
    }

    /**
     * 現在の残り時間を取得
     * @return 残り時間(秒)
     */
    public synchronized int getTimeRemaining(){
        return remainTime;
    }

    /**
     * 指定されたイベントに応じて状態を変更する。
     * 状態が変更された場合、Observerに通知を行う。
     * @param event 状態遷移を指示するイベント
     */
    public synchronized void setCurrentState(Event event){
        State nextState = currentState.transitionTo(event);
        if(nextState != currentState){
            this.currentState = nextState;
            //状態が変わったことをObserverに知らせる。
            notifyStateChange();
        }
    }

    @Override
    public void onTimeChange(int newTime){
        remainTime = newTime;
        notifyTimeChange();
    }

    @Override
    public void onTimeOut(){
        setCurrentState(Event.STATE_GAMEOVER);
    }

    /**
     * Observerに時間変化を伝えるためのメソッド。
     */
    private void notifyTimeChange(){
        //ラムダ式を使っている
        SwingUtilities.invokeLater(() -> {
            setChanged();
            notifyObservers("TIME_CHANGE");
        });
    }

    /**
     * Observerに状態変化を伝えるためのメソッド。
     */
    private void notifyStateChange(){
        SwingUtilities.invokeLater(() ->{
                setChanged(); 
                notifyObservers("STATE_CHANGE");
            });
    }
}




/**
 * ゲーム内で使用する汎用タイマークラス。
 * 残り時間を管理し、時間変更やタイムアウト時にリスナーへ通知する。
 */
class GameTimer {
    private int remainTime; // 残り時間
    private Timer timer; // タイマー
    private TimerListener listener; // リスナー（通知先）

    /**
     * タイマーリスナーのインターフェース。
     * 時間変更やタイムアウト時に通知を行う。
     */
    interface TimerListener {
        void onTimeChange(int newTime); // 残り時間が変更されたとき
        void onTimeOut(); // 残り時間が0になったとき
    }

    /**
     * コンストラクタ。
     * @param initialTime 初期残り時間（秒）
     * @param listener    タイマーイベントを受け取るリスナー
     */
    public GameTimer(int initialTime, TimerListener listener) {
        this.remainTime = initialTime;
        this.listener = listener;
    }

    /**
     * タイマーを開始する。
     */
    public void start() {
        stop(); // 既存のタイマーが動いていれば停止
        timer = new Timer(true); // デーモンスレッド
        timer.scheduleAtFixedRate(
            new TimerTask() {
                @Override
                public void run() {
                    if (remainTime > 0) {
                        remainTime--;
                        if (listener != null) {
                            listener.onTimeChange(remainTime); // 残り時間を通知
                        }
                    } else {
                        stop(); // タイマー停止
                        if (listener != null) {
                            listener.onTimeOut(); // タイムアウト通知
                        }
                    }
                }
            },
            0, 1000 // 初回遅延0ms、1秒ごとに実行
        );
    }

    /**
     * タイマーを停止する。
     */
    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }

    /**
     * 残り時間を取得する。
     * @return 現在の残り時間（秒）
     */
    public int getRemainingTime() {
        return remainTime;
    }

    /**
     * 残り時間を設定する（リセットや強制変更用）。
     * @param time 新しい残り時間（秒）
     */
    public void setRemainingTime(int time) {
        this.remainTime = time;
    }
}