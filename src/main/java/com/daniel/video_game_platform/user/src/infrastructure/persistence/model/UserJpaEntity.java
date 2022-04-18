package com.daniel.video_game_platform.user.src.infrastructure.persistence.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@Entity
@Table(
    name = "users",
    uniqueConstraints = {
      @UniqueConstraint(columnNames = "username"),
      @UniqueConstraint(columnNames = "emailAddress")
    })
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserJpaEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;

  @NotBlank
  @Size(min = 4, max = 20)
  @Column(name = "username", nullable = false)
  private String username;

  @NotBlank
  @Column(name = "emailAddress", nullable = false)
  private String emailAddress;

  @NotBlank
  @Column(name = "password", nullable = false)
  private String password;

  @ManyToMany(fetch = EAGER)
  @JoinTable(
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "roles_id"))
  private Set<RoleJpaEntity> roles = new HashSet<>();

  @Column(name = "isAccountActivated", nullable = false)
  private boolean isEnabled;

  @Column(name = "createDate", nullable = false)
  private Date createdAt;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updateDate", nullable = false)
  private Date updateAt;

  public UserJpaEntity(String username, String emailAddress) {
    this(null, username, emailAddress, null, null, false, null, null);
  }

  public UserJpaEntity(String username, String emailAddress, boolean isEnabled, Date updateAt) {
    this(null, username, emailAddress, null, null, isEnabled, null, updateAt);
  }

  public UserJpaEntity(String username, String emailAddress, String password) {
    this(null, username, emailAddress, password, null, false, null, null);
  }

  public UserJpaEntity(Long id, String username, String emailAddress, String password) {
    this(id, username, emailAddress, password, null, false, null, null);
  }

  public UserJpaEntity(
          String username, String emailAddress, String password, Set<RoleJpaEntity> roles) {
    this(null, username, emailAddress, password, roles, false, null, null);
  }

  public UserJpaEntity(Long id) {
    this.id = id;
  }

  public boolean isEnabled() {
    return isEnabled;
  }

  public Set<RoleJpaEntity> getRoles() {
    return roles;
  }

  @PrePersist
  private void onCreate() {
    createdAt = updateAt = new Date();
  }

  @PreUpdate
  private void onUpdate() {
    updateAt = new Date();
  }
}
