package com.mokhovav.boardgames.boardServices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mokhovav.boardgames.board.Element;
import com.mokhovav.boardgames.board.Elements;
import org.springframework.stereotype.Service;

@Service
public class BaseElementService implements ElementService<Element> {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public Element findByName(String name, Elements<? extends Element> elements) {
        return elements.stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);

    }

    @Override
    public String elementToJSon(Element element) {
        try {
            return mapper.writeValueAsString(element);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object jSonToElement(String jSon, Class className) {
        try {
            return mapper.readValue(jSon, className);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
