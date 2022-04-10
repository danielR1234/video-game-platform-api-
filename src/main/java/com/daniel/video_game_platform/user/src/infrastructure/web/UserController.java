package com.daniel.video_game_platform.user.src.infrastructure.web;

import com.daniel.video_game_platform.games.src.infrastructure.persistance.model.ChangePasswordRequest;
import com.daniel.video_game_platform.user.src.application.helper.SiteUrlFromRequest;
import com.daniel.video_game_platform.user.src.application.port.web.ChangeUserEndpointPort;
import com.daniel.video_game_platform.user.src.application.port.web.GetUserDataEndpointPort;
import com.daniel.video_game_platform.user.src.domain.ConfirmationToken;
import com.daniel.video_game_platform.user.src.infrastructure.web.model.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

  private final ChangeUserEndpointPort changeUserEndpointPort;
  private final GetUserDataEndpointPort getUserDataEndpointPort;

  public UserController(
          ChangeUserEndpointAdapter changeUserEndpointPort,
          GetUserDataEndpointPort getUserDataEndpointPort) {
    this.changeUserEndpointPort = changeUserEndpointPort;
    this.getUserDataEndpointPort = getUserDataEndpointPort;
  }

  @GetMapping("/confirmAccount")
  public ResponseEntity<String> confirmUserAccount(
          @RequestParam("token") String confirmationToken, HttpServletResponse response) {
    changeUserEndpointPort.activateUserAccount(new ConfirmationToken(confirmationToken));
    return ResponseEntity.ok().body("Account activated");
  }

  @PostMapping("/resetPassword")
  public ResponseEntity<String> resetPassword(
          HttpServletRequest request, @RequestParam("email") String userEmail)
      throws MessagingException, UnsupportedEncodingException {
    changeUserEndpointPort.sendPasswordResetEmail(
        userEmail, SiteUrlFromRequest.getSiteURL(request));
    return ResponseEntity.ok().body("Password resend Email send");
  }

  @PostMapping("/changePassword")
  public ResponseEntity<String> changePassword(
          @RequestParam("token") String token, @RequestBody ChangePasswordRequest password) {
    changeUserEndpointPort.resetPassword(token, password.getPassword());
    return ResponseEntity.ok().body("Password resetet");
  }

  @GetMapping
  List<UserDto> getAllUsers() {
    return getUserDataEndpointPort.fetchAll();
  }
}
