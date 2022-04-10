package com.daniel.video_game_platform.user.src.infrastructure.persistence;

import com.daniel.video_game_platform.user.src.application.port.persistance.WriteUserPort;
import com.daniel.video_game_platform.user.src.domain.User;
import com.daniel.video_game_platform.user.src.domain.UserFunctions;
import com.daniel.video_game_platform.user.src.infrastructure.persistence.model.UserJpaEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WriteUserAdapter implements WriteUserPort {

  private final UserRepository userRepository;

  public WriteUserAdapter(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User saveNew(User user) {
    UserJpaEntity userJpaEntity = UserJpaMapper.toJpaEntity(user);
    UserJpaEntity savedUserJpaEntity = userRepository.save(userJpaEntity);
    return UserJpaMapper.toDomain(savedUserJpaEntity);
  }

  @Override
  public Optional<User> update(User user) {
    return userRepository
        .findById(UserFunctions.userUserId.apply(user))
        .map(persistedUserData -> UserJpaMapper.toJpaEntity(user, persistedUserData))
        .map(userRepository::save)
        .map(UserJpaMapper::toDomain);
  }
}
