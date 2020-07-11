package com.mokhovav.boardgames.board;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
@Scope(value = "prototype")
public class Links extends ArrayList<Link> {
    @Override
    public boolean add(Link link) {
        return Optional.ofNullable(link)
                .filter(l -> l.getFrom() != null)
                .filter(l -> l.getTo() != null)
                .filter(l -> l.getFrom() != l.getTo())
                .filter(l -> l.getDirection() != null)
                .map(super::add)
                .orElse(false);
    }
}
