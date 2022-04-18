package com.daniel.video_game_platform.games.src.domain;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
public class Company {

  CompanyIdentifier companyIdentifier;
  String companyName;

  public Company(CompanyIdentifier companyIdentifier) {
    this.companyIdentifier = companyIdentifier;
  }
}
