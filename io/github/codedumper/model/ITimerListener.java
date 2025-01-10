package io.github.codedumper.model;

//タイマーリスナーとしての性質
public interface ITimerListener {
    void onTimeChange(double newTime);
    void onTimeOut();
    }
