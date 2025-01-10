package io.github.codedumper.model;

public interface IBombManager {
    /**
     * @brief eventに対応する爆弾に対して入力を行う。
     * @param event 爆弾に対応したイベント
     * @param input 入力するコード
     */
    void inputCode(Event event, int input);

    /**
     * @param event 爆弾に対応したイベント
     */
    void resetCode(Event event);

    /**
     * @param event 解除したい爆弾のイベント名
     * @return 爆弾解除の結果のboolean
     */
    boolean disarmBomb(Event event);

    /**
     * 
     * @return 爆弾が全て解除されたかどうかのbooleanフラグ
     */
    boolean areAllBombsDisarmed();
}
