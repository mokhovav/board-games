package com.mokhovav.boardgames.boardServices;

import com.mokhovav.boardgames.board.Board;
import org.springframework.stereotype.Component;

@Component
public interface BoardService {
    String boardToJSon(Board board);
    Board jSonToBoard(String jSon);
}
