package com.daniel.video_game_platform.games.src.application.port.web;

import com.daniel.video_game_platform.games.src.infrastructure.web.model.GameDto;

import java.util.List;

public interface GetGamesEndpointPort {

  List<GameDto> fetchAllGames();
}
