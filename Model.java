import java.util.Observable;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;


/**
 * 現状態currentState, 残り時間remainTimeを管理するModel。
 * モデルに対する状態設定はsetCurrentStateにより行われ、その際引数としてEventを受け取る。
 * eg.) gameModel.setCurrentState(STATE_TITLE) により、現状態がTitleに設定され、その状態をPanelにnotifyする。
 */

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

//
@SuppressWarnings("deprecation")
class GameModel extends Observable implements ModelInterface{
    private static final int INITIAL_TIME = 3600;
    
    private State currentState;
    private Timer timer;
    private int remainTime;

    //コンストラクタ
    public GameModel(){
        currentState = new TitleState();
        remainTime = INITIAL_TIME;
        startGlobalTimer();
    }

    //現在の状態を取得
    @Override
    public synchronized State getCurrentState(){
        return currentState;
    }

    //残り時間を取得
    @Override
    public synchronized int getTimeRemaining(){
        return remainTime;
    }

    //状態を変更
    @Override
    public synchronized void setCurrentState(Event event){
        State nextState = currentState.transitionTo(event);
        if(nextState != currentState){
            this.currentState = nextState;
            //状態が変わったことをObserverに知らせる。
            notifyStateChange();
        }
    }

    //タイマーを開始
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
    
    @Override
    public synchronized void stopTimer(){
        if(timer != null){
            timer.cancel();
            timer.purge();
        }
    }

    private void notifyTimeChange(){
        SwingUtilities.invokeLater(() -> {
            setChanged();
            notifyObservers("TIME_CHANGE");
        });
    }

    private void notifyStateChange(){
        SwingUtilities.invokeLater(() ->{
                setChanged(); 
                notifyObservers("STATE_CHANGE");
            });
    }
}




