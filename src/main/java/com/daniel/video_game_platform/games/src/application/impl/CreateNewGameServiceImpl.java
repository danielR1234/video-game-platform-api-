package com.daniel.video_game_platform.games.src.application.impl;

import com.daniel.video_game_platform.games.src.application.port.persistance.CompanyPort;
import com.daniel.video_game_platform.games.src.application.port.persistance.PlatformPort;
import com.daniel.video_game_platform.games.src.application.port.persistance.ReadGamePort;
import com.daniel.video_game_platform.games.src.application.port.persistance.WriteGamePort;
import com.daniel.video_game_platform.games.src.application.service.CreateNewGameService;
import com.daniel.video_game_platform.games.src.domain.Game;
import org.springframework.stereotype.Service;

@Service
public class CreateNewGameServiceImpl implements CreateNewGameService {

  private final ReadGamePort readGamePort;
  private final WriteGamePort writeGamePort;
  private final CompanyPort companyPort;
  private final PlatformPort platformPort;

  public CreateNewGameServiceImpl(
          ReadGamePort readGamePort,
          WriteGamePort writeGamePort,
          CompanyPort companyPort,
          PlatformPort platformPort) {
    this.readGamePort = readGamePort;
    this.writeGamePort = writeGamePort;
    this.companyPort = companyPort;
    this.platformPort = platformPort;
  }

  @Override
  public Game saveGame(Game game) {
    if (Boolean.TRUE.equals(readGamePort.existsGameByName(game)))
      throw new IllegalArgumentException("Game already exists");
    game.setPublisher(companyPort.fetchById(game.getDeveloper().getCompanyIdentifier()));
    game.setDeveloper(companyPort.fetchById(game.getDeveloper().getCompanyIdentifier()));
    game.setPlatforms(platformPort.fetchAllById(game.getPlatforms()));
    return writeGamePort.saveNew(game);
  }
}
