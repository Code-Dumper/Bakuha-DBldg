package io.github.codedumper.model.bomb;

//暗号入力を鍵とした爆弾解除戦略クラス
public class CodeDisarmStrategy implements DisarmStrategy{
    private final String correctCode;


    public CodeDisarmStrategy(String correctCode){
        this.correctCode = correctCode;
    }

    @Override
    public boolean canDisarm(Bomb bomb, Object input){
        if(!(input instanceof String)) return false;
        return correctCode.equals(input);
    }
}
