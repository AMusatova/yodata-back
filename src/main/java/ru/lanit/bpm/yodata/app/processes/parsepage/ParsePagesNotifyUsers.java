package ru.lanit.bpm.yodata.app.processes.parsepage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.lanit.bpm.yodata.app.api.PageService;
import ru.lanit.bpm.yodata.app.api.ParsingResultService;

@Slf4j
@RequiredArgsConstructor
@Component("notifyDelegate")
public class ParsePagesNotifyUsers implements JavaDelegate {
    private final ParsingResultService parsingResultService;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        parsingResultService.notifyUsersUnsentResults();

    }
}
