package com.daniel.video_game_platform.games.src.application.port.persistance;

import com.daniel.video_game_platform.games.src.domain.Platform;

import java.util.Set;

public interface PlatformPort {

  Set<Platform> fetchAllById(Set<Platform> platforms);
}
