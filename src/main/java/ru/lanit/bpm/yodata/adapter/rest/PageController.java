package ru.lanit.bpm.yodata.adapter.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.lanit.bpm.yodata.app.api.DuplicateEntityException;
import ru.lanit.bpm.yodata.app.api.NotFoundException;
import ru.lanit.bpm.yodata.app.api.PageService;
import ru.lanit.bpm.yodata.domain.Page;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/yodata/admin/pages")
public class PageController {
    private final PageService pageService;

    @PostMapping
    public ResponseEntity<String> crestePage(@RequestBody Page page){
        try {
            pageService.addPage(page.getName(), page.getUrl(), page.getParsingXPath());
            return ResponseEntity.ok("Created");
        } catch (DuplicateEntityException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getAllPages")
    public ResponseEntity<List<Page>> getAllPages(){
            return ResponseEntity.ok().body(pageService.getAllPages());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deletePage(@PathVariable Long id){
        try {
            pageService.deletePage(id);
            return  ResponseEntity.ok("Deleted");
        } catch (NotFoundException e) {
           return ResponseEntity.badRequest().body("Page was not found");
        }
    }
}
