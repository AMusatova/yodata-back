package ru.lanit.bpm.yodata.app.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ru.lanit.bpm.yodata.adapter.pageparser.PageParser;
import ru.lanit.bpm.yodata.app.api.DuplicateEntityException;
import ru.lanit.bpm.yodata.app.api.NotFoundException;
import ru.lanit.bpm.yodata.app.api.PageService;
import ru.lanit.bpm.yodata.app.api.ParsingResultService;
import ru.lanit.bpm.yodata.app.repo.PageRepository;
import ru.lanit.bpm.yodata.app.repo.ParsingResultRepository;
import ru.lanit.bpm.yodata.domain.Page;
import ru.lanit.bpm.yodata.domain.Subscription;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PageServiceImpl implements PageService {

    private final PageParser pageParser;
    private final PageRepository pageRepository;
    private final ParsingResultService parsingResultService;
    @Override
    public void addPage(String name, String url, String parsingXPath) throws DuplicateEntityException {
        if (pageRepository.findByName(name).isPresent()){
            throw new DuplicateEntityException("Page already exists");
        } else {
            pageRepository.save(new Page(name, url, parsingXPath));
        }
    }

    @Override
    public List<Page> getAllPages() {
        return pageRepository.findAll();
    }

    @Override
    public void deletePage(Long id) throws NotFoundException {
        if (pageRepository.findById(id).isPresent()){
            pageRepository.deleteById(id);
        } else {
            throw new NotFoundException("Page with name " + id + " was not found");
        }

    }

    @Override
    public void parsePages() {
        getAllPages().forEach(page -> {
            String result = pageParser.getUrlContent(page.getUrl(), page.getParsingXPath());
            parsingResultService.saveResult(page, result);
        });
    }
}
