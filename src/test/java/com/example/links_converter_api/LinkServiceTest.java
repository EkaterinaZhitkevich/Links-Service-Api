package com.example.links_converter_api;

import com.example.links_converter_api.entity.Link;
import com.example.links_converter_api.exception.NullLongLinkException;
import com.example.links_converter_api.repository.LinkJpaRepository;
import com.example.links_converter_api.service.impl.LinkServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LinkServiceTest {

    @InjectMocks
    LinkServiceImpl linkService;

    @Mock
    LinkJpaRepository linkJpaRepository;

    Link link;

    @BeforeEach
    public void createTestLink() {
        link = Link.builder().longLink("https://sysout.ru/testirovanie-kontrollerov-s-pomoshhyu-mockmvc/")
                .shortLink("cJUwF").build();
    }

    @Test
    public void saveLinkTest() {
        when(linkJpaRepository.save(any(Link.class))).thenReturn(link);
        linkService.saveLink(link);
        verify(linkJpaRepository, times(1)).save(any(Link.class));

    }

    @Test
    public void testGetShortLinkForLongLink() {
        String longLink = link.getLongLink();
        String expectedShortLink = "cJUwF";
        when(linkJpaRepository.findByLongLink(longLink)).thenReturn(link);
        String actualShortLink = linkService.getShortLinkForLongLink(longLink);
        Assertions.assertEquals(expectedShortLink, actualShortLink);
    }

    @Test
    @Disabled
    public void testSetShortLinkForLongLink() {
        String longLink = link.getLongLink();
        String shortLink = link.getShortLink();
        Link mockLink = Mockito.spy(Link.class);
        when(linkService.createShortLinkForLongLink(mockLink.getLongLink())).thenReturn(shortLink);
        when(mockLink.getLongLink()).thenReturn(longLink);
        when(linkJpaRepository.save(mockLink)).thenReturn(mockLink);
        linkService.setShortLinkForLongLink(mockLink);
        verify(mockLink, times(1)).setShortLink(shortLink);
    }

//    public void setShortLinkForLongLink(Link link) {
//        String longLink = link.getLongLink();
//        link.setShortLink(createShortLinkForLongLink(longLink));
//    }

    @Test
    public void testForTestLinkService(){
        Link spyLink = Mockito.spy(Link.class);
        linkService.forTest(spyLink);
        verify(spyLink, times(1)).getShortLink();
    }

    @Test
    public void testReturnLongLinkForShortLink_forValidLink(){
        String actualLongLink = link.getLongLink();
        String expectedLongLink = "https://sysout.ru/testirovanie-kontrollerov-s-pomoshhyu-mockmvc/";
        Assertions.assertEquals(expectedLongLink, actualLongLink);
    }

    @Test
    public void testReturnLongLinkForShortLink_forNullLongLink(){
        Assertions.assertThrows(NullLongLinkException.class,
                () -> linkService.returnLongLinkForShortLink(null));
    }


}
