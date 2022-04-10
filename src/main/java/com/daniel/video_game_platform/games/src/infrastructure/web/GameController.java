package com.daniel.video_game_platform.games.src.infrastructure.web;

import com.daniel.video_game_platform.games.src.application.port.web.ChangeGameEndpointPort;
import com.daniel.video_game_platform.games.src.application.port.web.CreateNewGameEndpointPort;
import com.daniel.video_game_platform.games.src.application.port.web.GetGamesEndpointPort;
import com.daniel.video_game_platform.games.src.infrastructure.web.model.AddGameRequest;
import com.daniel.video_game_platform.games.src.infrastructure.web.model.GameDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/games")
public class GameController {

  private final CreateNewGameEndpointPort createNewGameEndpointPort;
  private final GetGamesEndpointPort getGamesEndpointPort;
  private final ChangeGameEndpointPort changeGameEndpointPort;

  public GameController(
          CreateNewGameEndpointPort createNewGameEndpointPort,
          GetGamesEndpointPort getGamesEndpointPort,
          ChangeGameEndpointPort changeGameEndpointPort) {
    this.createNewGameEndpointPort = createNewGameEndpointPort;
    this.getGamesEndpointPort = getGamesEndpointPort;
    this.changeGameEndpointPort = changeGameEndpointPort;
  }

  @PostMapping
  GameDto postNewGame(@RequestBody AddGameRequest addGameRequest) {
    return createNewGameEndpointPort.saveNew(addGameRequest);
  }

  @GetMapping
  List<GameDto> getGames() {
    return getGamesEndpointPort.fetchAllGames();
  }

  @DeleteMapping("/{id}")
  ResponseEntity<String> deleteGame(@PathVariable Long id) {
    changeGameEndpointPort.deleteById(id);
    return ResponseEntity.ok().body("Game deleted");
  }

  @PutMapping("/{id}")
  GameDto updateGame(@RequestBody AddGameRequest addGameRequest, @PathVariable Long id) {
    return changeGameEndpointPort.updateGame(addGameRequest, id);
  }
}
