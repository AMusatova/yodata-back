package ru.lanit.bpm.yodata.app.api;

import ru.lanit.bpm.yodata.domain.Page;

import java.util.List;

public interface PageService {
    void addPage(String name, String url, String parsingXPath) throws DuplicateEntityException;
    List<Page> getAllPages();
    void deletePage(Long id) throws NotFoundException;
    void parsePages();
}
