package com.example.links_converter_api.controller;

import com.example.links_converter_api.dto.LongLinkRequestDto;
import com.example.links_converter_api.dto.ShortLinkResponseDto;
import com.example.links_converter_api.facade.LinkFacade;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@Validated
public class LinkController {

    private final LinkFacade linkFacade;

    @PostMapping("/short_link")
    public ResponseEntity<ShortLinkResponseDto> createShortLink(@RequestBody @Valid LongLinkRequestDto longLink){
        return ResponseEntity.status(HttpStatus.CREATED).body(linkFacade.createShortLink(longLink));
    }

    @GetMapping("/{short_link}")
    public ResponseEntity<Void> returnLongLink(@PathVariable("short_link") @Valid @Size String shortLink,
                                                             HttpServletResponse response) throws IOException {
        response.sendRedirect(linkFacade.returnLongLink(shortLink));
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }
}
