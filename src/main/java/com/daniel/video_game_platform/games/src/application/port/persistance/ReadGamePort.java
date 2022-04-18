package com.daniel.video_game_platform.games.src.application.port.persistance;

import com.daniel.video_game_platform.games.src.domain.Game;

import java.util.List;

public interface ReadGamePort {

  Boolean existsGameByName(Game game);

  List<Game> fetchAll();
}
