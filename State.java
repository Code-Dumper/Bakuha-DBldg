enum Event{
    STATE_TITLE,
    STATE_LOBBY,
    STATE_END,
    STATE_1F,
    STATE_2F,
    STATE_3F,
    STATE_4F,
    STATE_GAMEOVER
    //TODO あとはここに追加していく。ここも切り出したいのが本音。
}

/*
 * @brief
 * 状態を管理する抽象クラス
 */
abstract class State {
    protected String title;
    //コンストラクタ
    public State(String title) { this.title = title;}
    public State(){ this.title = "タイトルが指定されていません"; }
    //共通メソッド
    // TitleState sとすると、 s.transitionTo(STATE_LOBBY)のようにすることで、sの状態をLobbyStateに移行できる。
    public State transitionTo(Event event){
        return StateFactory.createState(event);
    }
    public String getTitle(){ return title;}
    public abstract Event getState();
}

//タイトル画面の状態を管理するクラス。
class TitleScreenState extends State{

    public TitleScreenState(){
        super("タイトル画面");
    }

    @Override
    public Event getState(){
        return Event.STATE_TITLE;
    }
    @Override
    public State transitionTo(Event event){
        if(event == Event.STATE_LOBBY || event == Event.STATE_END){
            return super.transitionTo(event);
        }else{
            return this;
        }
    }


}

//終了状態を意味する状態。
class EndState extends State{

    public EndState(){
        super("ゲーム終了");
    }
    @Override
    public State transitionTo(Event event){
        if(event == Event.STATE_TITLE){
            return super.transitionTo(event);
        }else{
            return this;
        }
    }
    @Override
    public Event getState(){
        return Event.STATE_END;
    }
}

//ロビーの画面を意味する状態。
class LobbyState extends State{

    public LobbyState(){
        super("ロビー");
    }
    @Override
    public State transitionTo(Event event){
        if(event == Event.STATE_1F || event == Event.STATE_2F || event == Event.STATE_3F || event == Event.STATE_4F){
            return super.transitionTo(event);
        }else{
            return this;
        }
    }

    @Override
    public Event getState(){
        return Event.STATE_LOBBY;
    }
}


///////////////////////////////
/// 未実装ぞーん
///////////////////////////////

class FloorOneState extends State{

    @Override
    public Event getState() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getView'");
    }

}
class FloorTwoState extends State{

    @Override
    public Event getState() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getView'");
    }
}

class FloorThreeState extends State{

    @Override
    public Event getState() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getView'");
    }    
}

class FloorFourState extends State{
    @Override
    public Event getState() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getView'");
    }
}

class GameOverState extends State{

    @Override
    public Event getState() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getView'");
    }    
}

/**
 * 状態遷移だけを担うクラス。
 */
class StateFactory{
    public static State createState(Event event){
        switch(event){
            case STATE_TITLE: return new TitleScreenState();
            case STATE_LOBBY: return new LobbyState();
            case STATE_END: return new EndState();
            case STATE_1F: return new FloorOneState();
            case STATE_2F: return new FloorTwoState();
            case STATE_3F: return new FloorThreeState();
            case STATE_4F: return new FloorFourState();
            case STATE_GAMEOVER: return new GameOverState();
            default: throw new UnsupportedOperationException("Unimplemented State");
        }
    }
}