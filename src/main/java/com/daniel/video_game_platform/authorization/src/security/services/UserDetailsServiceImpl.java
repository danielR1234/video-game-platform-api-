package com.daniel.video_game_platform.authorization.src.security.services;

import com.daniel.video_game_platform.user.src.infrastructure.persistence.UserRepository;
import com.daniel.video_game_platform.user.src.infrastructure.persistence.model.UserJpaEntity;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Data
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired private UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserJpaEntity userJpaEntity =
            userRepository
            .findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' not found"));

    return UserDetailsImpl.build(userJpaEntity);
  }
}
