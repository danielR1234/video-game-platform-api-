package com.daniel.video_game_platform.user.src.infrastructure.persistence;

import com.daniel.video_game_platform.user.src.domain.*;
import com.daniel.video_game_platform.user.src.infrastructure.persistence.model.UserJpaEntity;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

class UserJpaMapper {

  private UserJpaMapper() {}

  static User toDomain(UserJpaEntity userJpaEntity) {
    return new User(
        Username.of(userJpaEntity.getUsername()),
        EmailAddress.of(userJpaEntity.getEmailAddress()),
        Password.of(userJpaEntity.getPassword()),
        getRoleFromUserJpaEntity(userJpaEntity),
        UserIdentifier.of(userJpaEntity.getId()),
        userJpaEntity.isEnabled(),
        userJpaEntity.getCreatedAt(),
        userJpaEntity.getUpdateAt());
  }

  private static Set<Role> getRoleFromUserJpaEntity(UserJpaEntity userJpaEntity) {
    return userJpaEntity.getRoles().stream()
        .map(it -> new Role(it.getName(), it.getId()))
        .collect(Collectors.toSet());
  }

  static UserJpaEntity toJpaEntity(User user, UserJpaEntity persistedUserData) {

    persistedUserData.setUsername(user.getUsername().getUsername());
    persistedUserData.setEmailAddress(user.getEmailAddress().getEmail());
    persistedUserData.setPassword(user.getPassword().getPassword());
    persistedUserData.setEnabled(user.isEnabled());
    persistedUserData.setUpdateAt(new Date());

    return persistedUserData;
  }

  static UserJpaEntity toJpaEntity(User user) {
    return new UserJpaEntity(
        UserFunctions.userUsername.apply(user),
        UserFunctions.userEmailAddress.apply(user),
        UserFunctions.userPassword.apply(user),
        RoleJpaMapper.getRoleJpaCollectionFromUserRoleCollection(user));
  }
}
