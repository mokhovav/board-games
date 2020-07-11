package com.mokhovav.boardgames.boardServices;

import com.mokhovav.boardgames.board.Element;
import com.mokhovav.boardgames.board.Elements;
import org.springframework.stereotype.Component;

@Component
public interface ElementService<T extends Element> {
    T findByName(String name, Elements<? extends Element> elements);
    String elementToJSon(T element);
    Object jSonToElement(String jSon, Class className);
}
