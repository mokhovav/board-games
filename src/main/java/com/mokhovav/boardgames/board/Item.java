package com.mokhovav.boardgames.board;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class Item extends Element{

    public Item(String name, Properties properties) {
        super(name, properties);
    }

    public Item() {
    }

    public Item(String name) {
        super(name);
    }
}
