package io.github.codedumper.model.bomb;

import io.github.codedumper.model.GameModel;

//パズルを解除の鍵とした爆弾解除戦略クラス
public class PuzzleDisarmsStrategy implements DisarmStrategy{

    private final GameModel model;
    public PuzzleDisarmsStrategy(GameModel model){
        this.model = model;
    }

    public boolean canDisarm(Bomb bomb, Object input){
        return model.isPuzzleSolved();
    }
}
