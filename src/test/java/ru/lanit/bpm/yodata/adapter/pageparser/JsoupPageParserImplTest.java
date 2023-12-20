package ru.lanit.bpm.yodata.adapter.pageparser;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.lanit.bpm.yodata.domain.Page;
import ru.lanit.bpm.yodata.domain.Subscription;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class JsoupPageParserImplTest {

    private PageParser pageParser;

    @BeforeEach
    public void setUp(){
        pageParser = new JsoupPageParserImpl();
    }

    @Test
    void getUrlContent() {
        String result = pageParser.getUrlContent("https://www.cbr.ru", "//div[@class='main-indicator_rate'][2]/div[contains(@class,'mono-num')][2]/text()");
        log.info("Result: {}", result);
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }


}