package com.daniel.video_game_platform.games.src.infrastructure.persistance;

import com.daniel.video_game_platform.games.src.application.port.persistance.CompanyPort;
import com.daniel.video_game_platform.games.src.domain.Company;
import com.daniel.video_game_platform.games.src.domain.CompanyIdentifier;
import com.daniel.video_game_platform.games.src.infrastructure.persistance.model.CompanyJpaEntity;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class CompanyAdapter implements CompanyPort {

  private final CompanyRepository companyRepository;

  public CompanyAdapter(CompanyRepository companyRepository) {
    this.companyRepository = companyRepository;
  }

  @Override
  public Company fetchById(CompanyIdentifier companyIdentifier) {
    CompanyJpaEntity company =
            companyRepository
            .findById(companyIdentifier.getValue())
            .orElseThrow(() -> new EntityNotFoundException("Company not found"));

    return new Company(new CompanyIdentifier(company.getId()), company.getName());
  }
}
