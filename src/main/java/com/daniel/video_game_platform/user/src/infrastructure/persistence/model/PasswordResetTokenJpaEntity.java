package com.daniel.video_game_platform.user.src.infrastructure.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "passwordReset_token")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetTokenJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "token_id")
  private long tokenId;

  @Column(name = "confirmation_token")
  private String passwordResetToken;

  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  @Temporal(TemporalType.TIMESTAMP)
  private Date updateAt;

  @OneToOne(targetEntity = UserJpaEntity.class, fetch = FetchType.EAGER)
  @JoinColumn(nullable = false, name = "user_id")
  private UserJpaEntity userJpaEntity;

  public PasswordResetTokenJpaEntity(UserJpaEntity userJpaEntity) {
    this.userJpaEntity = userJpaEntity;
      createdAt = new Date();
      passwordResetToken = UUID.randomUUID().toString();
  }

  public PasswordResetTokenJpaEntity(UserJpaEntity userJpaEntity, String confirmationToken) {
    this.userJpaEntity = userJpaEntity;
      passwordResetToken = confirmationToken;
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
