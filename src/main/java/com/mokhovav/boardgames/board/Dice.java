package com.mokhovav.boardgames.board;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class Dice extends Element {
    public Dice(String name, Properties properties) {
        super(name, properties);
    }

    public Dice() {
    }

    public Dice(String name) {
        super(name);
    }
}
