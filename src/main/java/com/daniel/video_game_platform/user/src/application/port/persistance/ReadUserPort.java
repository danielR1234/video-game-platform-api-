package com.daniel.video_game_platform.user.src.application.port.persistance;

import com.daniel.video_game_platform.user.src.domain.EmailAddress;
import com.daniel.video_game_platform.user.src.domain.User;

import java.util.List;

public interface ReadUserPort {

  boolean existsByUsername(User user);

  User fetchByEmail(EmailAddress emailAddress);

  List<User> fetchAll();
}
