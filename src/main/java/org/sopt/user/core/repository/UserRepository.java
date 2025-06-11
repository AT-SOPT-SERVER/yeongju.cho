package org.sopt.user.core.repository;

import org.sopt.user.core.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findById(Long id);

    boolean existsByNickname(String nickname);

}