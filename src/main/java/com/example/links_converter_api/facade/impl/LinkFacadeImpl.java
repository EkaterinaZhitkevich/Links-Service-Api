package com.example.links_converter_api.facade.impl;

import com.example.links_converter_api.dto.LongLinkRequestDto;
import com.example.links_converter_api.dto.ShortLinkResponseDto;
import com.example.links_converter_api.exception.NullLongLinkException;
import com.example.links_converter_api.facade.LinkFacade;
import com.example.links_converter_api.service.LinkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class LinkFacadeImpl implements LinkFacade {

    private final LinkService linkService;

    @Override
    public ShortLinkResponseDto createShortLink(LongLinkRequestDto longLink) {
        log.info("Start generate short link for long link {}", longLink);
        String strLongLink = longLink.getLongLink();
        String shortLink = linkService.createShortLinkForLongLink(strLongLink);
        log.info("Short link successfully generated {}", shortLink);
        return ShortLinkResponseDto.builder().shortLink(shortLink).build();
    }


    @Override
    public String returnLongLink(String shortLink) {
        log.info("Long link search is started");
        String longLink = linkService.returnLongLinkForShortLink(shortLink);
        log.info("Long link is successfully found");
        return longLink;

    }
}
