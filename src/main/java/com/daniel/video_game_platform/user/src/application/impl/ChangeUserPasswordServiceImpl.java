package com.daniel.video_game_platform.user.src.application.impl;

import com.daniel.video_game_platform.Email.src.EmailService;
import com.daniel.video_game_platform.user.src.application.port.persistance.PasswordResetTokenPort;
import com.daniel.video_game_platform.user.src.application.port.persistance.ReadUserPort;
import com.daniel.video_game_platform.user.src.application.port.persistance.WriteUserPort;
import com.daniel.video_game_platform.user.src.application.service.ChangeUserPasswordService;
import com.daniel.video_game_platform.user.src.domain.EmailAddress;
import com.daniel.video_game_platform.user.src.domain.Password;
import com.daniel.video_game_platform.user.src.domain.PasswordResetToken;
import com.daniel.video_game_platform.user.src.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Service
public class ChangeUserPasswordServiceImpl implements ChangeUserPasswordService {
  private final PasswordResetTokenPort passwordResetTokenPort;
  private final PasswordEncoder encoder;
  private final WriteUserPort writeUserPort;
  private final ReadUserPort readUserPort;
  private final EmailService emailService;

  public ChangeUserPasswordServiceImpl(
          PasswordResetTokenPort passwordResetTokenPort,
          PasswordEncoder encoder,
          WriteUserPort writeUserPort,
          ReadUserPort readUserPort,
          EmailService emailService) {
    this.passwordResetTokenPort = passwordResetTokenPort;
    this.encoder = encoder;
    this.writeUserPort = writeUserPort;
    this.readUserPort = readUserPort;
    this.emailService = emailService;
  }

  @Override
  public void changePassword(PasswordResetToken passwordResetToken, Password password) {
    User user = passwordResetTokenPort.fetchByPasswordResetToken(passwordResetToken);
    user.setPassword(Password.of(encoder.encode(password.getPassword())));
    writeUserPort.update(user);
  }

  @Override
  public void sendPasswordResetEmail(EmailAddress emailAddress, String siteURL)
      throws MessagingException, UnsupportedEncodingException {

    User user = readUserPort.fetchByEmail(emailAddress);
    PasswordResetToken passwordResetToken = passwordResetTokenPort.save(user);
    emailService.sendResetPasswordEmail(user, siteURL, passwordResetToken.getPasswordResetToken());
  }
}
