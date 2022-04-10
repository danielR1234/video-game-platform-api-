package com.daniel.video_game_platform.authorization.src.application.impl;

import com.daniel.video_game_platform.Email.src.EmailService;
import com.daniel.video_game_platform.authorization.src.application.service.RegisterUserService;
import com.daniel.video_game_platform.authorization.src.helper.GetRolesHelper;
import com.daniel.video_game_platform.authorization.src.infrastructure.web.model.JwtResponse;
import com.daniel.video_game_platform.authorization.src.security.jwt.JwtUtils;
import com.daniel.video_game_platform.user.src.application.impl.SubmitNewUserServiceImpl;
import com.daniel.video_game_platform.user.src.application.port.persistance.ConfirmationTokenPort;
import com.daniel.video_game_platform.user.src.application.port.persistance.ReadRolePort;
import com.daniel.video_game_platform.user.src.domain.ConfirmationToken;
import com.daniel.video_game_platform.user.src.domain.Password;
import com.daniel.video_game_platform.user.src.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {

  private final ReadRolePort readRolePort;
  private final JwtUtils jwtUtils;
  private final SubmitNewUserServiceImpl submitNewUserService;
  private final EmailService emailService;
  private final ConfirmationTokenPort confirmationTokenPort;
  private final PasswordEncoder encoder;

  public RegisterUserServiceImpl(
          ReadRolePort readRolePort,
          JwtUtils jwtUtils,
          SubmitNewUserServiceImpl submitNewUserService,
          EmailService emailService,
          ConfirmationTokenPort confirmationTokenPort,
          PasswordEncoder encoder) {
    this.readRolePort = readRolePort;
    this.jwtUtils = jwtUtils;
    this.submitNewUserService = submitNewUserService;
    this.emailService = emailService;
    this.confirmationTokenPort = confirmationTokenPort;
    this.encoder = encoder;
  }

  private static JwtResponse getSignUpResponse(String accessToken, String refreshToken, User user) {
    return new JwtResponse(
        accessToken,
        refreshToken,
        user.getUserId().getValue(),
        user.getUsername().getUsername(),
        user.getEmailAddress().getEmail(),
        GetRolesHelper.getRolesFromUser(user));
  }

  @Override
  public JwtResponse validateUserRegistration(User user, String siteURL)
      throws MessagingException, UnsupportedEncodingException {
    String accessToken = jwtUtils.generateJwtAccessToken(user.getUsername().getUsername());
    String refreshToken = jwtUtils.generateJwtRefreshToken(user.getUsername().getUsername());
    User newUser = getUser(user);
    ConfirmationToken confirmationToken = confirmationTokenPort.saveNew(newUser);
    emailService.sendVerificationEmail(user, siteURL, confirmationToken.getConfirmationToken());
    return getSignUpResponse(accessToken, refreshToken, newUser);
  }

  private User getUser(User user) {
    user.setRoles(readRolePort.fetchByNameIn(user.getRoles()));
    String encode = encoder.encode(user.getPassword().getPassword());
    user.setPassword(Password.of(encode));
    return submitNewUserService.save(user);
  }
}
