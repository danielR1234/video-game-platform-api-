package com.daniel.video_game_platform.games.src.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Year;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Game {

  private GameIdentifier gameIdentifier;
  private String gameName;
  private String gameDescription;
  private GameSales gameSales;
  private Year gameReleaseYear;
  private GameImageUrl gameImageUrl;
  private Date createdAt;
  private Date updateAt;
  private Company publisher;
  private Company developer;
  private Set<Platform> platforms;

  public Game(
          String gameName,
          String gameDescription,
          GameSales gameSales,
          Year gameReleaseYear,
          GameImageUrl gameImageUrl,
          Company publisher,
          Company developer,
          Set<Platform> platforms) {
    this(
        null,
        gameName,
        gameDescription,
        gameSales,
        gameReleaseYear,
        gameImageUrl,
        null,
        null,
        publisher,
        developer,
        platforms);
  }
}
