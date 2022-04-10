package com.daniel.video_game_platform.user.src.infrastructure.persistence;

import com.daniel.video_game_platform.user.src.application.port.persistance.PasswordResetTokenPort;
import com.daniel.video_game_platform.user.src.domain.PasswordResetToken;
import com.daniel.video_game_platform.user.src.domain.User;
import com.daniel.video_game_platform.user.src.domain.UserFunctions;
import com.daniel.video_game_platform.user.src.infrastructure.persistence.model.PasswordResetTokenJpaEntity;
import com.daniel.video_game_platform.user.src.infrastructure.persistence.model.UserJpaEntity;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class PasswordResetTokenAdapter implements PasswordResetTokenPort {
  private final PasswordResetTokenRepository passwordResetTokenRepository;

  public PasswordResetTokenAdapter(PasswordResetTokenRepository passwordResetTokenRepository) {
    this.passwordResetTokenRepository = passwordResetTokenRepository;
  }

  @Override
  public PasswordResetToken save(User user) {
    PasswordResetToken passwordResetToken = new PasswordResetToken(user);
    PasswordResetTokenJpaEntity passwordResetTokenJpaEntity =
        new PasswordResetTokenJpaEntity(
            new UserJpaEntity(UserFunctions.userIdAsLong.apply(user)),
            passwordResetToken.getPasswordResetToken());
    PasswordResetTokenJpaEntity savedPasswordResetToken =
            passwordResetTokenRepository.save(passwordResetTokenJpaEntity);
    return new PasswordResetToken(savedPasswordResetToken.getPasswordResetToken());
  }

  @Override
  public User fetchByPasswordResetToken(PasswordResetToken passwordResetToken) {

    PasswordResetTokenJpaEntity passwordResetTokenJpaEntity =
            passwordResetTokenRepository
            .findByPasswordResetToken(passwordResetToken.getPasswordResetToken())
            .orElseThrow(() -> new EntityNotFoundException("wrong token"));

    return UserJpaMapper.toDomain(passwordResetTokenJpaEntity.getUserJpaEntity());
  }
}
