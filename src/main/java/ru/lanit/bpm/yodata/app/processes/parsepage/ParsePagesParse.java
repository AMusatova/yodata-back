package ru.lanit.bpm.yodata.app.processes.parsepage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.lanit.bpm.yodata.app.api.PageService;

@Slf4j
@RequiredArgsConstructor
@Component("parseDelegate")
public class ParsePagesParse implements JavaDelegate {
    private final PageService pageService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("Parsing started");
        pageService.parsePages();
        log.info("Parsing ended");
    }
}
