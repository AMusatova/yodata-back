package ru.lanit.bpm.yodata.app.api;

import ru.lanit.bpm.yodata.domain.Subscription;
import ru.lanit.bpm.yodata.domain.User;
import ru.lanit.bpm.yodata.domain.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserRoleService {

    void deleteRole(Long id);

    void setRole(String login, String role) throws NotFoundException, DuplicateEntityException;
}
