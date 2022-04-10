package com.daniel.video_game_platform.games.src.infrastructure.web;

import com.daniel.video_game_platform.games.src.domain.*;
import com.daniel.video_game_platform.games.src.infrastructure.helper.MapperHelper;
import com.daniel.video_game_platform.games.src.infrastructure.web.model.AddGameRequest;
import com.daniel.video_game_platform.games.src.infrastructure.web.model.GameDto;

import java.time.Year;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

class GameDtoMapper {

  private GameDtoMapper() {}

  static Game toDomainFromSaveGameBody(AddGameRequest addGameRequest) {
    return new Game(
        addGameRequest.getName(),
        addGameRequest.getDescription(),
        new GameSales(addGameRequest.getGlobalSales()),
        Year.of(addGameRequest.getYear()),
        new GameImageUrl(addGameRequest.getImageUrl()),
        new Company(new CompanyIdentifier(addGameRequest.getPublisherId())),
        new Company(new CompanyIdentifier(addGameRequest.getDeveloperId())),
        getPlatformsFromAddGameRequest(addGameRequest.getPlatformIds()));
  }

  static GameDto toDto(Game game) {
    return new GameDto(
        game.getGameIdentifier().getGameIdentifier(),
        game.getCreatedAt(),
        game.getUpdateAt(),
        game.getGameName(),
        game.getGameDescription(),
        game.getGameReleaseYear().getValue(),
        game.getGameSales().getValue(),
        game.getGameImageUrl().getImageUrl(),
        MapperHelper.getCompanyDtoPublisher(game),
        MapperHelper.getCompanyDtoDeveloper(game),
        MapperHelper.getPlatFormDTo(game));
  }

  private static Set<Platform> getPlatformsFromAddGameRequest(Set<Long> platformIds) {
    if (platformIds != null) return platformIds.stream()
            .map(it -> new Platform(new PlatformIdentifier(it)))
            .collect(Collectors.toSet());
    else return Collections.emptySet();
  }
}
