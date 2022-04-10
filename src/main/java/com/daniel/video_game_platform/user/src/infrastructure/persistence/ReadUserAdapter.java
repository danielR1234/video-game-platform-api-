package com.daniel.video_game_platform.user.src.infrastructure.persistence;

import com.daniel.video_game_platform.user.src.application.port.persistance.ReadUserPort;
import com.daniel.video_game_platform.user.src.domain.EmailAddress;
import com.daniel.video_game_platform.user.src.domain.User;
import com.daniel.video_game_platform.user.src.domain.UserFunctions;
import com.daniel.video_game_platform.user.src.infrastructure.persistence.model.UserJpaEntity;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Component
public class ReadUserAdapter implements ReadUserPort {

  private final UserRepository userRepository;

  public ReadUserAdapter(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public boolean existsByUsername(User user) {
    return userRepository.findByUsername(UserFunctions.userUsername.apply(user)).isPresent();
  }

  @Override
  public User fetchByEmail(EmailAddress emailAddress) {
    UserJpaEntity userJpaEntity =
            userRepository
            .findByEmailAddress(emailAddress.getEmail())
            .orElseThrow(() -> new EntityNotFoundException("No with that email"));
    return UserJpaMapper.toDomain(userJpaEntity);
  }

  @Override
  public List<User> fetchAll() {
    return userRepository.findAll().stream().map(UserJpaMapper::toDomain).toList();
  }
}
