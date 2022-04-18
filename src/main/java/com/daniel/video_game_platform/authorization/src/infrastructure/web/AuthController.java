package com.daniel.video_game_platform.authorization.src.infrastructure.web;

import com.daniel.video_game_platform.authorization.src.application.port.ChangeAuthStatusEndpointPort;
import com.daniel.video_game_platform.authorization.src.infrastructure.web.model.SignInRequest;
import com.daniel.video_game_platform.authorization.src.infrastructure.web.model.SignUpRequest;
import com.daniel.video_game_platform.authorization.src.infrastructure.web.model.TokenRefreshRequest;
import com.daniel.video_game_platform.authorization.src.infrastructure.web.model.TokenRefreshResponse;
import com.daniel.video_game_platform.user.src.application.helper.SiteUrlFromRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  private final ChangeAuthStatusEndpointPort changeAuthStatusEndpointPort;

  public AuthController(ChangeAuthStatusEndpointPort changeAuthStatusEndpointPort) {
    this.changeAuthStatusEndpointPort = changeAuthStatusEndpointPort;
  }

  @PostMapping("/signup")
  public ResponseEntity<Object> signUpUser(
          @Valid @RequestBody SignUpRequest signUpRequest, HttpServletRequest request)
      throws MessagingException, UnsupportedEncodingException {
    return ResponseEntity.ok(
            changeAuthStatusEndpointPort.signUp(signUpRequest, SiteUrlFromRequest.getSiteURL(request)));
  }

  @PostMapping("/signin")
  public ResponseEntity<Object> signIn(@Valid @RequestBody SignInRequest signInRequest) {
    return ResponseEntity.ok(changeAuthStatusEndpointPort.sign(signInRequest));
  }

  @PostMapping("/token/refresh")
  public ResponseEntity<TokenRefreshResponse> refreshToken(
      @Valid @RequestBody TokenRefreshRequest request) {
    return ResponseEntity.ok(
        new TokenRefreshResponse(changeAuthStatusEndpointPort.refreshAccessToken(request)));
  }
}
