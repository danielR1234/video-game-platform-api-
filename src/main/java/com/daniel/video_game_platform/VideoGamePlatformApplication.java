package com.daniel.video_game_platform;

import com.daniel.video_game_platform.games.src.infrastructure.persistance.CompanyRepository;
import com.daniel.video_game_platform.games.src.infrastructure.persistance.GameRepository;
import com.daniel.video_game_platform.games.src.infrastructure.persistance.PlatformRepository;
import com.daniel.video_game_platform.games.src.infrastructure.persistance.model.CompanyJpaEntity;
import com.daniel.video_game_platform.games.src.infrastructure.persistance.model.GameJpaEntity;
import com.daniel.video_game_platform.games.src.infrastructure.persistance.model.PlatFormJpaEntity;
import com.daniel.video_game_platform.user.src.infrastructure.persistence.RoleRepository;
import com.daniel.video_game_platform.user.src.infrastructure.persistence.model.RoleJpaEntity;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.daniel.video_game_platform.user.src.domain.ERole.*;

@SpringBootApplication
@AllArgsConstructor
public class VideoGamePlatformApplication implements CommandLineRunner {

  private final RoleRepository roleRepository;
  private final GameRepository gameRepository;
  private final CompanyRepository companyRepository;
  private final PlatformRepository platformRepository;

  public static void main(String[] args) {
    SpringApplication.run(VideoGamePlatformApplication.class, args);
  }

  @Override
  public void run(String... args) {

    PlatFormJpaEntity microsoftWindows = new PlatFormJpaEntity("Microsoft Windows");
    PlatFormJpaEntity googleStadia = new PlatFormJpaEntity("Google Stadia");
    PlatFormJpaEntity ps3 = new PlatFormJpaEntity("PlayStation 3");
    PlatFormJpaEntity ps4 = new PlatFormJpaEntity("PlayStation 4 ");
    PlatFormJpaEntity ps5 = new PlatFormJpaEntity("PlayStation 5 ");
    PlatFormJpaEntity xbox360 = new PlatFormJpaEntity("XBOX 360");
    PlatFormJpaEntity xboxOne = new PlatFormJpaEntity("XBOX ONE");
    PlatFormJpaEntity nintendoSwitch = new PlatFormJpaEntity("Nintendo Switch");
    PlatFormJpaEntity wiiU = new PlatFormJpaEntity("WII U");
    PlatFormJpaEntity wii = new PlatFormJpaEntity("WII ");
    PlatFormJpaEntity android = new PlatFormJpaEntity("Android ");

    platformRepository.saveAll(
        Stream.of(
                microsoftWindows,
                googleStadia,
                ps3,
                ps4,
                ps5,
                xbox360,
                xboxOne,
                nintendoSwitch,
                wiiU,
                wii,
                android)
            .collect(Collectors.toSet()));

    CompanyJpaEntity nanco = new CompanyJpaEntity("Nanco Bandai Games");
    CompanyJpaEntity nintendo = new CompanyJpaEntity("Nintendo");
    CompanyJpaEntity cdProject = new CompanyJpaEntity("CD Projekt");
    CompanyJpaEntity activision = new CompanyJpaEntity("Activision Square Enix Japan");
    CompanyJpaEntity cyberConnect = new CompanyJpaEntity("CyberConnect2");
    CompanyJpaEntity treyarch = new CompanyJpaEntity("Treyarch");

    companyRepository.saveAll(
        Stream.of(nanco, nintendo, cdProject, activision, cyberConnect, treyarch)
            .collect(Collectors.toSet()));

    GameJpaEntity cod =
        new GameJpaEntity(
            "Call of Duty: Black ops ||",
            "Call of Duty: Black Ops II is first-person shooter video game.Black Ops II begins in the year 2025, where China and the United States are in a cold war after China stops exporting rare earth elements.[4] In this future setting, militaries use robotic weaponry,cyberwarfare, unmanned vehicles and futuristic technology.",
            2012,
            (long) (24.3 * 1000000),
            nanco,
            treyarch,
            Stream.of(microsoftWindows, xbox360, wiiU).collect(Collectors.toSet()),
            "https://c4.wallpaperflare.com/wallpaper/263/276/954/call-of-duty-black-ops-2-wallpaper-preview.jpg");

    GameJpaEntity cyberpunk =
        new GameJpaEntity(
            "Cyberpunk 2077",
            "Cyberpunk 2077 is an action role-playing video game developed and published by CD Projekt. The story takes place in Night City, an open world set in the Cyberpunk universe.Players assume the first-person perspective of a customisable mercenary known as V, who canacquire skills in hacking and machinery with options for melee and ranged combat.",
            2020,
            (long) (13.7 * 1000000),
            cdProject,
            cdProject,
            Stream.of(xboxOne, ps4, ps5, googleStadia, microsoftWindows)
                .collect(Collectors.toSet()),
            "https://blog.notebooksbilliger.de/wp-content/uploads/2020/11/Cyberpunk-2077-PS4-Gameplay-Aufmacher.png");

    GameJpaEntity superMarioGalaxy =
        new GameJpaEntity(
            "Super Mario Galaxy",
            "Super Mario Galaxy[a] is a 2007 platform action-adventure video game developed and published by Nintendo for the Wii. It is the third 3D game in the Super Mario series. As Mario, the player embarks on a quest to rescue Princess Peach, save the universe from Bowser, and collect 120 Power Stars, after which the player can play the game as Luigi for a harderexperience.",
            2007,
            (long) (6.1 * 1000000),
            nintendo,
            nintendo,
            Stream.of(wii, wiiU, android).collect(Collectors.toSet()),
            "https://i.ytimg.com/vi/_O8lFJ2lWTA/maxresdefault.jpg");

    GameJpaEntity ssbb =
        new GameJpaEntity(
            "Super Smash Bros Brawl",
            "Super Smash Bros. Brawl is a 2008 crossover fighting video game developed by SoraLtd. and published by Nintendo for the Wii.[1] The third installment in the Super Smash Bros.series, it was announced at a pre-E3 2005 press conference by Nintendo president SatoruIwata.",
            2008,
            (long) (13.32 * 1000000),
            nintendo,
            nintendo,
            Stream.of(wii).collect(Collectors.toSet()),
            "https://www.teahub.io/photos/full/62-629549_friendships-super-smash-bros.jpg");

    GameJpaEntity naruto =
        new GameJpaEntity(
            "Naruto Shippuden: Ultimate Ninja Storm 22",
            "Naruto Shippuden: Ultimate Ninja Storm 2, known in Japan as Naruto Shippuden:Narutimate Storm 2 (NARUTOナルト 疾風伝 ナルティメットストーム2) is a fighting game developed byCyberConnect2. It is the second installment in the Ultimate Ninja Storm series, and thesequel to Naruto: Ultimate Ninja Storm published by Namco Bandai Games. It is based on theanime and manga series Naruto by Masashi Kishimoto, and was released in mid-2010 for thePlayStation 3 and Xbox 360. ",
            2010,
            (long) (1.1 * 1000000),
            nanco,
            cyberConnect,
            Stream.of(ps4, microsoftWindows, xboxOne, ps3, xbox360, nintendoSwitch)
                .collect(Collectors.toSet()),
            "https://fs-prod-cdn.nintendo-europe.com/media/images/10_share_images/games_15/nintendo_switch_download_software_1/H2x1_NSwitchDS_NarutoShippudenUltimateNinjaStorm2_image1600w.jpg");

    gameRepository.saveAll(Stream.of(cod, cyberpunk, superMarioGalaxy, ssbb, naruto).toList());

    roleRepository.save(new RoleJpaEntity(ADMIN));
    roleRepository.save(new RoleJpaEntity(USER));
    roleRepository.save(new RoleJpaEntity(AUTHOR));
  }
}
