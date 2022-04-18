package com.daniel.video_game_platform.games.src.application.port.persistance;

import com.daniel.video_game_platform.games.src.domain.Game;
import com.daniel.video_game_platform.games.src.domain.GameIdentifier;

public interface WriteGamePort {

  Game saveNew(Game game);

  Game update(Game game, Long gameId);

  void delete(GameIdentifier gameIdentifier);
}
