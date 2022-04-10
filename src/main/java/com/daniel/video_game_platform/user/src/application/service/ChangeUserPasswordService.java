package com.daniel.video_game_platform.user.src.application.service;

import com.daniel.video_game_platform.user.src.domain.EmailAddress;
import com.daniel.video_game_platform.user.src.domain.Password;
import com.daniel.video_game_platform.user.src.domain.PasswordResetToken;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface ChangeUserPasswordService {

  void changePassword(PasswordResetToken passwordResetToken, Password password);

  void sendPasswordResetEmail(EmailAddress emailAddress, String siteURL)
      throws MessagingException, UnsupportedEncodingException;
}
