package com.daniel.video_game_platform.games.src.application.service;

import com.daniel.video_game_platform.games.src.domain.Game;

public interface CreateNewGameService {
  Game saveGame(Game game);
}
