package com.mokhovav.boardgames.boardServices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mokhovav.boardgames.board.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BaseBoardService implements BoardService {
    @Autowired ElementService elementService;
    @Autowired LinkService linkService;
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public String boardToJSon(Board board) {
        BoardData data = boardToBoardData(board);
        System.out.println(data.getFields());
        System.out.println(data.getItems());
        System.out.println(data.getLinks());
        System.out.println(data.getDices());

//        try {
//            return mapper.writeValueAsString(boardToBoardData(board));
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
        return null;
    }

    @Override
    public Board jSonToBoard(String jSon) {
        return Optional.ofNullable(jSonToBoardData(jSon))
                .map(d -> {
                    Board board = new Board();
                    board.setFields((Fields) elementService.jSonToElement(d.getFields(), Field.class));
                    board.setItems((Items) elementService.jSonToElement(d.getItems(), Items.class));
                    board.setDices((Dices) elementService.jSonToElement(d.getDices(), Dices.class));
                    board.setLinks(linkService.jSonToLinks(d.getLinks(), board.getFields()));
                    return board;
                })
                .orElse(null);
    }

    private BoardData jSonToBoardData(String jSon) {
        try {
            return mapper.readValue(jSon, BoardData.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private BoardData boardToBoardData(Board board){
        try {
            return new BoardData(
                    mapper.writeValueAsString(board.getFields()),
                    mapper.writeValueAsString(board.getItems()),
                    linkService.linksToJSon(board.getLinks()),
                    mapper.writeValueAsString(board.getDices())
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
