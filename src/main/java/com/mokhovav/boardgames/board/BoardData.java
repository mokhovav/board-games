package com.mokhovav.boardgames.board;

import com.mokhovav.boardgames.board.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class BoardData {
    private Fields fields;
    private Items items;
    private LinksData linksData;
    private Dices dices;

    public BoardData() {
    }

    public BoardData(Fields fields, Items items, LinksData linksData, Dices dices) {
        this.fields = fields;
        this.items = items;
        this.linksData = linksData;
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

    public LinksData getLinksData() {
        return linksData;
    }

    public void setLinksData(LinksData linksData) {
        this.linksData = linksData;
    }

    public Dices getDices() {
        return dices;
    }

    public void setDices(Dices dices) {
        this.dices = dices;
    }
}
