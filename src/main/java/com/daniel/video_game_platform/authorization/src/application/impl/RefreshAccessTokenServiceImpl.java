package com.daniel.video_game_platform.authorization.src.application.impl;

import com.daniel.video_game_platform.authorization.src.application.service.RefreshAccessTokenService;
import com.daniel.video_game_platform.authorization.src.security.jwt.JwtUtils;
import org.springframework.stereotype.Service;

@Service
public class RefreshAccessTokenServiceImpl implements RefreshAccessTokenService {

  private final JwtUtils jwtUtils;

  public RefreshAccessTokenServiceImpl(JwtUtils jwtUtils) {
    this.jwtUtils = jwtUtils;
  }

  @Override
  public String refreshToken(String token) {
    jwtUtils.validateJwtToken(token);
    String username = jwtUtils.getUserNameFromJwtToken(token);
    return jwtUtils.generateJwtAccessToken(username);
  }
}
