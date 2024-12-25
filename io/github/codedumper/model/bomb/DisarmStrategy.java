package io.github.codedumper.model.bomb;

public interface DisarmStrategy{
    boolean disarm(Bomb bomb, Object input);
}