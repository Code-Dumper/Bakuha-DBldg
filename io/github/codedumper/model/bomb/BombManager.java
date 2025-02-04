package io.github.codedumper.model.bomb;

import java.util.ArrayList;
import java.util.List;

import io.github.codedumper.model.GameModel;

/*
 * 爆弾解除と爆弾情報を担うクラス。
 */

public class BombManager {

    GameModel model;
    List<Bomb> bombs;

    public BombManager(GameModel model){
        this.model = model;
        bombs = new ArrayList<Bomb>();
        addBomb(model, new CodeDisarmStrategy(model, model.dataManager.getCode(1)));
        addBomb(model, new CodeDisarmStrategy(model, model.dataManager.getCode(2)));
        addBomb(model, new CodeDisarmStrategy(model, model.dataManager.getCode(3)));
        addBomb(model, new CodeDisarmStrategy(model, model.dataManager.getCode(4)));
    }

    public void addBomb(GameModel model, BaseDisarmStrategy disarmStrategy){
        bombs.add(new Bomb(model, disarmStrategy));
    }
    
    public boolean disarmBomb(int index, Object input){
        if(index < 0 || bombs.size() <= index){
            throw new IllegalArgumentException();
        }
        Bomb bombToDisarm = bombs.get(index);
        return bombToDisarm.disarm(input);
    }

    public boolean areAllBombsDisarmed(){
        for(Bomb bomb: bombs){
            if(! bomb.isDisarmed()){
                return false;
            }
        }
        return true;
    }
}