package com.daniel.video_game_platform.games.src.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class GameIdentifier {

  @NotNull Long gameIdentifier;
}
