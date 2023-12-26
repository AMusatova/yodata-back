package ru.lanit.bpm.yodata.app.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.lanit.bpm.yodata.app.api.DuplicateEntityException;
import ru.lanit.bpm.yodata.app.api.NotFoundException;
import ru.lanit.bpm.yodata.app.api.UserRoleService;
import ru.lanit.bpm.yodata.app.repo.UserRepository;
import ru.lanit.bpm.yodata.app.repo.UserRolesRepository;
import ru.lanit.bpm.yodata.domain.User;
import ru.lanit.bpm.yodata.domain.UserRole;
import ru.lanit.bpm.yodata.fw.YodataBackendApplication;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = YodataBackendApplication.class)
public class UserRoleServiceTest {
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserRolesRepository userRolesRepository;
    @Autowired
    private UserRepository userRepository;
    private final String testRole = "TEST_ROLE";

    @Transactional
    @Test
    void deleteRole_success(){
        userRepository.save(new User("test", "test", "test", "test", 1L));
        User user = userRepository.findById("test").orElseThrow();
        userRolesRepository.save(new UserRole(user, testRole));
        UserRole userRole = userRolesRepository.findByUserAndAndRole(user, testRole).orElseThrow();
        userRoleService.deleteRole(userRole.getId());
        assertTrue(userRolesRepository.findById(userRole.getId()).isEmpty());
    }

    @Transactional
    @Test
    void deleteRole_notFound(){
        assertThrows(Exception.class, () -> {userRoleService.deleteRole(999L);});
    }

    @Transactional
    @Test
    void setRole_success() throws DuplicateEntityException, NotFoundException {
        userRepository.save(new User("test", "test", "test", "test", 1L));
        User user = userRepository.findById("test").orElseThrow();
        userRoleService.setRole(user.getLogin(), testRole);
        UserRole result = userRolesRepository.findByUserAndAndRole(user, testRole).orElseThrow();
        assertEquals("test", result.getUser().getLogin());
        assertEquals("test", result.getUser().getLastName());
        assertEquals("test", result.getUser().getPassword());
        assertEquals("test", result.getUser().getFirstName());
        assertEquals(1L, result.getUser().getTelegramId());
        assertEquals(testRole, result.getRole());
    }

    @Transactional
    @Test
    void setRole_userNotFound(){
        User user = new User("test", "test", "test", "test", 1L);
        assertThrows(Exception.class, () -> {userRoleService.setRole(user.getLogin(), testRole);;});
    }

    @Transactional
    @Test
    void setRole_duplicate() throws DuplicateEntityException, NotFoundException {
        userRepository.save(new User("test", "test", "test", "test", 1L));
        User user = userRepository.findById("test").orElseThrow();
        userRoleService.setRole(user.getLogin(), testRole);
        UserRole result = userRolesRepository.findByUserAndAndRole(user, testRole).orElseThrow();
        assertThrows(Exception.class, () -> {userRoleService.setRole(user.getLogin(), testRole);;});
    }
}
