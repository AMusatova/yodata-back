package ru.lanit.bpm.yodata.app.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lanit.bpm.yodata.app.api.DuplicateEntityException;
import ru.lanit.bpm.yodata.app.api.NotFoundException;
import ru.lanit.bpm.yodata.app.api.UserRoleService;
import ru.lanit.bpm.yodata.app.repo.UserRepository;
import ru.lanit.bpm.yodata.app.repo.UserRolesRepository;
import ru.lanit.bpm.yodata.domain.User;
import ru.lanit.bpm.yodata.domain.UserRole;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRolesRepository userRolesRepository;
    private final UserRepository userRepository;


    @Override
    public void deleteRole(Long id) {
        userRolesRepository.deleteById(id);
    }

    @Override
    public void setRole(String login, String role) throws NotFoundException, DuplicateEntityException {
        Optional<User> user = userRepository.findById(login);
        if (user.isPresent()) {
            Optional<UserRole> currentRole = userRolesRepository.findByUserAndAndRole(user.get(), role);
            if (currentRole.isEmpty()) {
                userRolesRepository.save(new UserRole(user.get(), role));
            } else {
                throw new DuplicateEntityException("User already has role " + role);
            }
        } else {
            throw new NotFoundException("User does not exist");
        }
    }
}
