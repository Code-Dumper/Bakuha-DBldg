package io.github.codedumper.model;

import java.util.ArrayList;
import java.util.List;

/*
 * 爆弾解除と爆弾情報を担うマネージャクラス。
 */
public class BombManager {

    private ArrayList<Bomb> bombs;

    //コンストラクタ
    public BombManager(){
        bombs = new ArrayList<Bomb>();
        addBomb(new Bomb(new CodeDisarmStrategy("299792458 m")));
        addBomb(new Bomb(new CodeDisarmStrategy("赤方偏移")));
        addBomb(new Bomb(new CodeDisarmStrategy("粘性率と非熱")));
        addBomb(new Bomb(new CodeDisarmStrategy("再履修")));
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
