package com.mokhovav.boardgames.board;

import com.mokhovav.boardgames.board.Direction;
import com.mokhovav.boardgames.board.Field;
import com.mokhovav.boardgames.board.Properties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class LinkData {
    private String from;
    private String to;
    private Direction direction;
    private Properties properties;

    public LinkData() {
    }

    public LinkData(String from, String to, Direction direction, Properties properties) {
        this.from = from;
        this.to = to;
        this.direction = direction;
        this.properties = properties;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
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
