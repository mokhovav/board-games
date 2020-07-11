package com.mokhovav.boardgames.boardServices;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class BoardData {
    private String fields;
    private String items;
    private String links;
    private String dices;

    public BoardData() {
    }

    public BoardData(String fields, String items, String links, String dices) {
        this.fields = fields;
        this.items = items;
        this.links = links;
        this.dices = dices;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public String getDices() {
        return dices;
    }

    public void setDices(String dices) {
        this.dices = dices;
    }
}
