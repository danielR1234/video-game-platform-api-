package com.daniel.video_game_platform.user.src.application.port.web;

import com.daniel.video_game_platform.user.src.infrastructure.web.model.UserDto;

import java.util.List;

public interface GetUserDataEndpointPort {

  List<UserDto> fetchAll();
}
