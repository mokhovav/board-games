package com.mokhovav.boardgames.boardServices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mokhovav.boardgames.board.Field;
import com.mokhovav.boardgames.board.Fields;
import com.mokhovav.boardgames.board.Link;
import com.mokhovav.boardgames.board.Links;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BaseLinkService implements LinkService {
    private ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private ElementService elementService;

    @Override
    public LinkData linkToLinkData(Link link) {
        return Optional.ofNullable(link)
                .filter(l -> l.getFrom() != null)
                .filter(l -> l.getTo() != null)
                .map(l -> new LinkData(
                                l.getFrom().getName(),
                                l.getTo().getName(),
                                l.getDirection(),
                                l.getProperties()))
                .orElse(null);
    }

    @Override
    public String linkToJSon(Link link) {
        return Optional.ofNullable(linkToLinkData(link))
                .map(l -> {
                    try {
                        return mapper.writeValueAsString(l);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .orElse(null);
    }

    @Override
    public LinksData linksToLinksData(Links links) {
        return links.stream().map(this::linkToLinkData).collect(Collectors.toCollection(LinksData::new));
    }

    @Override
    public String linksToJSon(Links links) {
        try {
            return mapper.writeValueAsString(linksToLinksData(links));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public LinkData jSonToLinkData(String jSon) {
        return Optional.ofNullable(jSon)
                .filter(j -> !j.isBlank())
                .map(j -> {
                    try {
                        return mapper.readValue(j, LinkData.class);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .orElse(null);
    }

    @Override
    public Link jSonToLink(String jSon, Fields fields) {
        return linkDataToLink(jSonToLinkData(jSon), fields);
    }

    @Override
    public LinksData jSonToLinksData(String jSon) {
        return Optional.ofNullable(jSon)
                .filter(j -> !j.isBlank())
                .map(j -> {
                    try {
                        return mapper.readValue(j, LinksData.class);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .orElse(null);
    }

    @Override
    public Links jSonToLinks(String jSon, Fields fields) {
        return jSonToLinksData(jSon).stream().map(l -> linkDataToLink(l,fields)).collect(Collectors.toCollection(Links::new));
    }

    private Link linkDataToLink(LinkData data, Fields fields){
        return Optional.ofNullable(data)
                .filter(d -> !d.getFrom().isBlank())
                .filter(d -> !d.getTo().isBlank())
                .map(d -> {
                    Link link = new Link();
                    link.setFrom((Field) elementService.findByName(d.getFrom(),fields));
                    link.setTo((Field) elementService.findByName(d.getTo(),fields));
                    link.setDirection(d.getDirection());
                    link.setProperties(d.getProperties());
                    return link;
                })
                .filter(l -> l.getFrom() != null)
                .filter(l -> l.getTo() != null)
        .orElse(null);
    }


}
