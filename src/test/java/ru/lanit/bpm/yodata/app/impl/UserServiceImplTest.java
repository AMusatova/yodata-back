package ru.lanit.bpm.yodata.app.impl;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.lanit.bpm.yodata.app.api.DuplicateEntityException;
import ru.lanit.bpm.yodata.app.api.NotFoundException;
import ru.lanit.bpm.yodata.app.api.UserService;
import ru.lanit.bpm.yodata.app.repo.UserRepository;
import ru.lanit.bpm.yodata.domain.User;
import ru.lanit.bpm.yodata.fw.YodataBackendApplication;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = YodataBackendApplication.class)
class UserServiceImplTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Transactional
    @Test
    void registerUser_success() throws DuplicateEntityException {

        userService.registerUser("a", "b", "c", "d", 1L);

        User user = userRepository.findById("a").orElseThrow();

        assertEquals("b", user.getPassword());
        assertEquals("c", user.getFirstName());
        assertEquals("d", user.getLastName());
        assertEquals(1L, user.getTelegramId());

    }

    @Transactional
    @Test
    void registerUser_duplicate() {

        assertThrows(DuplicateEntityException.class, () -> {
            userService.registerUser("a", "b", "c", "d", 1L);
            userService.registerUser("a", "b", "c", "d", 1L);
        });

    }

    @Test
    @Disabled
    void getUserSubscriptions_success() throws NotFoundException {
        System.out.println(userService.getUserSubscriptions("user1")
        );
    }

    @Test
    @Transactional
    void getUserSubscriptions_notFound() {
        assertThrows(NotFoundException.class, ()-> {
            userService.getUserSubscriptions("a");
        });
    }

    @Test
    @Transactional
    void findUserByLogin_success() throws DuplicateEntityException {
        userService.registerUser("a", "b", "c", "d", 1L);
        User user = userService.findUserByLogin("a").orElseThrow();

        assertEquals("b", user.getPassword());
        assertEquals("c", user.getFirstName());
        assertEquals("d", user.getLastName());
        assertEquals(1L, user.getTelegramId());
    }

    @Test
    void findUserByLogin_notfound() {
        assertFalse(userService.findUserByLogin("a").isPresent());
    }

    @Test
    @Transactional
    void changePassword_success() throws NotFoundException, DuplicateEntityException {
        userService.registerUser("a", "b", "c", "d", 1L);
        userService.changePassword("a", "z");
        User user = userService.findUserByLogin("a").orElseThrow();
        assertEquals("z", user.getPassword());
    }

    @Test
    @Transactional
    void changePassword_notFound() {
        assertThrows(NotFoundException.class, () -> {
            userService.changePassword("a", "b");
        });
    }

    @Test
    @Transactional
    void deleteUser_success() throws NotFoundException, DuplicateEntityException {
        userService.registerUser("a", "b", "c", "d", 1L);
        userService.deleteUser("a");
        assertFalse(userService.findUserByLogin("a").isPresent());
    }

    @Test
    @Transactional
    void deleteUser_notFound() {
        assertThrows(NotFoundException.class, () -> {
            userService.deleteUser("a");
        });
    }

    @Test
    @Transactional
    void checkPassword_correct() throws DuplicateEntityException, NotFoundException {
        userService.registerUser("a", "b", "c", "d", 1L);
        assertTrue(userService.checkPassword("a", "b"));
    }

    @Test
    @Transactional
    void checkPassword_incorrect() throws DuplicateEntityException, NotFoundException {
        userService.registerUser("a", "b", "c", "d", 1L);
        assertFalse(userService.checkPassword("a", "bb"));
    }

    @Test
    @Transactional
    void checkPassword_notFound() {
        assertThrows(NotFoundException.class, () -> {
            userService.checkPassword("a", "b");
        });
    }
}