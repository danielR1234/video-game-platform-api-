package com.daniel.video_game_platform.authorization.src.infrastructure.web.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtResponse {

  private final List<String> roles;
  private String accessToken;
  private String type = "Bearer";
  private String refreshToken;
  private Long id;
  private String username;
  private String email;

  public JwtResponse(
          String accessToken,
          String refreshToken,
          Long id,
          String username,
          String email,
          List<String> roles) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
    this.id = id;
    this.username = username;
    this.email = email;
    this.roles = roles;
  }
}
