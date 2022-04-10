package com.daniel.video_game_platform.games.src.application.service;

import com.daniel.video_game_platform.games.src.domain.Game;
import com.daniel.video_game_platform.games.src.domain.GameIdentifier;

public interface ChangeExistingGameService {

  void deleteGameById(GameIdentifier gameIdentifier);

  Game updateGame(Game game, Long gameId);
}
