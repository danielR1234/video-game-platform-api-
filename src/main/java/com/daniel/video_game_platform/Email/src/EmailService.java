package com.daniel.video_game_platform.Email.src;

import com.daniel.video_game_platform.user.src.domain.User;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface EmailService {

  SimpleMailMessage sendVerificationEmail(User user, String siteURL, String confirmationToken)
      throws MessagingException, UnsupportedEncodingException;

  SimpleMailMessage sendResetPasswordEmail(User user, String siteURL, String passwordResetToken)
      throws MessagingException, UnsupportedEncodingException;
}
