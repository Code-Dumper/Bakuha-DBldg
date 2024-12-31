package io.github.codedumper.model;
import java.util.*;


public class StateMachine {
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
        // 状態「STATE_1F」の遷移リスト
        transitions.put(Event.STATE_1F,
                        Arrays.asList(  Event.STATE_LOBBY,
                                        Event.STATE_1F_ROOM,
                                        Event.STATE_1F_BOMB));
        // 状態「STATE_2F」の遷移リスト
        transitions.put(Event.STATE_2F,
                        Arrays.asList(  Event.STATE_LOBBY,
                                        Event.STATE_2F_ROOM,
                                        Event.STATE_2F_BOMB));
        // 状態「STATE_3F」の遷移リスト
        transitions.put(Event.STATE_3F,
                        Arrays.asList(  Event.STATE_LOBBY,
                                        Event.STATE_3F_ROOM,
                                        Event.STATE_3F_BOMB));
        // 状態「STATE_4F」の遷移リスト
        transitions.put(Event.STATE_4F,
                        Arrays.asList(  Event.STATE_LOBBY,
                                        Event.STATE_4F_ROOM,
                                        Event.STATE_4F_BOMB));
        // 状態「STATE_1F_BOMB」
        transitions.put(Event.STATE_1F_BOMB,
                        Arrays.asList(  Event.STATE_1F));
        // 状態「STATE_2F_BOMB」
        transitions.put(Event.STATE_2F_BOMB,
                        Arrays.asList(  Event.STATE_2F));
        // 状態「STATE_3F_BOMB」
        transitions.put(Event.STATE_3F_BOMB,
                        Arrays.asList(  Event.STATE_3F));
        // 状態「STATE_4F_BOMB」
        transitions.put(Event.STATE_2F_BOMB,
                        Arrays.asList(  Event.STATE_4F));  
                                     
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
