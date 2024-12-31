package io.github.codedumper.model.bomb;

public interface DisarmStrategy{
    boolean canDisarm(Bomb bomb, Object input);
}