package ru.lanit.bpm.yodata.app.impl;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.lanit.bpm.yodata.app.api.*;
import ru.lanit.bpm.yodata.app.repo.PageRepository;
import ru.lanit.bpm.yodata.app.repo.SubscriptionRepository;
import ru.lanit.bpm.yodata.domain.Page;
import ru.lanit.bpm.yodata.domain.Subscription;
import ru.lanit.bpm.yodata.domain.User;
import ru.lanit.bpm.yodata.fw.YodataBackendApplication;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = YodataBackendApplication.class)
class SubscriptionServiceImplTest {
    @Autowired
    SubscriptionService subscriptionService;
    @Autowired
    UserService userService;
    @Autowired
    PageService pageService;
    @Autowired
    PageRepository pageRepository;
    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Test
    @Transactional
    void addSubscription_success() throws DuplicateEntityException, NotFoundException {
        pageService.addPage("p1", "p2", "p3");
        Page page = pageRepository.findByName("p1").get();
        userService.registerUser("u1", "u2", "u3", "u4", 5L);
        User user = userService.findUserByLogin("u1").get();
        subscriptionService.addSubscription("u1", pageRepository.findByName("p1").get().getId());
        assertTrue(subscriptionRepository.findByUserAndPage(user, page).isPresent());
    }

    @Test
    @Transactional
    void addSubscription_userNotFound() throws DuplicateEntityException, NotFoundException {
        pageService.addPage("p1", "p2", "p3");
        assertThrows(NotFoundException.class, ()-> {
            subscriptionService.addSubscription("u1", pageRepository.findByName("p1").get().getId());
        });
    }

    @Test
    @Transactional
    void addSubscription_pageNotFound() throws DuplicateEntityException, NotFoundException {
        userService.registerUser("u1", "u2", "u3", "u4", 5L);
        assertThrows(NotFoundException.class, ()-> {
            subscriptionService.addSubscription("u1", 9999L);
        });
    }
    @Test
    @Transactional
    void addSubscription_duplicate() throws DuplicateEntityException, NotFoundException {
        pageService.addPage("p1", "p2", "p3");
        userService.registerUser("u1", "u2", "u3", "u4", 5L);
        assertThrows(DuplicateEntityException.class, ()-> {
            subscriptionService.addSubscription("u1", pageRepository.findByName("p1").get().getId());
            subscriptionService.addSubscription("u1", pageRepository.findByName("p1").get().getId());
        });
    }

    @Test
    @Transactional
    void deleteSubscription() throws NotFoundException, DuplicateEntityException {
        pageService.addPage("p1", "p2", "p3");
        Page page = pageRepository.findByName("p1").get();
        userService.registerUser("u1", "u2", "u3", "u4", 5L);
        User user = userService.findUserByLogin("u1").get();
        subscriptionService.addSubscription("u1", pageRepository.findByName("p1").get().getId());
        subscriptionService.deleteSubscription("u1", pageRepository.findByName("p1").get().getId());
        assertFalse(subscriptionRepository.findByUserAndPage(user, page).isPresent());
    }
    @Test
    @Transactional
    void deleteSubscription_userNotFound() throws NotFoundException, DuplicateEntityException {
        pageService.addPage("p1", "p2", "p3");
        userService.registerUser("u1", "u2", "u3", "u4", 5L);
        subscriptionService.addSubscription("u1", pageRepository.findByName("p1").get().getId());
        userService.deleteUser("u1");
        assertThrows(NotFoundException.class, ()-> {
            subscriptionService.deleteSubscription("u1", pageRepository.findByName("p1").get().getId());
        });
    }
    @Test
    @Transactional
    @Disabled
    void deleteSubscription_pageNotFound() throws NotFoundException, DuplicateEntityException {
        pageService.addPage("p1", "p2", "p3");
        userService.registerUser("u1", "u2", "u3", "u4", 5L);
        subscriptionService.addSubscription("u1", pageRepository.findByName("p1").get().getId());
        pageService.deletePage(pageRepository.findByName("p1").get().getId());
        assertThrows(NotFoundException.class, ()-> {
            subscriptionService.deleteSubscription("u1", pageRepository.findByName("p1").get().getId());
        });
    }
    @Test
    @Transactional
    void deleteSubscription_subscriptionNotFound() throws NotFoundException {
        assertThrows(NotFoundException.class, ()-> {
            subscriptionService.deleteSubscription("u1", 99999L);
        });
    }
}