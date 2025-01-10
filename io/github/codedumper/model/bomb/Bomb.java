package io.github.codedumper.model.bomb;

import io.github.codedumper.model.GameModel;



public class Bomb{
    private BaseDisarmStrategy disarmStrategy;
    private boolean disarmed;

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

    public boolean isDisarmed() {
        return disarmed;
    }
}
