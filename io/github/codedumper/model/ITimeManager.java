package io.github.codedumper.model;

//時間を管理する性質を表すインターフェース
public interface ITimeManager extends ITimerListener{
    /**
     * @return 現在の残り時間のdouble
     */
    double getTimeRemaining();
}
