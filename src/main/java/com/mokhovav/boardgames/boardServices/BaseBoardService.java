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
        try {
            return mapper.writeValueAsString(boardToBoardData(board));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Board jSonToBoard(String jSon) {
        return Optional.ofNullable(jSonToBoardData(jSon))
                .map(d -> {
                    Board board = new Board();
                    board.setFields(d.getFields());
                    board.setItems(d.getItems());
                    board.setDices(d.getDices());
                    board.setLinks(linkService.linksDataToLinks(d.getLinksData(), board.getFields()));
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
            return new BoardData(
                    board.getFields(),
                    board.getItems(),
                    linkService.linksToLinksData(board.getLinks()),
                    board.getDices()
            );

//        return null;
    }
}
