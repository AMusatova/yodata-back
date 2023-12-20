package ru.lanit.bpm.yodata.adapter.hibernate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.lanit.bpm.yodata.app.repo.ParsingResultRepositoryCustom;
import ru.lanit.bpm.yodata.app.repo.UserRepository;
import ru.lanit.bpm.yodata.domain.ParsingResult;
import ru.lanit.bpm.yodata.domain.User;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ParsingResultRepositoryImpl implements ParsingResultRepositoryCustom {


    private final UserRepository userRepository;

    private final EntityManager entityManager;


    @Override
    public Map<ParsingResult, List<User>> getUnsentWithUsersList(){
        Map<ParsingResult, List<User>> result = new HashMap<ParsingResult, List<User>>();
        List<ParsingResult> unsentParsingResults = entityManager
            .createQuery("select PR from ParsingResult PR where PR.sent = false")
            .getResultList();
        for (ParsingResult parsingResult: unsentParsingResults){
            List<User> users = userRepository.getUsersSubscribedOnPage(parsingResult.getPage().getId());
            result.put(parsingResult, users);
        }
        return result;
    }
}
