package com.daniel.video_game_platform.user.src.infrastructure.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@AllArgsConstructor
@Setter
public class UserDto {

  private Long id;
  private String username;
  private String emailAddress;
  private String password;
  private boolean isEnabled;
  private Set<String> roles;
  private Date createdAt;
  private Date updateAt;

  public UserDto(
          Long id,
          String username,
          String emailAddress,
          boolean isEnabled,
          Set<String> roles,
          Date createdAt,
          Date updateAt) {
    this(id, username, emailAddress, null, isEnabled, roles, createdAt, updateAt);
  }
}
