package com.daniel.video_game_platform.Email.src;

import com.daniel.video_game_platform.user.src.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
class EmailServiceImpl implements EmailService {

  @Value("${daniel.app.JavaMailSenderSetFromEmail}")
  private static String senderEmail;

  private final JavaMailSender mailSender;

  @Value("${daniel.app.JavaMailSenderName}")
  private String senderName;

  public EmailServiceImpl(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  private static SimpleMailMessage constructEmail(String subject, String body, User user) {
    SimpleMailMessage email = new SimpleMailMessage();
    email.setSubject(subject);
    email.setText(body);
    email.setTo(user.getEmailAddress().getEmail());
    email.setFrom(EmailServiceImpl.senderEmail);
    return email;
  }

  @Override
  public SimpleMailMessage sendVerificationEmail(
          User user, String siteURL, String confirmationToken) {
    final String subject = "Confirm Registration";

    String verifyURL = siteURL + "/api/v1/user/confirmAccount?token=" + confirmationToken;

    String content =
        String.format(
            "Dear %s,<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"%s\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br> %s",
            user.getUsername().getUsername(), verifyURL, senderName);

    EmailServiceImpl.log.info("verifyUrl " + verifyURL);
    var mail = EmailServiceImpl.constructEmail(subject, content, user);
    mailSender.send(mail);
    return mail;
  }

  @Override
  public SimpleMailMessage sendResetPasswordEmail(
          User user, String siteURL, String confirmationToken) {
    final String subject = "Change Password";

    String verifyURL = siteURL + "/api/v1/user/changePassword?token=" + confirmationToken;

    String content =
        String.format(
            "Dear %s,<br>"
                + "Please click the link below to reset your password:<br>"
                + "<h3><a href=\"%s\" target=\"_self\">RESET</a></h3>"
                + "Thank you,<br> %s",
            user.getUsername().getUsername(), verifyURL, senderName);

    EmailServiceImpl.log.info("verifyUrl " + verifyURL);

    var mail = EmailServiceImpl.constructEmail(subject, content, user);
    mailSender.send(mail);
    return mail;
  }
}
