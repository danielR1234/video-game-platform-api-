package com.daniel.video_game_platform.user.src.infrastructure.persistence;

import com.daniel.video_game_platform.user.src.application.port.persistance.ConfirmationTokenPort;
import com.daniel.video_game_platform.user.src.domain.ConfirmationToken;
import com.daniel.video_game_platform.user.src.domain.User;
import com.daniel.video_game_platform.user.src.domain.UserFunctions;
import com.daniel.video_game_platform.user.src.infrastructure.persistence.model.ConfirmationTokenJpaEntity;
import com.daniel.video_game_platform.user.src.infrastructure.persistence.model.UserJpaEntity;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class ConfirmationTokenAdapter implements ConfirmationTokenPort {

  private final ConfirmationTokenRepository confirmationTokenRepository;

  public ConfirmationTokenAdapter(ConfirmationTokenRepository confirmationTokenRepository) {
    this.confirmationTokenRepository = confirmationTokenRepository;
  }

  @Override
  public ConfirmationToken saveNew(User user) {
    ConfirmationToken confirmationToken = new ConfirmationToken(user);
    ConfirmationTokenJpaEntity confirmationTokenJpaEntity =
        new ConfirmationTokenJpaEntity(
            new UserJpaEntity(UserFunctions.userIdAsLong.apply(user)),
            confirmationToken.getConfirmationToken());
    ConfirmationTokenJpaEntity saveConfirmationToken =
            confirmationTokenRepository.save(confirmationTokenJpaEntity);
    return new ConfirmationToken(saveConfirmationToken.getConfirmationToken());
  }

  @Override
  public String fetchByConfirmationToken(ConfirmationToken confirmationToken) {
    ConfirmationTokenJpaEntity confirmationTokenJpaEntity =
            confirmationTokenRepository
            .findByConfirmationToken(confirmationToken.getConfirmationToken())
            .orElseThrow(() -> new EntityNotFoundException("No with that token"));
    return confirmationTokenJpaEntity.getUserJpaEntity().getEmailAddress();
  }
}
