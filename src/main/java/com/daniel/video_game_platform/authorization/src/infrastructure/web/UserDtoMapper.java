package com.daniel.video_game_platform.authorization.src.infrastructure.web;

import com.daniel.video_game_platform.authorization.src.helper.GetRolesHelper;
import com.daniel.video_game_platform.authorization.src.infrastructure.web.model.SignInRequest;
import com.daniel.video_game_platform.authorization.src.infrastructure.web.model.SignUpRequest;
import com.daniel.video_game_platform.user.src.domain.EmailAddress;
import com.daniel.video_game_platform.user.src.domain.Password;
import com.daniel.video_game_platform.user.src.domain.User;
import com.daniel.video_game_platform.user.src.domain.Username;

class UserDtoMapper {

  private UserDtoMapper() {}

  static User getUserFromSignUpRequest(SignUpRequest signUpRequest) {
    return new User(
        Username.of(signUpRequest.getUsername()),
        EmailAddress.of(signUpRequest.getEmail()),
        Password.of(signUpRequest.getPassword()),
        GetRolesHelper.mapRolesClass(signUpRequest.getRoles()),
        false);
  }

  static User getUserFromSignInRequest(SignInRequest signInRequest) {
    return new User(
        Username.of(signInRequest.getUsername()), Password.of(signInRequest.getPassword()));
  }
}
