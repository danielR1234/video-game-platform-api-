package com.daniel.video_game_platform.games.src.infrastructure.web;

import com.daniel.video_game_platform.games.src.application.impl.ChangeExistingGameServiceImpl;
import com.daniel.video_game_platform.games.src.application.port.web.ChangeGameEndpointPort;
import com.daniel.video_game_platform.games.src.domain.GameIdentifier;
import com.daniel.video_game_platform.games.src.infrastructure.web.model.AddGameRequest;
import com.daniel.video_game_platform.games.src.infrastructure.web.model.GameDto;
import org.springframework.stereotype.Component;

@Component
public class ChangeGameEndpointAdapter implements ChangeGameEndpointPort {

  private final ChangeExistingGameServiceImpl changeExistingGameService;

  public ChangeGameEndpointAdapter(ChangeExistingGameServiceImpl changeExistingGameService) {
    this.changeExistingGameService = changeExistingGameService;
  }

  @Override
  public void deleteById(Long gameId) {
    changeExistingGameService.deleteGameById(new GameIdentifier(gameId));
  }

  @Override
  public GameDto updateGame(AddGameRequest addGameRequest, Long id) {
    return GameDtoMapper.toDto(
            changeExistingGameService.updateGame(
            GameDtoMapper.toDomainFromSaveGameBody(addGameRequest), id));
  }
}
