package com.daniel.video_game_platform.games.src.application.port.web;

import com.daniel.video_game_platform.games.src.infrastructure.web.model.AddGameRequest;
import com.daniel.video_game_platform.games.src.infrastructure.web.model.GameDto;

public interface CreateNewGameEndpointPort {

  GameDto saveNew(AddGameRequest AddGameRequest);
}
