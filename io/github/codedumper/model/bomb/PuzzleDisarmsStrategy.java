package io.github.codedumper.model.bomb;

import io.github.codedumper.model.GameModel;

//パズルを解除の鍵とした爆弾解除戦略クラス
public class PuzzleDisarmsStrategy extends BaseDisarmStrategy{
    
    public PuzzleDisarmsStrategy(GameModel model, Boolean key){
        super(model, key);
    }

    public boolean canDisarm(Bomb bomb, Object input){
        return model.isPuzzleSolved();
    }
}
