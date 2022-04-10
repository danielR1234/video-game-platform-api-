package com.daniel.video_game_platform.user.src.infrastructure.persistence;

import com.daniel.video_game_platform.user.src.infrastructure.persistence.model.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserJpaEntity, Long> {
  Optional<UserJpaEntity> findByUsername(String username);

  Optional<UserJpaEntity> findByEmailAddress(String emailAddress);
}
