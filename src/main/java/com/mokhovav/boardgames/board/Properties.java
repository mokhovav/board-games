package com.mokhovav.boardgames.board;

import com.mokhovav.base.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;

@Component
@Scope(value = "prototype")
public class Properties extends HashMap<String, String> {

    @Override
    public String put(String key, String value) {
        return Optional.ofNullable(key)
                .filter(s -> !s.isBlank())
                .filter(s -> !super.containsKey(s))
                .map(s -> super.put(s, value))
                .orElse(null);
    }
}
