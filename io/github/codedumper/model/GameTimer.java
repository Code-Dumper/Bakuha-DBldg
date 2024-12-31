package io.github.codedumper.model;
import java.util.Timer;
import java.util.TimerTask;
/**
 * ゲーム内で使用する汎用タイマークラス。
 * 残り時間を管理し、時間変更やタイムアウト時にリスナーへ通知します。
 * <p>
 * 主な機能:
 * <ul>
 *   <li>残り時間のカウントダウン</li>
 *   <li>残り時間変更時の通知</li>
 *   <li>時間切れ時の通知</li>
 *   <li>残り時間の取得と設定</li>
 * </ul>
 * </p>
 */
public class GameTimer {
    private double remainTime; // 残り時間
    private Timer timer; // タイマー
    private TimerListener listener; // リスナー（通知先）

    /**
     * タイマーリスナーのインターフェース。
     * 時間変更やタイムアウト時に通知を行います。
     */
    public interface TimerListener {
        /**
         * 残り時間が変更されたときに呼び出されます。
         * @param newTime 新しい残り時間（秒）
         */
        void onTimeChange(double newTime);

        /**
         * 残り時間が0になったときに呼び出されます。
         */
        void onTimeOut();
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
     * タイマーを開始します。
     * 残り時間が1秒ごとに減少し、変更が通知されます。
     * 残り時間が0になると、タイムアウト通知が行われます。
     */
    public void start() {
        stop(); // 既存のタイマーが動いていれば停止
        timer = new Timer(true); // デーモンスレッド
        timer.scheduleAtFixedRate(
            new TimerTask() {
                @Override
                public void run() {
                    if (remainTime > 0) {
                        remainTime = remainTime - 0.087;
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
            0, 87 // 初回遅延0ms、0.085秒ごとに実行
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
}
