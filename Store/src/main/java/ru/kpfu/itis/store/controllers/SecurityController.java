package ru.kpfu.itis.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.store.models.PasswordResetToken;
import ru.kpfu.itis.store.models.User;
import ru.kpfu.itis.store.models.forms.LoginForm;
import ru.kpfu.itis.store.repositories.PasswordResetTokenRepository;
import ru.kpfu.itis.store.repositories.UserAuthorityRepository;
import ru.kpfu.itis.store.repositories.UserRepository;
import ru.kpfu.itis.store.services.UserService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Properties;
import java.util.UUID;

@Controller
public class SecurityController {

  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository userRepo;

  @Autowired
  private UserAuthorityRepository userAuthorityRepo;

  @Autowired
  private PasswordResetTokenRepository passwordResetTokenRepository;
  
  protected String showRegisterForm(ModelMap map){
    map.put("userAuthorities", userAuthorityRepo.findAll());
    return "security/register_form";
  }

  @RequestMapping(value = "/login")
  @PreAuthorize("isAnonymous()")
  public String login(@RequestParam(required = false) String error, @ModelAttribute("loginForm") LoginForm loginForm, BindingResult result, ModelMap map) {
    map.put("error", error);
    return "security/login_form";
  }

  @RequestMapping(value = "/restore", method = RequestMethod.GET)
  public String login(ModelMap map) {
    map.put("user", new User());
    return "security/password";
  }
  @RequestMapping(value = "/restore", method = RequestMethod.POST)
  public String resetPassword(HttpServletRequest request, @ModelAttribute("user") User whoami, RedirectAttributes redirectAttributes) {

    User user = userRepo.findByUsername(whoami.getUsername());
    if(user != null){
      String token = UUID.randomUUID().toString();
      userService.createPasswordResetTokenForUser(user, token);
      String appUrl = "http://" + request.getServerName() +
              ":" + request.getServerPort() +
              request.getContextPath();
      System.out.println(appUrl);
      constructResetTokenEmail(appUrl, token, user);
      redirectAttributes.addFlashAttribute("message", "Check your email for retrieving password. Also, don't forget look into spam folder ;)");
      redirectAttributes.addFlashAttribute("messageType", "success");
      return "redirect:" + MvcUriComponentsBuilder.fromPath("/").build();

    }
    redirectAttributes.addFlashAttribute("message", "E-mail not found.. Try again.");
    redirectAttributes.addFlashAttribute("messageType", "success");
    return "redirect:" + MvcUriComponentsBuilder.fromPath("/restore").build();

  }

  private void constructResetTokenEmail(
          String contextPath, String token, User user) {
    String url = contextPath + "/user/changePassword?id=" + user.getId() + "&token=" + token;

    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.socketFactory.port", "465");
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", "465");

    Session session = Session.getDefaultInstance(props, new Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("sergei.equalizer@gmail.com", "ghjonline357");
      }
    });

    try {
      Message message = new MimeMessage(session);
//от кого
      message.setFrom(new InternetAddress("admin@gmail.com","Admin"));
//кому
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getUsername()));
//тема сообщения
      message.setSubject("Reset Password");
//текст
      message.setText("Reset: " + url);

//отправляем сообщение
      Transport.send(message);
    } catch (MessagingException | UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }


  }

  @RequestMapping(value = "/user/changePassword", method = RequestMethod.GET)
  public String showChangePasswordPage(ModelMap map, @RequestParam("id") long id, @RequestParam("token") String token, RedirectAttributes redirectAttributes) {

    PasswordResetToken passToken = userService.getPasswordResetToken(token);

    if (passToken == null) {
        redirectAttributes.addFlashAttribute("message", "Error! User not found..");
        redirectAttributes.addFlashAttribute("messageType", "danger");
        return "redirect:" + MvcUriComponentsBuilder.fromPath("/").build();
    }

    User user = passToken.getUser();
    if(user.getId() != id) {
      redirectAttributes.addFlashAttribute("message", "Error! User not found..");
      redirectAttributes.addFlashAttribute("messageType", "danger");
      return "redirect:" + MvcUriComponentsBuilder.fromPath("/").build();
    }

    Calendar cal = Calendar.getInstance();

    if (((cal.getTime().getTime() - passToken.getExpiryDate().getTime())) <= 1440) {

      redirectAttributes.addFlashAttribute("message", "Error! Your token expired..");
      redirectAttributes.addFlashAttribute("messageType", "danger");
      return "redirect:" + MvcUriComponentsBuilder.fromPath("/").build();
    }
    Authentication auth = new UsernamePasswordAuthenticationToken(
            user, null, userService.loadUserByUsername(user.getUsername()).getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(auth);
    passwordResetTokenRepository.delete(passToken);
    map.put("user", user);
    return "security/changepassword";
  }

  @RequestMapping(value = "/user/savePassword", method = RequestMethod.POST)
  public String savePassword(@ModelAttribute("user") User whoami, RedirectAttributes redirectAttributes) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if(whoami.getPassword() != null) {
      String password = whoami.getPassword();

      userService.changeUserPassword(user, password);
      redirectAttributes.addFlashAttribute("message", "Password has been changed.. Log in!");
      redirectAttributes.addFlashAttribute("messageType", "danger");
      return "redirect:" + MvcUriComponentsBuilder.fromPath("/").build();
    }
    redirectAttributes.addFlashAttribute("message", "Error! Password hasn't been changed..");
    redirectAttributes.addFlashAttribute("messageType", "danger");
    return "redirect:" + MvcUriComponentsBuilder.fromPath("/").build();
  }

  @RequestMapping(value = "/register", method = RequestMethod.GET)
  @PreAuthorize("isAnonymous()")
  public String register(ModelMap map) {
    map.put("user", new User());
    return showRegisterForm(map);
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  @PreAuthorize("isAnonymous()")
  public String registerHandler(
    RedirectAttributes redirectAttributes,
    @ModelAttribute("user") @Valid User user,
    BindingResult result,
    ModelMap map
  ) {
    if (!result.hasErrors()) {
      try{
        userService.registerUser(user);
        redirectAttributes.addFlashAttribute("message", "You has been registered successfully");
        redirectAttributes.addFlashAttribute("messageType", "success");
        return "redirect:" + MvcUriComponentsBuilder.fromPath("/").build();
      }
      catch(DuplicateKeyException ex){
        result.rejectValue("username", "entry.duplicate", "There is account with such email already.");
      }
    }
    return showRegisterForm(map);
  }

  @RequestMapping("/profile")
  @PreAuthorize("isAuthenticated()")
  public String profile(HttpServletRequest request) {
    return "security/profile";
  }
}
