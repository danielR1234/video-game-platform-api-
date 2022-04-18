package com.daniel.video_game_platform.user.src.infrastructure.persistence;

import com.daniel.video_game_platform.user.src.infrastructure.persistence.model.ConfirmationTokenJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository
    extends JpaRepository<ConfirmationTokenJpaEntity, String> {
  Optional<ConfirmationTokenJpaEntity> findByConfirmationToken(String confirmationToken);
}
