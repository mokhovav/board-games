package com.mokhovav.boardgames.board;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public enum Direction {
    FORWARD,
    BACK,
    BOTH,
    BREAK
}
