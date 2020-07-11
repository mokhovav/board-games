package com.mokhovav.boardgames.board;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class Board {
    private Fields fields;
    private Items items;
    private Links links;
    private Dices dices;
}
