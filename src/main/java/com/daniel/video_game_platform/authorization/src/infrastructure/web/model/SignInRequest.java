package com.daniel.video_game_platform.authorization.src.infrastructure.web.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SignInRequest {
  @NotBlank private String username;

  @NotBlank private String password;
}
