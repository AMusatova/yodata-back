package ru.lanit.bpm.yodata.app.repo;

import org.springframework.data.jpa.repository.Query;
import ru.lanit.bpm.yodata.domain.ParsingResult;
import ru.lanit.bpm.yodata.domain.User;

import java.util.List;
import java.util.Map;


public interface ParsingResultRepositoryCustom {
    public Map<ParsingResult, List<User>> getUnsentWithUsersList();
}
