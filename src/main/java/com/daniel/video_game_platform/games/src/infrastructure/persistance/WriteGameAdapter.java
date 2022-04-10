package com.daniel.video_game_platform.games.src.infrastructure.persistance;

import com.daniel.video_game_platform.games.src.application.port.persistance.WriteGamePort;
import com.daniel.video_game_platform.games.src.domain.Game;
import com.daniel.video_game_platform.games.src.domain.GameIdentifier;
import com.daniel.video_game_platform.games.src.infrastructure.persistance.model.GameJpaEntity;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class WriteGameAdapter implements WriteGamePort {

  private final GameRepository gameRepository;

  public WriteGameAdapter(GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }

  @Override
  public Game saveNew(Game game) {
    GameJpaEntity gameJpaEntity = GameJpaMapper.toJpaEntity(game);
    return GameJpaMapper.toDomain(gameRepository.save(gameJpaEntity));
  }

  @Override
  public Game update(Game game, Long gameId) {

    GameJpaEntity persistedEntity =
            gameRepository
            .findById(gameId)
            .orElseThrow(() -> new EntityNotFoundException("No Game found with that id"));

    if (game.getGameName() != null) persistedEntity.setName(game.getGameName());
    if (game.getGameDescription() != null) persistedEntity.setDescription(game.getGameDescription());
    if (game.getGameReleaseYear() != null) persistedEntity.setYear(game.getGameReleaseYear().getValue());
    if (game.getGameSales().getValue() != null) persistedEntity.setGlobalSales(game.getGameSales().getValue());
    if (game.getGameImageUrl().getImageUrl() != null) persistedEntity.setImageUrl(game.getGameImageUrl().getImageUrl());

    return GameJpaMapper.toDomain(gameRepository.save(persistedEntity));
  }

  @Override
  public void delete(GameIdentifier gameIdentifier) {
    gameRepository.deleteById(gameIdentifier.getGameIdentifier());
  }
}
