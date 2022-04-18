package com.daniel.video_game_platform.authorization.src.security.services;

import com.daniel.video_game_platform.user.src.infrastructure.persistence.model.UserJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDetailsImpl implements UserDetails {

  private Long id;
  private String username;
  private String email;
  private String password;
  private Boolean isEnabled;
  private Collection<? extends GrantedAuthority> authorities;

  private UserDetailsImpl(
          Long id,
          String username,
          String email,
          String password,
          Boolean isEnabled,
          List<GrantedAuthority> authorities) {

    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.isEnabled = isEnabled;
    this.authorities = authorities;
  }

  static UserDetailsImpl build(UserJpaEntity userJpaEntity) {
    List<SimpleGrantedAuthority> authorities =
        userJpaEntity.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getName().name()))
            .toList();

    return new UserDetailsImpl(
        userJpaEntity.getId(),
        userJpaEntity.getUsername(),
        userJpaEntity.getEmailAddress(),
        userJpaEntity.getPassword(),
        userJpaEntity.isEnabled(),
        authorities);
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return isEnabled;
  }
}
