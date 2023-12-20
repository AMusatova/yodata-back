package ru.lanit.bpm.yodata.app.repo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.lanit.bpm.yodata.domain.User;
import ru.lanit.bpm.yodata.fw.YodataBackendApplication;

import java.util.List;

@SpringBootTest (classes = YodataBackendApplication.class)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;
    @Test
    void getUsersSubscribedOnPage() {
        List<User> result = userRepository.getUsersSubscribedOnPage(Long.valueOf(5));
        System.out.println(result);
    }
}
