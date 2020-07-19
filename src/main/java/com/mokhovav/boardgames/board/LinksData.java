package com.mokhovav.boardgames.board;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
@Scope(value = "prototype")
public class LinksData extends ArrayList<LinkData> {
    @Override
    public boolean add(LinkData linkData) {
        return Optional.ofNullable(linkData)
                .filter(l -> !l.getFrom().isBlank())
                .filter(l -> !l.getTo().isBlank())
                .filter(l -> !l.getFrom().equals(l.getTo()))
                .filter(l -> l.getDirection() != null)
                .map(super::add)
                .orElse(false);
    }
}
