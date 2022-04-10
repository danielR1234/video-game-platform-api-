package com.daniel.video_game_platform.games.src.application.impl;

import com.daniel.video_game_platform.games.src.application.port.persistance.ReadGamePort;
import com.daniel.video_game_platform.games.src.application.service.GetGamesService;
import com.daniel.video_game_platform.games.src.domain.Game;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetGamesServiceImpl implements GetGamesService {

  private final ReadGamePort readGamePort;

  public GetGamesServiceImpl(ReadGamePort readGamePort) {
    this.readGamePort = readGamePort;
  }

  @Override
  public List<Game> getAllGames() {
    return readGamePort.fetchAll();
  }
}
