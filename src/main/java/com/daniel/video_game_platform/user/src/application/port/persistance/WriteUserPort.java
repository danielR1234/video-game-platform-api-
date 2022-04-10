package com.daniel.video_game_platform.user.src.application.port.persistance;

import com.daniel.video_game_platform.user.src.domain.User;

import java.util.Optional;

public interface WriteUserPort {

  User saveNew(User user);

  Optional<User> update(User user);
}
