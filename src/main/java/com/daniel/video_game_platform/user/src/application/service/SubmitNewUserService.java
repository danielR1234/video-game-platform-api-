package com.daniel.video_game_platform.user.src.application.service;

import com.daniel.video_game_platform.user.src.domain.User;

public interface SubmitNewUserService {
  User save(User user);
}
