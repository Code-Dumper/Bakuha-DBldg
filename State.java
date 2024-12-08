enum Event{
    STATE_TITLE,
    STATE_LOBBY,
    STATE_END,
    STATE_1F,
    STATE_2F,
    STATE_3F,
    STATE_4F,
    STATE_GAMEOVER
}
/*
 * @brief
 * 状態を管理するクラス
 */
class State{
    protected String title;
    public State(String title) { this.title = title;}
    public State(){ this.title = "タイトルが指定されていません"; }

    public State transitionTo(Event event){
        return StateFactory.createState(event);
    }

    public String getTitle(){ return title;}
    public Event getState(){
        return StateFactory.getState(this);
    };
}

//タイトル画面の状態を管理するクラス。
class TitleState extends State{

    public TitleState(){
        super("タイトル画面");
    }

    @Override
    public State transitionTo(Event event){
        if(event == Event.STATE_LOBBY || event == Event.STATE_END || event ==Event.STATE_GAMEOVER){
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
        if(event == Event.STATE_TITLE || event == Event.STATE_GAMEOVER){
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
        if(event == Event.STATE_1F || event == Event.STATE_2F || event == Event.STATE_3F || event == Event.STATE_4F || event == Event.STATE_GAMEOVER){
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
}
class FloorTwoState extends State{
}

class FloorThreeState extends State{
}

class FloorFourState extends State{
}

