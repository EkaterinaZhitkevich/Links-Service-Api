package com.example.links_converter_api.service.impl;

import com.example.links_converter_api.entity.Link;
import com.example.links_converter_api.exception.NullLongLinkException;
import com.example.links_converter_api.repository.LinkJpaRepository;
import com.example.links_converter_api.service.LinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Random;

@Service
@Slf4j
public class LinkServiceImpl implements LinkService {

    private LinkJpaRepository linkJpaRepository;

    @Autowired
    public void setLinkJpaRepository(LinkJpaRepository linkJpaRepository) {
        this.linkJpaRepository = linkJpaRepository;
    }

    @Override
    @Transactional
    public String createShortLinkForLongLink(String longLink) {
        String shortLink = createRandomString();
        Link link = Link.builder().longLink(longLink).shortLink(shortLink).build();
        linkJpaRepository.save(link);
        return shortLink;
    }

    @Override
    public String getShortLinkForLongLink(String longLink) {
        Link link = linkJpaRepository.findByLongLink(longLink);
        return link.getShortLink();
    }

    @Override
    public String returnLongLinkForShortLink(String shortLink) {
        Link link = linkJpaRepository.findByShortLink(shortLink);
        if (link == null){
            throw new NullLongLinkException("Long link not found for his short link");
        }
        return link.getLongLink();
    }

    @Override
    public void saveLink(Link link) {
        linkJpaRepository.save(link);
    }

    @Override
    public void setShortLinkForLongLink(Link link) {
        String longLink = link.getLongLink();
        link.setShortLink(createShortLinkForLongLink(longLink));
    }

    public void forTest(Link link){
        link.getShortLink();
    }

    private static String createRandomString() {
        Random random = new SecureRandom();
        int shortLinkLength = 5;
        char[] text = new char[shortLinkLength];
        String charSequence = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
        for (int i = 0; i < text.length; i++) {
            text[i] = charSequence.charAt(random.nextInt(charSequence.length() - 1));
        }
        String shortLink = new String(text);
        return shortLink;
    }

}
