package com.daniel.video_game_platform.games.src.infrastructure.helper;

import com.daniel.video_game_platform.games.src.domain.Game;
import com.daniel.video_game_platform.games.src.domain.Platform;
import com.daniel.video_game_platform.games.src.domain.PlatformIdentifier;
import com.daniel.video_game_platform.games.src.infrastructure.persistance.model.CompanyJpaEntity;
import com.daniel.video_game_platform.games.src.infrastructure.persistance.model.GameJpaEntity;
import com.daniel.video_game_platform.games.src.infrastructure.persistance.model.PlatFormJpaEntity;
import com.daniel.video_game_platform.games.src.infrastructure.web.model.CompanyDto;
import com.daniel.video_game_platform.games.src.infrastructure.web.model.PlatformDto;

import java.util.Set;
import java.util.stream.Collectors;

public class MapperHelper {

  private MapperHelper() {}

  public static Set<PlatFormJpaEntity> getPlatFormGameDomain(Game game) {
    return game.getPlatforms().stream()
        .map(
            it ->
                new PlatFormJpaEntity(
                    it.getPlatformIdentifier().getValue(), String.valueOf(it.getPlatFormName())))
        .collect(Collectors.toSet());
  }

  public static Set<PlatformDto> getPlatFormDTo(Game game) {
    return game.getPlatforms().stream()
        .map(it -> new PlatformDto(it.getPlatformIdentifier().getValue(), it.getPlatFormName()))
        .collect(Collectors.toSet());
  }

  public static CompanyJpaEntity getCompanyJpaEntityPublisher(Game game) {
    return new CompanyJpaEntity(
        game.getPublisher().getCompanyIdentifier().getValue(),
        game.getPublisher().getCompanyName());
  }

  public static CompanyDto getCompanyDtoPublisher(Game game) {
    return new CompanyDto(
        game.getPublisher().getCompanyIdentifier().getValue(),
        game.getPublisher().getCompanyName());
  }

  public static CompanyJpaEntity getCompanyJpaEntityDeveloper(Game game) {
    return new CompanyJpaEntity(
        game.getPublisher().getCompanyIdentifier().getValue(),
        game.getPublisher().getCompanyName());
  }

  public static CompanyDto getCompanyDtoDeveloper(Game game) {
    return new CompanyDto(
        game.getPublisher().getCompanyIdentifier().getValue(),
        game.getPublisher().getCompanyName());
  }

  public static Set<Platform> getPlatFormGameJpaEntitySet(GameJpaEntity gameJpaEntity) {
    return gameJpaEntity.getPlatforms().stream()
        .map(it -> new Platform(new PlatformIdentifier(it.getId()), it.getName()))
        .collect(Collectors.toSet());
  }
}
