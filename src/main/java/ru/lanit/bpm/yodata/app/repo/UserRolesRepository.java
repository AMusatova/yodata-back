package ru.lanit.bpm.yodata.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.lanit.bpm.yodata.domain.User;
import ru.lanit.bpm.yodata.domain.UserRole;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRole, Long> {
    @Query("select ur from UserRole ur where ur.user=:user and ur.role=:role")
    Optional<UserRole> findByUserAndAndRole(User user, String role);
}