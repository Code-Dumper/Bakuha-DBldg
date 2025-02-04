package io.github.codedumper.model.bomb;

import io.github.codedumper.model.GameModel;

//暗号入力を鍵とした爆弾解除戦略クラス
public class CodeDisarmStrategy{
    private GameModel model;
    private int key;
    public CodeDisarmStrategy(GameModel model, int correctCode){
        this.model = model;
        this.key = correctCode;
    }

    public boolean canDisarm(Bomb bomb, int input){
        return key == input;
    }

    public int getCorrectCode(){
        return this.key;
    }
}
