package com.mokhovav.boardgames.board;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
@Scope(value = "prototype")
public class Elements<T extends Element> extends ArrayList<T> {
    @Override
    public boolean add(T element) {
        return Optional.ofNullable(element)
                .filter(e -> e.getName() != null)
                .filter(e -> !e.getName().isBlank())
                .map(e -> this.stream().anyMatch(arrayField -> arrayField.getName().equals(e.getName())) ? null : e)
                .map(super::add)
                .orElse(false);
    }
}
