package com.daniel.video_game_platform.games.src.application.port.web;

import com.daniel.video_game_platform.games.src.infrastructure.web.model.AddGameRequest;
import com.daniel.video_game_platform.games.src.infrastructure.web.model.GameDto;

public interface ChangeGameEndpointPort {

  void deleteById(Long gameId);

  GameDto updateGame(AddGameRequest addGameRequest, Long id);
}
