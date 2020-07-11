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

    public Board() {
    }

    public Board(Fields fields, Items items, Links links, Dices dices) {
        this.fields = fields;
        this.items = items;
        this.links = links;
        this.dices = dices;
    }

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Dices getDices() {
        return dices;
    }

    public void setDices(Dices dices) {
        this.dices = dices;
    }
}
