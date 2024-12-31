package io.github.codedumper.model.bomb;

//暗号入力を鍵とした爆弾解除戦略クラス
public class CodeDisarmStrategy implements DisarmStrategy{
    private final int correctCode;

//暗号入力を鍵とした爆弾解除戦略クラス
public class CodeDisarmStrategy extends BaseDisarmStrategy{

    public CodeDisarmStrategy(int correctCode){
        this.correctCode = correctCode;
    }

    @Override
    public boolean canDisarm(Bomb bomb, Object input){
        if(!(input instanceof Integer)) return false;
        return correctCode == Integer.valueOf((int)input);
    }
}
