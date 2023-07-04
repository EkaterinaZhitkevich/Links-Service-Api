package com.example.links_converter_api;

import com.example.links_converter_api.controller.LinkController;
import com.example.links_converter_api.dto.LongLinkRequestDto;
import com.example.links_converter_api.dto.ShortLinkResponseDto;
import com.example.links_converter_api.exception.NullLongLinkException;
import com.example.links_converter_api.facade.LinkFacade;
import com.example.links_converter_api.handler.LinkServiceExceptionHandler;
import com.example.links_converter_api.service.LinkService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LinkController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LinksControllerTest {

    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    LinkFacade linkFacade;

    @BeforeEach
    public void beforeEach(){
        mockMvc = getMockMvc();
    }

    // only camelCase or _
    // TODO @ parametrized tests
    @Test
    void whenValidInput_thenReturnsCreated() throws Exception {
        LongLinkRequestDto longLinkRequestDto =
                new LongLinkRequestDto("https://sysout.ru/testirovanie-kontrollerov-s-pomoshhyu-mockmvc/");
        ShortLinkResponseDto shortLinkResponseDto = new ShortLinkResponseDto("cJUwF");
        when(linkFacade.createShortLink(any(LongLinkRequestDto.class))).thenReturn(shortLinkResponseDto);
        MvcResult resultActions = mockMvc.perform(post("/short_link")
                        .content(objectMapper.writeValueAsString(longLinkRequestDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isCreated()).andReturn();
        ShortLinkResponseDto shortLinkResponseDto1 = objectMapper
                .readValue(resultActions.getResponse().getContentAsString(),
                        ShortLinkResponseDto.class);
        Assertions.assertEquals(shortLinkResponseDto.getShortLink(), shortLinkResponseDto1.getShortLink());
    }

    @Test
    void whenNullInput_thenReturnNotFound() throws Exception {
        LongLinkRequestDto linkRequestDto = new LongLinkRequestDto(null);
        ShortLinkResponseDto shortLinkResponseDto = new ShortLinkResponseDto(null);
//        when(linkFacade.createShortLink(linkRequestDto)).thenThrow(new NullLongLinkException("Not Found"));
        mockMvc.perform(post("/short_link")
                        .content(objectMapper.writeValueAsString(linkRequestDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isBadRequest());
    }

    private MockMvc getMockMvc(){
        return MockMvcBuilders
                .standaloneSetup(new LinkController(linkFacade))
                .setControllerAdvice(new LinkServiceExceptionHandler())
                .build();
    }

}
