package com.daniel.video_game_platform.authorization.src.application.service;

import com.daniel.video_game_platform.authorization.src.infrastructure.web.model.JwtResponse;
import com.daniel.video_game_platform.user.src.domain.User;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface RegisterUserService {

  JwtResponse validateUserRegistration(User user, String siteURL)
      throws MessagingException, UnsupportedEncodingException;
}
