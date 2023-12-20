package ru.lanit.bpm.yodata.app.api;

import ru.lanit.bpm.yodata.domain.Page;

import java.util.List;

/**
 * todo Document type ParsingResultService
 */
public interface ParsingResultService {
    void saveResult(Page page, String result);

    void notifyUsersUnsentResults();

    public List<String> getAllNeededToBeSent();

    String getLastSentResultByPage(Page page);
}
