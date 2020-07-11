package com.mokhovav.boardgames;

import com.mokhovav.boardgames.board.Field;
import com.mokhovav.boardgames.board.Fields;
import com.mokhovav.boardgames.board.Properties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BoardGamesApplicationTests {

    @Autowired
    Properties properties;
    @Autowired
    Fields fields;

    @Test
    void contextLoads() {
    }

    @Test
    void checkingTheOperationWithProperties(){
        properties.put("1", "One");
        assertEquals(1, properties.size());
        properties.put("2", "Two");
        assertEquals(2, properties.size());
        properties.put("2", "Two");
        assertEquals(2, properties.size());
        properties.put(" ", "Two");
        assertEquals(2, properties.size());
        properties.put(null, "Two");
        assertEquals(2,properties.size());
    }

    @Test
    void checkingTheOperationWithFields(){
        fields.add(null);
        assertEquals(0, fields.size());
        fields.add(new Field());
        assertEquals(0, fields.size());
        fields.add(new Field("field1"));
        assertEquals(1, fields.size());
        fields.add(new Field("field2"));
        assertEquals(2,fields.size());
        fields.add(new Field("field2"));
        assertEquals(2, fields.size());
    }

}
