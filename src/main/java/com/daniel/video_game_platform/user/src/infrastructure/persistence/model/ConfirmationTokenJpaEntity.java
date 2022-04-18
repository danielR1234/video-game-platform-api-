package com.daniel.video_game_platform.user.src.infrastructure.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "confirmation_token")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmationTokenJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "token_id")
  private long tokenId;

  @Column(name = "confirmation_token")
  private String confirmationToken;

  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  @Temporal(TemporalType.TIMESTAMP)
  private Date updateAt;

  @OneToOne(targetEntity = UserJpaEntity.class, fetch = FetchType.EAGER)
  @JoinColumn(nullable = false, name = "user_id")
  private UserJpaEntity userJpaEntity;

  public ConfirmationTokenJpaEntity(UserJpaEntity userJpaEntity) {
    this.userJpaEntity = userJpaEntity;
    createdAt = new Date();
    confirmationToken = UUID.randomUUID().toString();
  }

  public ConfirmationTokenJpaEntity(UserJpaEntity userJpaEntity, String confirmationToken) {
    this.userJpaEntity = userJpaEntity;
    this.confirmationToken = confirmationToken;
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
