package ru.lanit.bpm.yodata.app.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.lanit.bpm.yodata.app.api.DuplicateEntityException;
import ru.lanit.bpm.yodata.app.api.NotFoundException;
import ru.lanit.bpm.yodata.app.api.PageService;
import ru.lanit.bpm.yodata.app.repo.PageRepository;
import ru.lanit.bpm.yodata.domain.Page;
import ru.lanit.bpm.yodata.fw.YodataBackendApplication;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = YodataBackendApplication.class)
class PageServiceImplTest {

    @Autowired
    PageService pageService;
    @Autowired
    PageRepository pageRepository;

    @Transactional
    @Test
    void addPage_success() throws DuplicateEntityException {
        pageService.addPage("a", "b", "c");
        Page page = pageRepository.findByName("a").get();
        assertEquals("b", page.getUrl());
        assertEquals("c", page.getParsingXPath());
    }

    @Transactional
    @Test
    void addPage_duplicate() {
        assertThrows(DuplicateEntityException.class, ()-> {
            pageService.addPage("a", "b", "c");
            pageService.addPage("a", "b", "c");
        });
    }

    @Test
    void getAllPages_success() {
        System.out.println(pageService.getAllPages());
    }

    @Test
    @Transactional
    void deletePage_success() throws DuplicateEntityException, NotFoundException {
        pageService.addPage("a", "b", "c");
        pageService.deletePage(pageRepository.findByName("a").get().getId());
        assertFalse(pageRepository.findByName("a").isPresent());
    }

    @Test
    @Transactional
    void deletePage_notFound() {
        assertThrows(NotFoundException.class, ()-> {
            pageService.deletePage(99999L);
        });
    }
}