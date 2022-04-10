package com.daniel.video_game_platform.authorization.src.infrastructure.web.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class TokenRefreshRequest {

  private String refreshToken;
}
