package com.daniel.video_game_platform.authorization.src.application.impl;

import com.daniel.video_game_platform.authorization.src.application.service.LoginUserService;
import com.daniel.video_game_platform.authorization.src.helper.GetRolesHelper;
import com.daniel.video_game_platform.authorization.src.infrastructure.web.model.JwtResponse;
import com.daniel.video_game_platform.authorization.src.security.jwt.JwtUtils;
import com.daniel.video_game_platform.authorization.src.security.services.UserDetailsImpl;
import com.daniel.video_game_platform.user.src.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginUserServiceImpl implements LoginUserService {

  private final AuthenticationManager authenticationManager;
  @Autowired private final JwtUtils jwtUtils;

  public LoginUserServiceImpl(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
    this.authenticationManager = authenticationManager;
    this.jwtUtils = jwtUtils;
  }

  private static JwtResponse getSignInResponse(
          String accessToken, String refreshToken, UserDetailsImpl userDetails, List<String> roles) {
    return new JwtResponse(
        accessToken,
        refreshToken,
        userDetails.getId(),
        userDetails.getUsername(),
        userDetails.getEmail(),
        roles);
  }

  @Override
  public JwtResponse validateUserLogin(User user) throws DisabledException {
    Authentication authentication = getAuthentication(user);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String accessToken = jwtUtils.generateJwtAccessToken(user.getUsername().getUsername());
    String refreshToken = jwtUtils.generateJwtRefreshToken(user.getUsername().getUsername());
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = GetRolesHelper.getRolesFromUserDetails(userDetails);
    return getSignInResponse(accessToken, refreshToken, userDetails, roles);
  }

  private Authentication getAuthentication(User user) {
    return authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            user.getUsername().getUsername(), user.getPassword().getPassword()));
  }
}
