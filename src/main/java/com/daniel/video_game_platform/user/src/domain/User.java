package com.daniel.video_game_platform.user.src.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Set;

@Getter
@AllArgsConstructor
@ToString
@Setter
public class User {

  private Username username;
  private EmailAddress emailAddress;
  private Password password;
  private Set<Role> roles;
  private UserIdentifier userId;
  private boolean isEnabled;
  private Date createdAt;
  private Date updatedAt;

  public User(
          Username username,
          EmailAddress emailAddress,
          Password password,
          Set<Role> roles,
          boolean isEnabled) {
    this.username = username;
    this.emailAddress = emailAddress;
    this.password = password;
    this.roles = roles;
    this.isEnabled = isEnabled;
  }

  public User(Username username, EmailAddress emailAddress, Password password) {
    this(username, emailAddress, password, null, null, false, null, null);
  }

  public User(
          Username username,
          EmailAddress emailAddress,
          UserIdentifier id,
          boolean isEnabled,
          Set<Role> roles,
          Date createdAt,
          Date updatedAt) {
    this(username, emailAddress, null, roles, id, isEnabled, createdAt, updatedAt);
  }

  public User(Username username, EmailAddress emailAddress, boolean isEnabled) {
    this(username, emailAddress, null, null, null, isEnabled, null, null);
  }

  public User(Username username, EmailAddress emailAddress, Password password, Set<Role> roles) {
    this(username, emailAddress, password, roles, null, false, null, null);
  }

  public User(Username username, Password password) {
    this(username, null, password, null, null, false, null, null);
  }

  public Set<Role> getRoles() {
    return roles;
  }
}
