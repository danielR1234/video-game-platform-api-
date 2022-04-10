package com.daniel.video_game_platform.games.src.infrastructure.persistance.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "video_games")
public class GameJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "createDate", nullable = false)
  private Date createdAt;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updateDate", nullable = false)
  private Date updateAt;

  @Column(name = "name", unique = true)
  private String name;

  @Column(name = "description", length = 3000)
  private String description;

  @Column(name = "release_year", nullable = false)
  private Integer year;

  @Column(name = "global_sales", nullable = false)
  private Long globalSales;

  @Column(name = "imageUrl", length = 1000, nullable = false)
  private String imageUrl;

  @ManyToOne
  @JoinColumn(name = "publisher_id", nullable = false)
  private CompanyJpaEntity publisher;

  @ManyToOne
  @JoinColumn(name = "developer_id", nullable = false)
  private CompanyJpaEntity developer;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "videoGame_platforms",
      joinColumns = @JoinColumn(name = "videoGame_id"),
      inverseJoinColumns = @JoinColumn(name = "platform_id"))
  private Set<PlatFormJpaEntity> platforms = new HashSet<>();

  public GameJpaEntity(
          String name,
          String description,
          Integer year,
          Long globalSales,
          CompanyJpaEntity publisher,
          CompanyJpaEntity developer,
          Set<PlatFormJpaEntity> platforms,
          String imageUrl) {

    this(
        null,
        null,
        null,
        name,
        description,
        year,
        globalSales,
        imageUrl,
        publisher,
        developer,
        platforms);
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
