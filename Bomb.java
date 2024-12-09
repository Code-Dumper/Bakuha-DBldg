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

    //コンストラクタ
    public CodeDisarmStrategy(String correctCode){
        this.correctCode = correctCode;
    }
    //inputCodeがcorrectCodeと等しいかどうかで解除を決める
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
    
    //コンストラクタ
    public Bomb(DisarmStrategy disarmStrategy){
        this.isDefused = false;
        this.disarmStrategy = disarmStrategy;
    }

    //爆弾を解除できるか判定し、解除できるならisDefusedをtrueにする
    public void defuse(String input){
        if(disarmStrategy instanceof CodeDisarmStrategy){
            if(disarmStrategy.tryToDisarm(input)){
                isDefused = true;
            }
        }
    }

    //爆弾が解除されてるかを教える
    public boolean isDefused(){
        return isDefused;
    }
}

/*
 * 爆弾解除と爆弾情報を担うマネージャクラス。
 */
class BombManager{
    private ArrayList<Bomb> bombs;

    //コンストラクタ
    public BombManager(){
        bombs = new ArrayList<Bomb>();
    }

    //爆弾をマネージャに追加する
    public void addBomb(Bomb bomb){
        bombs.add(bomb);
    }

    //indexの爆弾がinputCodeで解除できるか検証し、
    //解除できるなら解除しtrueを返す、解除できないなら解除せずfalse。
    public boolean defuseBomb(int index, String inputCode){
        if(index < 0 || index >= bombs.size()){
            throw new IllegalArgumentException("Invalid");
        }else{
            Bomb bomb = bombs.get(index);
            bomb.defuse(inputCode);
            return bomb.isDefused();
        }
    }

    //マネージャの管理する全ての爆弾が解除されてるかを返す
    public boolean areAllBombsDefused(){
        for(Bomb bomb: bombs){
            if(!bomb.isDefused()){
                return false;
            }
        }
        return true;
    }
    //マネージャの管理している爆弾のListを返す
    public List<Bomb> getBombs(){
        return bombs;
    }
}