package ru.lanit.bpm.yodata.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lanit.bpm.yodata.domain.Page;
import ru.lanit.bpm.yodata.domain.Subscription;
import ru.lanit.bpm.yodata.domain.User;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findByUserAndPage(User user, Page page);

    Optional<Object> deleteByUserAndPage(User user, Page page);
}