package io.github.codedumper.model.bomb;

import io.github.codedumper.model.GameModel;

//暗号入力を鍵とした爆弾解除戦略クラス
public class CodeDisarmStrategy extends BaseDisarmStrategy{
    int correctCode;
    public CodeDisarmStrategy(GameModel model, int correctCode){
        super(model, correctCode);
    }

    @Override
    public boolean canDisarm(Bomb bomb, Object input){
        if(!(input instanceof Integer)) return false;
        return key == Integer.valueOf((int)input);
    }
}
