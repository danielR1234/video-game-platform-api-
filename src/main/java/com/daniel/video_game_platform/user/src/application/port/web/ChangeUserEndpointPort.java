package com.daniel.video_game_platform.user.src.application.port.web;

import com.daniel.video_game_platform.user.src.domain.ConfirmationToken;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface ChangeUserEndpointPort {

  void activateUserAccount(ConfirmationToken confirmationToken);

  void sendPasswordResetEmail(String userEmail, String siteURL)
      throws MessagingException, UnsupportedEncodingException;

  void resetPassword(String token, String password);
}
