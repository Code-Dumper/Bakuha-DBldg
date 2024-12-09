import java.util.ArrayList;
import java.util.List;

    //TODO Bombが長いのでできればパッケージ化とかで細かくしたい

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
 * 仮の解除戦略用クラス。Stringとして与えられる解除コードにより爆弾解除
 */
class CodeDisarmStrategy implements DisarmStrategy{
    private String correctCode;

    public CodeDisarmStrategy(String correctCode){
        this.correctCode = correctCode;
    }
    
    public boolean tryToDisarm(String inputCode){
        return inputCode.equals(correctCode);
    }
}

/**
 * 爆弾の性質を定義するクラス。
 * 爆弾は解除されたかどうか、どのように解除されるかの二つの情報を持つ。
 */
class Bomb {
    private boolean isDefused;
    private DisarmStrategy disarmStrategy;

    public Bomb(DisarmStrategy disarmStrategy){
        this.isDefused = false;
        this.disarmStrategy = disarmStrategy;
    }

    public void defuse(String input){
        if(disarmStrategy instanceof CodeDisarmStrategy){
            if(disarmStrategy.tryToDisarm(input)){
                isDefused = true;
            }
        }
    }

    public boolean isDefused(){
        return isDefused;
    }
}

/*
 * 爆弾解除と爆弾情報を担うマネージャクラス。
 */
class BombManager{
    private ArrayList<Bomb> bombs;

    public BombManager(){
        bombs = new ArrayList<Bomb>();
    }

    public void addBomb(Bomb bomb){
        bombs.add(bomb);
    }

    public boolean defuseBomb(int index, String inputCode){
        if(index < 0 || index >= bombs.size()){
            throw new IllegalArgumentException("Invalid");
        }else{
            Bomb bomb = bombs.get(index);
            bomb.defuse(inputCode);
            return bomb.isDefused();
        }
    }

    public boolean areAllBombsDefused(){
        for(Bomb bomb: bombs){
            if(!bomb.isDefused()){
                return false;
            }
        }
        return true;
    }

    public List<Bomb> getBombs(){
        return bombs;
    }
}