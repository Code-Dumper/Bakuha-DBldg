enum Event{
    STATE_TITLE,
    STATE_LOBBY,
    STATE_END,
    STATE_1F,
    STATE_2F,
    STATE_3F,
    STATE_4F,
    STATE_GAMEOVER,
    STATE_1F_ROOM,
    STATE_2F_ROOM,
    STATE_3F_ROOM,
    STATE_4F_ROOM
}
/*
 * @brief
 * 状態を管理するクラス。各状態は遷移のためのメソッドtransitionTo、
 * String型のタイトル獲得用のメソッドgetTitle、状態取得用のgetStateを持つ。
 */
class State{
    protected String title;
    //コンストラクタ
    public State(String title) { this.title = title;}
    public State(){ this.title = "Untitled"; }
    //状態を遷移させる
    public State transitionTo(Event event){
        return StateFactory.createState(event);
    }

    public String getTitle(){ return title;}
    public Event getState(){
        return StateFactory.getState(this);
    }
}

//タイトル画面の状態を管理するクラス。
class TitleState extends State{

    public TitleState(){
        super("タイトル画面");
    }

    @Override
    public State transitionTo(Event event){
        if( event == Event.STATE_LOBBY || 
            event == Event.STATE_END || 
            event ==Event.STATE_GAMEOVER){
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
        if( event == Event.STATE_TITLE || 
            event == Event.STATE_GAMEOVER){
            return super.transitionTo(event);
        }else{
            return this;
        }
    }
}

//ロビーの画面を意味する状態。
class LobbyState extends State{

    public LobbyState(){
        super("ロビー");
    }

    @Override
    public State transitionTo(Event event){
        if( event == Event.STATE_1F || 
            event == Event.STATE_2F || 
            event == Event.STATE_3F || 
            event == Event.STATE_4F || 
            event == Event.STATE_GAMEOVER){
            return super.transitionTo(event);
        }else{
            return this;
        }
    }
}

/**
 * ゲームオーバーの状態を表す状態
 */
class GameOverState extends State{
    public GameOverState(){
        super("ゲームオーバー");
    }
    @Override
    public State transitionTo(Event event){
        return this;
    }
}

class FloorOneState extends State{
    public FloorOneState(){
        super("1F");
    }
    @Override
    public State transitionTo(Event event){
        if( event == Event.STATE_LOBBY ||
            event == Event.STATE_GAMEOVER || 
            event == Event.STATE_1F_ROOM){
            return super.transitionTo(event);
        }else{
            return this;
        }
    }
}
class FloorTwoState extends State{
    public FloorTwoState(){
        super("2F");
    }
    @Override
    public State transitionTo(Event event){
        if( event == Event.STATE_LOBBY || 
            event == Event.STATE_GAMEOVER || 
            event == Event.STATE_2F_ROOM){
            return super.transitionTo(event);
        }else{
            return this;
        }
    }
}

class FloorThreeState extends State{
    public FloorThreeState(){
        super("3F");
    }
    @Override
    public State transitionTo(Event event){
        if( event == Event.STATE_LOBBY || 
            event == Event.STATE_GAMEOVER || 
            event == Event.STATE_3F_ROOM){
            return super.transitionTo(event);
        }else{
            return this;
        }
    }
}

class FloorFourState extends State{
    public FloorFourState(){
        super("4F");
    }
    @Override
    public State transitionTo(Event event){
        if( event == Event.STATE_LOBBY || 
            event == Event.STATE_GAMEOVER || 
            event == Event.STATE_4F_ROOM){
            return super.transitionTo(event);
        }else{
            return this;
        }
    }
}

class OneFloorRoomState extends State{}
class TwoFloorRoomState extends State{}
class ThreeFloorRoomState extends State{}
class FourFloorRoomState extends State{}


