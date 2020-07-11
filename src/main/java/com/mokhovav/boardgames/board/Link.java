package com.mokhovav.boardgames.board;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Scope(value = "prototype")
public class Link {
    private Field from;
    private Field to;
    private Direction direction;

    public Field getFrom() {
        return from;
    }

    public void setFrom(Field from) {
        this.from = from;
    }

    public Field getTo() {
        return to;
    }

    public void setTo(Field to) {
        this.to = to;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
