package com.daniel.video_game_platform.games.src.infrastructure.persistance;

import com.daniel.video_game_platform.games.src.application.port.persistance.ReadGamePort;
import com.daniel.video_game_platform.games.src.domain.Game;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReadGameAdapter implements ReadGamePort {

  private final GameRepository gameRepository;

  public ReadGameAdapter(GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }

  @Override
  public Boolean existsGameByName(Game game) {
    return gameRepository.existsByName(game.getGameName());
  }

  @Override
  public List<Game> fetchAll() {
    return gameRepository.findAll().stream().map(GameJpaMapper::toDomain).toList();
  }
}
