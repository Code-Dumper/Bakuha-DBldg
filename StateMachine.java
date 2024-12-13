import java.util.*;

enum Event {
    STATE_TITLE,
    STATE_LOBBY,
    STATE_END,
    STATE_1F,
    STATE_2F,
    STATE_3F,
    STATE_4F,
    STATE_GAMEOVER,
    STATE_GAMECLEAR,
    STATE_1F_ROOM,
    STATE_2F_ROOM,
    STATE_3F_ROOM,
    STATE_4F_ROOM,
    STATE_HIDDEN_ROOM
}

class StateMachine {
    // 各状態に対して、遷移先が記録されたMap.
    //Mapはキーと値の2⻆ 要素からなる複数データを格納するものである。
    //すなわち、Map<キーの型名, 値の型名> 変数名にて宣言される。
    private final Map<Event, List<Event>> transitions;

    public StateMachine() {
        transitions = new HashMap<>();
        setUpTransitions();
    }

    private void setUpTransitions() {
        // 状態「STATE_TITLE」の遷移リスト
        transitions.put(Event.STATE_TITLE,
                        Arrays.asList(  Event.STATE_LOBBY, 
                                        Event.STATE_END, 
                                        Event.STATE_GAMEOVER));

        // 状態「STATE_LOBBY」の遷移リスト
        transitions.put(Event.STATE_LOBBY, 
                        Arrays.asList(  Event.STATE_1F, 
                                        Event.STATE_2F, 
                                        Event.STATE_3F, 
                                        Event.STATE_4F, 
                                        Event.STATE_GAMEOVER));

        // 状態「STATE_GAMEOVER」の遷移リスト
        transitions.put(Event.STATE_GAMEOVER, 
                        Arrays.asList(  Event.STATE_TITLE, 
                                        Event.STATE_GAMEOVER));
    }

    public Event getNextState(Event currentState, Event event) {
        List<Event> possibleTransitions = transitions.getOrDefault(currentState, Collections.emptyList());
        if (possibleTransitions.contains(event)) {
            return event;
        } else {
            return currentState; // 状態の変更がない場合、現在の状態を返す
        }
    }
}
