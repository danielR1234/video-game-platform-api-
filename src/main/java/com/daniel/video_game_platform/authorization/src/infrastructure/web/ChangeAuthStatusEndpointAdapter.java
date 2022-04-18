package com.daniel.video_game_platform.authorization.src.infrastructure.web;

import com.daniel.video_game_platform.authorization.src.application.port.ChangeAuthStatusEndpointPort;
import com.daniel.video_game_platform.authorization.src.application.service.LoginUserService;
import com.daniel.video_game_platform.authorization.src.application.service.RefreshAccessTokenService;
import com.daniel.video_game_platform.authorization.src.application.service.RegisterUserService;
import com.daniel.video_game_platform.authorization.src.infrastructure.web.model.JwtResponse;
import com.daniel.video_game_platform.authorization.src.infrastructure.web.model.SignInRequest;
import com.daniel.video_game_platform.authorization.src.infrastructure.web.model.SignUpRequest;
import com.daniel.video_game_platform.authorization.src.infrastructure.web.model.TokenRefreshRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Component
public class ChangeAuthStatusEndpointAdapter implements ChangeAuthStatusEndpointPort {

  private final RegisterUserService registerUserService;
  private final LoginUserService loginUserService;
  private final RefreshAccessTokenService refreshAccessTokenService;

  public ChangeAuthStatusEndpointAdapter(
          RegisterUserService authService,
          LoginUserService loginUserUseCase,
          RefreshAccessTokenService refreshAccessTokenUseCase) {
    registerUserService = authService;
    loginUserService = loginUserUseCase;
    refreshAccessTokenService = refreshAccessTokenUseCase;
  }

  @Override
  public String refreshAccessToken(TokenRefreshRequest tokenRefreshRequest) {
    return refreshAccessTokenService.refreshToken(tokenRefreshRequest.getRefreshToken());
  }

  @Override
  public JwtResponse signUp(SignUpRequest signUpRequest, String siteUrl)
      throws MessagingException, UnsupportedEncodingException {
    return registerUserService.validateUserRegistration(
        UserDtoMapper.getUserFromSignUpRequest(signUpRequest), siteUrl);
  }

  @Override
  public ResponseEntity<Object> sign(SignInRequest signInRequest) {
    try {
      return ResponseEntity.ok(
              loginUserService.validateUserLogin(
              UserDtoMapper.getUserFromSignInRequest(signInRequest)));
    } catch (BadCredentialsException e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Wrong Password or Username");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Confirm your Email please");
    }
  }
}
