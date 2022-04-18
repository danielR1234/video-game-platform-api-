package com.daniel.video_game_platform.authorization.src.infrastructure.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TokenRefreshResponse {

  private String accessToken;
}
