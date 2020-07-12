package com.mokhovav.boardgames;

import com.mokhovav.boardgames.board.*;
import com.mokhovav.boardgames.boardServices.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BoardGamesApplicationTests {

    @Autowired
    Properties properties;
    @Autowired
    Fields fields;
    @Autowired
    Links links;
    @Autowired
    Dices dices;
    @Autowired
    Items items;
    @Autowired
    LinkService linkService;
    @Autowired
    ElementService elementService;
    @Autowired
    BoardService boardService;

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
        assertEquals(2, fields.size());
        fields.add(new Field("field2"));
        assertEquals(2, fields.size());
    }

    @Test
    void checkingTheOperationWithLinksAndDirections(){
        links.add(null);
        assertEquals(0, links.size());
        links.add(new Link());
        assertEquals(0, links.size());
        Field field1 = new Field("field1");
        Field field2 = new Field("field2");
        links.add(new Link(null, null, null));
        assertEquals(0, links.size());
        links.add(new Link(field1, null, Direction.FORWARD));
        assertEquals(0, links.size());
        links.add(new Link(null, field2, Direction.BACK));
        assertEquals(0, links.size());
        links.add(new Link(field1, field2, null));
        assertEquals(0, links.size());
        links.add(new Link(field1, field1, Direction.BOTH));
        assertEquals(0, links.size());
        links.add(new Link(field1, field2, Direction.BREAK));
        assertEquals(1, links.size());
    }

    @Test
    void elementServiceAndLinkConversionTest(){
        Field field1 = new Field("field1");
        Field field2 = new Field("field2");
        Field field3 = new Field("field3");
        fields.add(field1);
        fields.add(field2);
        fields.add(field3);
        Link link1 = new Link(field1,field2);
        Link link2 = new Link(field2,field3);
        links.add(link1);
        links.add(link2);

        assertNotNull(elementService.findByName("field1", fields));
        assertNull(elementService.findByName("field", fields));
        assertNull(elementService.findByName("", fields));
        assertNull(elementService.findByName(null, fields));

        Field f = (Field) elementService.jSonToElement(elementService.elementToJSon(field1), Field.class);
        assertEquals(elementService.elementToJSon(field1), elementService.elementToJSon(f));

        LinksData linksData = linkService.linksToLinksData(links);
        Links newLinks = linkService.linksDataToLinks(linksData, fields);

        assertEquals(links.get(0).getFrom(), newLinks.get(0).getFrom());
        assertEquals(links.get(0).getTo(), newLinks.get(0).getTo());
        assertEquals(links.get(0).getDirection(), newLinks.get(0).getDirection());
        assertEquals(links.get(0).getProperties(), newLinks.get(0).getProperties());

        assertEquals(links.get(1).getFrom(), newLinks.get(1).getFrom());
        assertEquals(links.get(1).getTo(), newLinks.get(1).getTo());
        assertEquals(links.get(1).getDirection(), newLinks.get(1).getDirection());
        assertEquals(links.get(1).getProperties(), newLinks.get(1).getProperties());
    }

    @Test
    void boardServiceTest(){
        Field field1 = new Field("field1");
        Field field2 = new Field("field2");
        Field field3 = new Field("field3");
        fields.add(field1);
        fields.add(field2);
        fields.add(field3);
        Link link1 = new Link(field1,field2);
        Link link2 = new Link(field2,field3);
        links.add(link1);
        links.add(link2);

        Item item1 = new Item("item1");
        Item item2 = new Item("item2");
        items.add(item1);
        items.add(item2);

        Dice dice1 = new Dice("dice1");
        Dice dice2 = new Dice("dice2");
        dices.add(dice1);
        dices.add(dice2);

        Board board = new Board(fields, items, links, dices);
        String result = boardService.boardToJSon(board);
        assertEquals(result,boardService.boardToJSon(boardService.jSonToBoard(result)));
    }

}
