package io.github.codedumper.model.bomb;

import io.github.codedumper.model.GameModel;

//暗号入力を鍵とした爆弾解除戦略クラス
public class CodeDisarmStrategy extends BaseDisarmStrategy{

    public CodeDisarmStrategy(GameModel model, String correctCode){
        super(model, correctCode);
    }

    @Override
    public boolean canDisarm(Bomb bomb, Object input){
        if(!(input instanceof String)) return false;
        return key.equals(input);
    }
}
