package io.github.codedumper.model;
import java.util.*;


public class StateMachine {
    // 各状態に対して、遷移先が記録されたMap.
    //Mapはキーと値の2⻆ 要素からなる複数データを格納するものである。
    //すなわち、Map<キーの型名, 値の型名> 変数名にて宣言される。
    private final Map<State, List<State>> transitions;

    public StateMachine() {
        transitions = new HashMap<>();
        setUpTransitions();
    }

    private void setUpTransitions() {
        // 状態「STATE_TITLE」の遷移リスト
        transitions.put(State.STATE_TITLE,
                        Arrays.asList(  State.STATE_LOBBY, 
                                        State.STATE_END, 
                                        State.STATE_GAMEOVER));

        // 状態「STATE_LOBBY」の遷移リスト
        transitions.put(State.STATE_LOBBY, 
                        Arrays.asList(  State.STATE_1F, 
                                        State.STATE_2F, 
                                        State.STATE_3F, 
                                        State.STATE_4F, 
                                        State.STATE_GAMEOVER));
        // 状態「STATE_1F」の遷移リスト
        transitions.put(State.STATE_1F,
                        Arrays.asList(  State.STATE_LOBBY,
                                        State.STATE_1F_ROOM));
        // 状態「STATE_2F」の遷移リスト
        transitions.put(State.STATE_2F,
                        Arrays.asList(  State.STATE_LOBBY,
                                        State.STATE_2F_ROOM));
        // 状態「STATE_3F」の遷移リスト
        transitions.put(State.STATE_3F,
                        Arrays.asList(  State.STATE_LOBBY,
                                        State.STATE_3F_ROOM));
        // 状態「STATE_4F」の遷移リスト
        transitions.put(State.STATE_4F,
                        Arrays.asList(  State.STATE_LOBBY,
                                        State.STATE_4F_ROOM));
        // 状態「STATE_GAMEOVER」の遷移リスト
        transitions.put(State.STATE_GAMEOVER, 
                        Arrays.asList(  State.STATE_TITLE, 
                                        State.STATE_GAMEOVER));
    }

    public State getNextState(State currentState, State event) {
        List<State> possibleTransitions = transitions.getOrDefault(currentState, Collections.emptyList());
        if (possibleTransitions.contains(event)) {
            return event;
        } else {
            return currentState; // 状態の変更がない場合、現在の状態を返す
        }
    }
}
