package com.mokhovav.boardgames.board;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class Field extends Element{

    public Field() {
    }

    public Field(String name) {
        super(name);
    }
}
