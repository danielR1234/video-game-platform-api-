package com.daniel.video_game_platform.games.src.infrastructure.persistance;

import com.daniel.video_game_platform.games.src.application.port.persistance.PlatformPort;
import com.daniel.video_game_platform.games.src.domain.Platform;
import com.daniel.video_game_platform.games.src.domain.PlatformIdentifier;
import com.daniel.video_game_platform.games.src.infrastructure.persistance.model.PlatFormJpaEntity;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PlatformAdapter implements PlatformPort {

  private final PlatformRepository platformRepository;

  public PlatformAdapter(PlatformRepository platformRepository) {
    this.platformRepository = platformRepository;
  }

  @Override
  public Set<Platform> fetchAllById(Set<Platform> platforms) {
    Set<PlatFormJpaEntity> getPlatforms =
        platforms.stream()
            .map(
                it ->
                        platformRepository
                        .findById(it.getPlatformIdentifier().getValue())
                        .orElseThrow(
                            () -> new EntityNotFoundException("Platform dooes not exists")))
            .collect(Collectors.toSet());

    return getPlatforms.stream()
        .map(it -> new Platform(new PlatformIdentifier(it.getId()), it.getName()))
        .collect(Collectors.toSet());
  }
}
