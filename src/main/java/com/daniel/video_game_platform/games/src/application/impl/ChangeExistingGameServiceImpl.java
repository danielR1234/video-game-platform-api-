package com.daniel.video_game_platform.games.src.application.impl;

import com.daniel.video_game_platform.games.src.application.port.persistance.WriteGamePort;
import com.daniel.video_game_platform.games.src.application.service.ChangeExistingGameService;
import com.daniel.video_game_platform.games.src.domain.Game;
import com.daniel.video_game_platform.games.src.domain.GameIdentifier;
import org.springframework.stereotype.Service;

@Service
public class ChangeExistingGameServiceImpl implements ChangeExistingGameService {

  private final WriteGamePort writeGamePort;

  public ChangeExistingGameServiceImpl(WriteGamePort writeGamePort) {
    this.writeGamePort = writeGamePort;
  }

  @Override
  public void deleteGameById(GameIdentifier gameIdentifier) {
    writeGamePort.delete(gameIdentifier);
  }

  @Override
  public Game updateGame(Game game, Long gameId) {
    return writeGamePort.update(game, gameId);
  }
}
