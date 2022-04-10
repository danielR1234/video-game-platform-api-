package com.daniel.video_game_platform.user.src.application.service;

import com.daniel.video_game_platform.user.src.domain.User;

import java.util.List;

public interface GetUserDataService {
  List<User> fetchAll();
}
