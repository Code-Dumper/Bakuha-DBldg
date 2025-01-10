package io.github.codedumper.model.bomb;

import io.github.codedumper.model.GameModel;

//爆弾解除の方法が持っておくべき基本の性質を定義したクラス。

public abstract class BaseDisarmStrategy{
    protected GameModel model;
    protected Object key;
    
    public BaseDisarmStrategy(GameModel model, Object key){
        this.model = model;
        this.key = key;
    }
    public abstract boolean canDisarm(Bomb bomb, Object input);
}