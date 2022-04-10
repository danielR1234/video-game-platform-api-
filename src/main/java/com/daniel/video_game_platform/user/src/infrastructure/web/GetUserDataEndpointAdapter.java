package com.daniel.video_game_platform.user.src.infrastructure.web;

import com.daniel.video_game_platform.user.src.application.port.web.GetUserDataEndpointPort;
import com.daniel.video_game_platform.user.src.application.service.GetUserDataService;
import com.daniel.video_game_platform.user.src.infrastructure.web.model.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetUserDataEndpointAdapter implements GetUserDataEndpointPort {

  private final GetUserDataService getUserDataService;

  public GetUserDataEndpointAdapter(GetUserDataService getUserDataService) {
    this.getUserDataService = getUserDataService;
  }

  @Override
  public List<UserDto> fetchAll() {
    return getUserDataService.fetchAll().stream().map(UserDtoMapper::dto).toList();
  }
}
