package com.example.links_converter_api.facade;

import com.example.links_converter_api.dto.LongLinkRequestDto;
import com.example.links_converter_api.dto.ShortLinkResponseDto;

public interface LinkFacade {

    ShortLinkResponseDto createShortLink(LongLinkRequestDto longLink);


    String returnLongLink(String shortLink);

}
