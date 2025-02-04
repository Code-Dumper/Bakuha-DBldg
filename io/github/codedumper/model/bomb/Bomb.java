package io.github.codedumper.model.bomb;

import io.github.codedumper.model.GameModel;



public class Bomb{
    private BaseDisarmStrategy disarmStrategy;
    private boolean disarmed;
    private int codeInputted;

    public Bomb(GameModel model, BaseDisarmStrategy disarmStrategy) {
        this.disarmStrategy = disarmStrategy;
        this.disarmed = false;
    }

    public boolean disarm(Object input) {
        if (disarmStrategy.canDisarm(this, input)) {
            disarmed = true;
            return true;
        } else {
            return false;
        }
    }

    public void inputCode(int input){
        if(this.codeInputted >= 9000){
            return;
        }else{
            this.codeInputted = codeInputted * 10 + input;
        }
    }
    public int getCode(){
        return this.codeInputted;
    }

    public void resetCode(){
        this.codeInputted = 0;
    }

    public boolean isDisarmed() {
        return disarmed;
    }
}
