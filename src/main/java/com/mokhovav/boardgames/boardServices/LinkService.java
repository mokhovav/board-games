package com.mokhovav.boardgames.boardServices;

import com.mokhovav.boardgames.board.Fields;
import com.mokhovav.boardgames.board.Link;
import com.mokhovav.boardgames.board.Links;
import org.springframework.stereotype.Component;

@Component
public interface LinkService {
    LinkData linkToLinkData(Link link);
    String linkToJSon(Link link);

    LinksData linksToLinksData(Links links);
    String linksToJSon(Links links);

    LinkData jSonToLinkData(String jSon);
    Link jSonToLink(String jSon, Fields fields);

    LinksData jSonToLinksData(String jSon);
    Links jSonToLinks(String jSon, Fields fields);
}
