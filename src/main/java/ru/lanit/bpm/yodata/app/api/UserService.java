package ru.lanit.bpm.yodata.app.api;

import ru.lanit.bpm.yodata.domain.Subscription;
import ru.lanit.bpm.yodata.domain.User;
import ru.lanit.bpm.yodata.domain.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void registerUser(String login, String password, String firstName, String lastName, Long telegramId) throws DuplicateEntityException;

    List<Subscription> getUserSubscriptions(String login) throws NotFoundException;

    Optional<User> findUserByLogin(String login);
    Optional<User> findUserByTelegramId(Long telegramId);

    void changePassword(String login, String newPassword) throws NotFoundException;
    void deleteUser(String login) throws NotFoundException;

    boolean checkPassword(String login, String password) throws NotFoundException;

    List<User> getAllUsers();

    List<UserRole> getAllUsersWithRoles();
}
