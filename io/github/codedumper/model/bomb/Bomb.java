package io.github.codedumper.model.bomb;

import io.github.codedumper.model.GameModel;



public class Bomb{
    private CodeDisarmStrategy disarmStrategy;
    private boolean disarmed;
    private int codeInputted;

    public Bomb(GameModel model, CodeDisarmStrategy disarmStrategy) {
        this.disarmStrategy = disarmStrategy;
        this.disarmed = false;
    }

    public boolean disarm(int input) {
        if (disarmStrategy.canDisarm(this, input)) {
            disarmed = true;
            return true;
        } else {
            return false;
        }
    }

    public void inputCode(int input){
        if(this.codeInputted >= 1000){
            return;
        }else{
            this.codeInputted = codeInputted * 10 + input;
        }
    }
    public int getCurrentCode(){
        return this.codeInputted;
    }

    public void resetCurrentCode(){
        this.codeInputted = 0;
    }

    public int getProperCode(){
        return this.disarmStrategy.getCorrectCode();
    }

    public boolean isDisarmed() {
        return disarmed;
    }
}
