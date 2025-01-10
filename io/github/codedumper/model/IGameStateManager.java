package io.github.codedumper.model;

//ゲームの状態を管理するという性質を表すインターフェース
public interface IGameStateManager {
    /**
     * @param event セットする状態
     */
    void setCurrentState(Event event);
    
    /**
     * @return 現在の状態を表すEvent型
     */
    Event getCurrentState();
}
