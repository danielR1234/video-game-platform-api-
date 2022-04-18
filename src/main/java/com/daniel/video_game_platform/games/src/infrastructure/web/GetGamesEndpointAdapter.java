package com.daniel.video_game_platform.games.src.infrastructure.web;

import com.daniel.video_game_platform.games.src.application.port.web.GetGamesEndpointPort;
import com.daniel.video_game_platform.games.src.application.service.GetGamesService;
import com.daniel.video_game_platform.games.src.infrastructure.web.model.GameDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetGamesEndpointAdapter implements GetGamesEndpointPort {

  private final GetGamesService getGamesService;

  public GetGamesEndpointAdapter(GetGamesService getGamesService) {
    this.getGamesService = getGamesService;
  }

  @Override
  public List<GameDto> fetchAllGames() {
    return getGamesService.getAllGames().stream().map(GameDtoMapper::toDto).toList();
  }
}
