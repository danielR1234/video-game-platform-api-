package com.daniel.video_game_platform.user.src.infrastructure.web;

import com.daniel.video_game_platform.user.src.domain.User;
import com.daniel.video_game_platform.user.src.infrastructure.web.model.UserDto;

import java.util.stream.Collectors;

class UserDtoMapper {

  private UserDtoMapper() {}

  static UserDto dto(User user) {
    return new UserDto(
        user.getUserId().getValue(),
        user.getUsername().getUsername(),
        user.getEmailAddress().getEmail(),
        user.isEnabled(),
        user.getRoles().stream().map(it -> it.getName().name()).collect(Collectors.toSet()),
        user.getCreatedAt(),
        user.getUpdatedAt());
  }
}
