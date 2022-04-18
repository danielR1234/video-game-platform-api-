// package com.daniel.video_game_platform.services;
//
// import com.daniel.video_game_platform.Email.src.EmailServiceImpl;
// import com.daniel.video_game_platform.model.AppUser;
// import com.daniel.video_game_platform.services.test_doubles.FakeJavaMailSender;
// import org.hamcrest.CoreMatchers;
// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.Test;
//
// import static org.hamcrest.MatcherAssert.assertThat;
//
// public class EmailServiceImplTest {
//
//  private static EmailServiceImpl emailService() {
//    return new EmailServiceImpl(new FakeJavaMailSender());
//  }
//
//  @Test
//  public void sendVerificationEmail_usesUserMailAsReceiver() {
//
//    // Arrange
//    final var expectedReceiver = "test@lise.de";
//    var user = new AppUser();
//    user.setEmail(expectedReceiver);
//
//    // Act
//    var mail = emailService().sendVerificationEmail(user, "", "");
//
//    // Assert
//    Assertions.assertArrayEquals(new String[] {expectedReceiver}, mail.getTo());
//  }
//
//  @Test
//  public void sendVerificationEmail_mailText_containsUsername() {
//
//    // Arrange
//    var user = new AppUser();
//    final var expectedUsername = "testuser";
//    user.setUsername(expectedUsername);
//
//    // Act
//    var mail = emailService().sendVerificationEmail(user, "", "");
//
//    // Assert
//    assertThat(mail.getText(), CoreMatchers.containsString(expectedUsername));
//  }
//
//  @Test
//  public void sendVerificationEmail_mailText_containsVerifyUrlComposedFromSiteUrlAndToken() {
//
//    // Arrange
//    var user = new AppUser();
//    final var siteUrl = "lise.de";
//    final var token = "very_unique_token";
//
//    // Act
//    var mail = emailService().sendVerificationEmail(user, siteUrl, token);
//
//    // Assert
//    final var expectedVerifyUrl = siteUrl + "/api/v1/user/confirmAccount?token=" + token;
//    assertThat(mail.getText(), CoreMatchers.containsString(expectedVerifyUrl));
//  }
// }
