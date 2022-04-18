package com.daniel.video_game_platform.user.src.infrastructure.persistence;

import com.daniel.video_game_platform.user.src.infrastructure.persistence.model.PasswordResetTokenJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository
    extends JpaRepository<PasswordResetTokenJpaEntity, Long> {
  Optional<PasswordResetTokenJpaEntity> findByPasswordResetToken(String passwordResetToken);
}
