import java.util.Timer;
import java.util.TimerTask;

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