package io.github.codedumper.model;
import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
    private double remainTime; // 残り時間
    private Timer timer; // タイマー
    private boolean isActive;
    private ITimerListener listener; // リスナー（通知先)
    private GameModel model;

    /**
     * コンストラクタ。
     * @param initialTime 初期残り時間（秒）
     * @param listener    タイマーイベントを受け取るリスナー
     */
    public GameTimer(int initialTime, ITimerListener listener, GameModel model) {
        this.remainTime = initialTime;
        this.listener = listener;
        this.model = model;
        this.isActive = false;
    }

    /**
     * タイマーを開始します。
     * 残り時間が減少し、変更が通知されます。
     * 残り時間が0になると、タイムアウト通知が行われます。
     */
    public void start() {
        stop(); // 既存のタイマーが動いていれば停止
        this.isActive = true;
        System.out.println("Timer started.");
        timer = new Timer(true); // デーモンスレッド
        timer.scheduleAtFixedRate(
            new TimerTask() {
                @Override
                public void run() {
                    if (remainTime > 0) {
                        if(!model.getCurrentState().equals(State.STATE_TITLE)){
                            remainTime = remainTime - 0.1;
                        }
                        if (listener != null) {

                            listener.onTimeChange(remainTime); // 残り時間を通知
                        }
                    } else {
                        //負になる可能性があるので0に戻しておく
                        remainTime = 0;
                        System.out.println("Timer stopped.");
                        stop(); // タイマー停止
                        isActive = false;
                        if (listener != null) {
                            listener.onTimeOut(); // タイムアウト通知
                        }
                    }
                }
            },
            0, 10 // 初回遅延0ms、period * 1/1000 秒ごとに実行
        );
    }

    /**
     * タイマーを停止します。
     */
    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }

    /**
     * 現在の残り時間を取得します。
     * @return 現在の残り時間（秒）
     */
    public double getRemainingTime() {
        return remainTime;
    }

    /**
     * 残り時間を設定します（リセットや強制変更用）。
     * @param time 新しい残り時間（秒）
     */
    public void setRemainingTime(double time) {
        this.remainTime = time;
    }

    public boolean isStopped(){
        return !isActive;
    }
}
