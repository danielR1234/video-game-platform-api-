package com.daniel.video_game_platform.authorization.src.application.service;

import com.daniel.video_game_platform.authorization.src.infrastructure.web.model.JwtResponse;
import com.daniel.video_game_platform.user.src.domain.User;

public interface LoginUserService {
  JwtResponse validateUserLogin(User user) throws Exception;
}
