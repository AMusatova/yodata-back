package ru.lanit.bpm.yodata.app.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lanit.bpm.yodata.app.api.DuplicateEntityException;
import ru.lanit.bpm.yodata.app.api.NotFoundException;
import ru.lanit.bpm.yodata.app.api.UserService;
import ru.lanit.bpm.yodata.app.repo.UserRepository;
import ru.lanit.bpm.yodata.app.repo.UserRolesRepository;
import ru.lanit.bpm.yodata.domain.Subscription;
import ru.lanit.bpm.yodata.domain.User;
import ru.lanit.bpm.yodata.domain.UserRole;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService  {

    private final UserRepository userRepository;

    private final UserRolesRepository userRolesRepository;


    @Override
    public void registerUser(String login, String password, String firstName, String lastName, Long telegramId) throws DuplicateEntityException {
        if (userRepository.findById(login).isPresent()) {
            throw new DuplicateEntityException("User already exists");
        } else {
            User user = new User(login, password, firstName, lastName, telegramId);
            userRepository.save(user);
            userRolesRepository.save(new UserRole(user,"USER" ));
        }
    }

    @Override
    public List<Subscription> getUserSubscriptions(String login) throws NotFoundException {
        if (userRepository.findById(login).isPresent()) {
            return userRepository.getUserSubscriptions(login);
        } else {
        throw new NotFoundException("User does not exist");
    }
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        return userRepository.findById(login);
    }

    @Override
    public Optional<User> findUserByTelegramId(Long telegramId) {
        return userRepository.findByTelegramId(telegramId);
    }

    @Override
    public void changePassword(String login, String newPassword) throws NotFoundException {
        if (userRepository.findById(login).isPresent()) {
            User user = userRepository.findById(login).get();
            user.setPassword(newPassword);
            userRepository.save(user);
        } else {
            throw new NotFoundException("User does not exist");
        }
    }

    @Override
    public void deleteUser(String login) throws NotFoundException {
        if (userRepository.findById(login).isPresent()) {
            userRepository.deleteById(login);
        } else {
            throw new NotFoundException("User does not exist");
        }
    }

    @Override
    public boolean checkPassword(String login, String password) throws NotFoundException {
        if (userRepository.findById(login).isPresent()) {
            return userRepository.findById(login).get().getPassword().equals(password);
        } else {
            throw new NotFoundException("User does not exist");
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<UserRole> getAllUsersWithRoles() {
        return userRolesRepository.findAll();
    }
}
