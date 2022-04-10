package com.daniel.video_game_platform.games.src.infrastructure.web;

import com.daniel.video_game_platform.games.src.application.port.web.CreateNewGameEndpointPort;
import com.daniel.video_game_platform.games.src.application.service.CreateNewGameService;
import com.daniel.video_game_platform.games.src.infrastructure.web.model.AddGameRequest;
import com.daniel.video_game_platform.games.src.infrastructure.web.model.GameDto;
import org.springframework.stereotype.Component;

@Component
public class CreateNewGameEndpointAdapter implements CreateNewGameEndpointPort {

  private final CreateNewGameService createNewGameService;

  public CreateNewGameEndpointAdapter(CreateNewGameService createNewGameService) {
    this.createNewGameService = createNewGameService;
  }

  @Override
  public GameDto saveNew(AddGameRequest addGameRequest) {
    return GameDtoMapper.toDto(
            createNewGameService.saveGame(GameDtoMapper.toDomainFromSaveGameBody(addGameRequest)));
  }
}
