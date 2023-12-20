package ru.lanit.bpm.yodata.app.processes.parsepage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.lanit.bpm.yodata.app.api.PageService;
import ru.lanit.bpm.yodata.app.api.ParsingResultService;
import ru.lanit.bpm.yodata.domain.ParsingResult;
import ru.lanit.bpm.yodata.fw.CamundaConfig;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component("decisionDelegate")
public class ParsePagesDecision {
    private final ParsingResultService parsingResultService;
    private final CamundaConfig camundaConfig;

    public boolean isNotificationNeeded() {
        List<String> unsentResults = parsingResultService.getAllNeededToBeSent();
        if (unsentResults.size() > camundaConfig.getMinSendingAmount()){
            log.info("Notification is needed");
            return true;
        } else {
        log.info("Notification is skipped");
        return false;
        }
    }
}
