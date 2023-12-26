package ru.lanit.bpm.yodata.app.repo;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.lanit.bpm.yodata.app.api.DuplicateEntityException;
import ru.lanit.bpm.yodata.app.api.UserService;
import ru.lanit.bpm.yodata.domain.User;
import ru.lanit.bpm.yodata.domain.UserRole;
import ru.lanit.bpm.yodata.fw.YodataBackendApplication;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = YodataBackendApplication.class)
public class UserRolesRepositoryTests {
    @Autowired
    private UserRolesRepository userRolesRepository;
    @Autowired
    private UserRepository userRepository;
    private final String testRole = "TEST_ROLE";
    @Test
    @Transactional
    void findByUserAndAndRole_found() throws DuplicateEntityException {
        userRepository.save(new User("test", "test", "test", "test", 1L));
        User user = userRepository.findById("test").orElseThrow();
        userRolesRepository.save(new UserRole(user, testRole));
        UserRole result = userRolesRepository.findByUserAndAndRole(user, testRole).orElseThrow();
        assertEquals("test", result.getUser().getLogin());
        assertEquals("test", result.getUser().getLastName());
        assertEquals("test", result.getUser().getPassword());
        assertEquals("test", result.getUser().getFirstName());
        assertEquals(1L, result.getUser().getTelegramId());
        assertEquals(testRole, result.getRole());
    }

    @Test
    @Transactional
    void findByUserAndAndRole_notFound() throws DuplicateEntityException {
        userRepository.save(new User("test", "test", "test", "test", 1L));
        User user = userRepository.findById("test").orElseThrow();
        Optional<UserRole> result = userRolesRepository.findByUserAndAndRole(user, testRole);
        assertTrue(result.isEmpty());
    }
}
