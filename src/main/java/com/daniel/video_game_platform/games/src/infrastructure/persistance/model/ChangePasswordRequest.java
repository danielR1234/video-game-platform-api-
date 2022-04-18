package com.daniel.video_game_platform.games.src.infrastructure.persistance.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ChangePasswordRequest {

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;
}
