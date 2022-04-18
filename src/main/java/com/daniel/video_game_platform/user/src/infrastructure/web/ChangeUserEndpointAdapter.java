package com.daniel.video_game_platform.user.src.infrastructure.web;

import com.daniel.video_game_platform.user.src.application.port.web.ChangeUserEndpointPort;
import com.daniel.video_game_platform.user.src.application.service.ChangeUserPasswordService;
import com.daniel.video_game_platform.user.src.application.service.ChangeUserStatusService;
import com.daniel.video_game_platform.user.src.domain.ConfirmationToken;
import com.daniel.video_game_platform.user.src.domain.EmailAddress;
import com.daniel.video_game_platform.user.src.domain.Password;
import com.daniel.video_game_platform.user.src.domain.PasswordResetToken;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Component
public class ChangeUserEndpointAdapter implements ChangeUserEndpointPort {

  private final ChangeUserStatusService changeUserStatusService;
  private final ChangeUserPasswordService changeUserPasswordService;

  public ChangeUserEndpointAdapter(
          ChangeUserStatusService changeUserStatusUseCase,
          ChangeUserPasswordService changeUserPasswordService) {

    changeUserStatusService = changeUserStatusUseCase;
    this.changeUserPasswordService = changeUserPasswordService;
  }

  @Override
  public void activateUserAccount(ConfirmationToken confirmationToken) {
    changeUserStatusService.activatedUserAccount(confirmationToken);
  }

  @Override
  public void sendPasswordResetEmail(String userEmail, String siteURL)
      throws MessagingException, UnsupportedEncodingException {
    changeUserPasswordService.sendPasswordResetEmail(EmailAddress.of(userEmail), siteURL);
  }

  @Override
  public void resetPassword(String token, String password) {
    changeUserPasswordService.changePassword(new PasswordResetToken(token), new Password(password));
  }
}
