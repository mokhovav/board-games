package com.mokhovav.boardgames.boardServices;

import com.mokhovav.boardgames.board.Fields;
import com.mokhovav.boardgames.board.Links;
import org.springframework.stereotype.Component;

@Component
public interface LinkService {
    LinksData linksToLinksData(Links links);
    Links linksDataToLinks(LinksData linksData, Fields fields);
}
