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
    private Properties properties;

    public Link() {
    }

    public Link(Field from, Field to) {
        this.from = from;
        this.to = to;
        this.direction = Direction.BOTH;
    }

    public Link(Field from, Field to, Direction direction) {
        this.from = from;
        this.to = to;
        this.direction = direction;
    }

    public Link(Field from, Field to, Direction direction, Properties properties) {
        this.from = from;
        this.to = to;
        this.direction = direction;
        this.properties = properties;
    }

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

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
