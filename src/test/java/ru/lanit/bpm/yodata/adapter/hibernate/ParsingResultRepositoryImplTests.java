package ru.lanit.bpm.yodata.adapter.hibernate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.lanit.bpm.yodata.app.repo.ParsingResultRepository;
import ru.lanit.bpm.yodata.domain.ParsingResult;
import ru.lanit.bpm.yodata.domain.User;
import ru.lanit.bpm.yodata.fw.YodataBackendApplication;

import java.util.List;
import java.util.Map;

@SpringBootTest(classes = YodataBackendApplication.class)
public class ParsingResultRepositoryImplTests {
    @Autowired
    private ParsingResultRepository parsingResultRepository;
    @Test
    void getUnsentWithUsersList() {
        Map<ParsingResult, List<User>> result = parsingResultRepository.getUnsentWithUsersList();
        System.out.println(result);
    }
}
