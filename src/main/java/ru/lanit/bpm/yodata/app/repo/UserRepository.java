package ru.lanit.bpm.yodata.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.lanit.bpm.yodata.domain.Subscription;
import ru.lanit.bpm.yodata.domain.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("select ss from User u join Subscription ss on u.login=ss.user where u.login = :login")
    List<Subscription> getUserSubscriptions(String login);

    @Query("select u from User u join Subscription ss on u.login=ss.user where ss.page.id=:pageId")
    List<User> getUsersSubscribedOnPage(Long pageId);

    Optional<User> findByTelegramId(Long telegramId);
}
