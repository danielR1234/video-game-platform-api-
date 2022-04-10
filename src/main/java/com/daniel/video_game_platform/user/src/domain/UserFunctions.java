package com.daniel.video_game_platform.user.src.domain;

import java.util.Objects;
import java.util.function.Function;

public class UserFunctions {
  public static final Function<Username, String> username = Username::getUsername;
  public static final Function<User, String> userUsername = username.compose(User::getUsername);
  public static final Function<Password, String> password = Password::getPassword;
  public static final Function<User, String> userPassword = password.compose(User::getPassword);
  public static final Function<User, Long> userIdAsLong =
      user -> Objects.isNull(user.getUserId()) ? null : user.getUserId().getLongValue();
  private static final Function<UserIdentifier, Long> userId = UserIdentifier::getValue;
  public static final Function<User, Long> userUserId = userId.compose(User::getUserId);
  private static final Function<EmailAddress, String> emailAddress = EmailAddress::getEmail;
  public static final Function<User, String> userEmailAddress =
      emailAddress.compose(User::getEmailAddress);

  private UserFunctions() {}
}
