package io.github.codedumper.model.bomb;

import java.util.ArrayList;
import java.util.List;

import io.github.codedumper.model.GameModel;
import io.github.codedumper.model.State;

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

    public int getBombCodeOf(State currentState){
        int keyIndex;
        switch(currentState){
            case STATE_1F:  keyIndex = 1;   break;
            case STATE_2F:  keyIndex = 2;   break;
            case STATE_3F:  keyIndex = 3;   break;
            case STATE_4F:  keyIndex = 4;   break;
            default:        keyIndex = 0;
        }
        return bombs.get(keyIndex).getCode();
    }

    public void inputCodeTo(State currentState, int value){
        int keyIndex;
        switch(currentState){
            case STATE_1F:  keyIndex = 1;   break;
            case STATE_2F:  keyIndex = 2;   break;
            case STATE_3F:  keyIndex = 3;   break;
            case STATE_4F:  keyIndex = 4;   break;
            default:        keyIndex = 0;
        }
        //何らかの要因で予定していた状態以外から入力が入ったら破棄する
        if(keyIndex == 0) return;
        bombs.get(keyIndex).inputCode(value);
    }

    public boolean disarmBomb(State currentState){
        int keyIndex;
        switch(currentState){
            case STATE_1F: keyIndex = 1; break;
            case STATE_2F: keyIndex = 2; break;
            case STATE_3F: keyIndex = 3; break;
            case STATE_4F: keyIndex = 4; break;
            default:       keyIndex = 0; break;
        }
        if(keyIndex == 0){return false;}
        return bombs.get(keyIndex).disarm(getBombCodeOf(currentState));
    }

    public boolean isDisarmedBombOf(State currentState){
        int keyIndex;
        switch(currentState){
            case STATE_1F: keyIndex = 1; break;
            case STATE_2F: keyIndex = 2; break;
            case STATE_3F: keyIndex = 3; break;
            case STATE_4F: keyIndex = 4; break;
            default:       keyIndex = 0; break;
        }
        return bombs.get(keyIndex).isDisarmed();
    }

    public void resetCode(State currentState){
        int keyIndex;
        switch(currentState){
            case STATE_1F: keyIndex = 1; break;
            case STATE_2F: keyIndex = 2; break;
            case STATE_3F: keyIndex = 3; break;
            case STATE_4F: keyIndex = 4; break;
            default:       keyIndex = 0; break;
        }
        bombs.get(keyIndex).resetCode();
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