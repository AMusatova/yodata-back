package ru.lanit.bpm.yodata.app.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.lanit.bpm.yodata.adapter.telegram.TelegramAdapter;
import ru.lanit.bpm.yodata.app.api.ParsingResultService;
import ru.lanit.bpm.yodata.app.repo.ParsingResultRepository;
import ru.lanit.bpm.yodata.domain.Page;
import ru.lanit.bpm.yodata.domain.ParsingResult;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class ParsingResultServiceImpl implements ParsingResultService {
    private final ParsingResultRepository parsingResultRepository;
    private final TelegramAdapter telegramAdapter;
    @Override
    public void saveResult(Page page, String result) {
        parsingResultRepository.save(new ParsingResult(page, result));
    }

    @Override
    public void notifyUsersUnsentResults() {
        parsingResultRepository.getUnsentWithUsersList().forEach((result, users)->{
            users.forEach(user -> {
                log.info("Sending to user {}", user);
                telegramAdapter.notifyUser(user.getTelegramId(),
                    "Результаты парсинга: " + result.getResult());
            });
            result.setSent(true);
            parsingResultRepository.save(result);
        });
    }

    @Override
    public String getLastSentResultByPage(Page page) {
        List<ParsingResult> listOfSent = parsingResultRepository.findAllSentByPageOrderByParsingDateTimeDesc(page.getId());
        if (!listOfSent.isEmpty()){
            return listOfSent.get(0).getResult();
        }
        return "";
    }


    @Override
    public List<String> getAllNeededToBeSent() {
        List<String> result = new ArrayList<>();
        List<ParsingResult> listOfUnsent = parsingResultRepository.findAllBySent(false);
        listOfUnsent.forEach(current -> {
            String lastRes = getLastSentResultByPage(current.getPage());
            if (!current.getResult().equals(lastRes) && result.indexOf(current.getResult())<0) {
                result.add(current.getResult());
            } else {
                parsingResultRepository.deleteById(current.getId());
            }
        });
        return result;
    }
}
