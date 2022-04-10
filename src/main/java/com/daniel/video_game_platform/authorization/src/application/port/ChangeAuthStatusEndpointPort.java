package com.daniel.video_game_platform.authorization.src.application.port;

import com.daniel.video_game_platform.authorization.src.infrastructure.web.model.JwtResponse;
import com.daniel.video_game_platform.authorization.src.infrastructure.web.model.SignInRequest;
import com.daniel.video_game_platform.authorization.src.infrastructure.web.model.SignUpRequest;
import com.daniel.video_game_platform.authorization.src.infrastructure.web.model.TokenRefreshRequest;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface ChangeAuthStatusEndpointPort {

  String refreshAccessToken(TokenRefreshRequest tokenRefreshRequest);

  JwtResponse signUp(SignUpRequest signUpRequest, String siteUrl)
      throws MessagingException, UnsupportedEncodingException;

  ResponseEntity<Object> sign(SignInRequest signInRequest);
}
