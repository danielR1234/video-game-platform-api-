package com.daniel.video_game_platform.games.src.infrastructure.persistance;

import com.daniel.video_game_platform.games.src.domain.*;
import com.daniel.video_game_platform.games.src.infrastructure.helper.MapperHelper;
import com.daniel.video_game_platform.games.src.infrastructure.persistance.model.GameJpaEntity;

import java.time.Year;

class GameJpaMapper {

  private GameJpaMapper() {}

  static Game toDomain(GameJpaEntity gameJpaEntity) {

    return new Game(
        new GameIdentifier(gameJpaEntity.getId()),
        gameJpaEntity.getName(),
        gameJpaEntity.getDescription(),
        new GameSales(gameJpaEntity.getGlobalSales()),
        Year.of(gameJpaEntity.getYear()),
        new GameImageUrl(gameJpaEntity.getImageUrl()),
        gameJpaEntity.getCreatedAt(),
        gameJpaEntity.getUpdateAt(),
        new Company(
            new CompanyIdentifier(gameJpaEntity.getPublisher().getId()),
            gameJpaEntity.getPublisher().getName()),
        new Company(
            new CompanyIdentifier(gameJpaEntity.getDeveloper().getId()),
            gameJpaEntity.getDeveloper().getName()),
        MapperHelper.getPlatFormGameJpaEntitySet(gameJpaEntity));
  }

  static GameJpaEntity toJpaEntity(Game game) {

    return new GameJpaEntity(
        game.getGameName(),
        game.getGameDescription(),
        game.getGameReleaseYear().getValue(),
        game.getGameSales().getValue(),
        MapperHelper.getCompanyJpaEntityPublisher(game),
        MapperHelper.getCompanyJpaEntityDeveloper(game),
        MapperHelper.getPlatFormGameDomain(game),
        game.getGameImageUrl().getImageUrl());
  }
}
