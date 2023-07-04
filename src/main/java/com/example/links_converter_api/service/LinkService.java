package com.example.links_converter_api.service;

import com.example.links_converter_api.entity.Link;

public interface LinkService {

    void saveLink(Link link);
    String createShortLinkForLongLink(String longLink);

    String getShortLinkForLongLink(String longLink);

    void setShortLinkForLongLink(Link link);

    String returnLongLinkForShortLink(String shortLink);
}
