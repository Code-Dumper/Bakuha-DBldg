package io.github.codedumper.model;

/*
 * 爆弾解除の解除戦略を定義するインターフェース
 */
interface DisarmStrategy {
    /**
     * 爆弾を解除する試みを行う。
     * @return 解除に成功した場合はtrue、失敗した場合はfalse。
     */
    boolean tryToDisarm(String s);
}

/**
 * 爆弾の性質を定義するクラス。
 * 爆弾は解除されたかどうか、どのように解除されるかの二つの情報を持つ。
 */
public class Bomb {
    private boolean isDefused;
    private DisarmStrategy disarmStrategy;
    
    //コンストラクタ
    public Bomb(CodeDisarmStrategy disarmStrategy){
        this.isDefused = false;
        this.disarmStrategy = disarmStrategy;
    }

    //爆弾を解除できるか判定し、解除できるならisDefusedをtrueにする
    public void defuse(String input){
        if(disarmStrategy.tryToDisarm(input)){
            isDefused = true;
        }
    }

    //爆弾が解除されてるかを教える
    public boolean isDefused(){
        return isDefused;
    }
}

