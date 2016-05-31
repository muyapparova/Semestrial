package ru.kpfu.itis.store.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.store.models.PasswordResetToken;
import ru.kpfu.itis.store.models.User;
import ru.kpfu.itis.store.models.UserAuthority;
import ru.kpfu.itis.store.repositories.BillingRepository;
import ru.kpfu.itis.store.repositories.PasswordResetTokenRepository;
import ru.kpfu.itis.store.repositories.UserAuthorityRepository;
import ru.kpfu.itis.store.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {



  @Autowired
  private UserRepository userRepo;

  @Autowired
  private BillingRepository billingRepository;

  @Autowired
  private PasswordResetTokenRepository passwordResetTokenRepository;

  @Autowired
  private UserAuthorityRepository userAuthorityRepo;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    return userRepo.findByUsername(username);
  }

  public void registerUser(User user) {
    if(userRepo.findByUsername(user.getUsername()) != null){
      throw new DuplicateKeyException("Duplicate key - username field.");
    }
    user.addAuthority(userAuthorityRepo.findByAuthority("ROLE_USER"));
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setPasswordRepeat(user.getPassword());
    userRepo.save(user);
  }
  
  public User updateFullNameAndAuthorities(Integer id, String fullName, Set<UserAuthority> authorities){
    User user = userRepo.findOne(id);
    if(user == null){
      throw new EntityNotFoundException("User with id " + id + "has not been found.");
    }
    user.setPasswordRepeat(user.getPassword());
    user.setFullName(fullName);
    user.setAuthorities(authorities);
    return user;
  }

  public User changeUserPassword(User user, String password){
    user.setPassword(passwordEncoder.encode(password));
    user.setPasswordRepeat(passwordEncoder.encode(password));
    userRepo.save(user);
    return user;
  }

  public PasswordResetToken createPasswordResetTokenForUser(User user, String token){
    PasswordResetToken passwordResetToken = new PasswordResetToken();
    passwordResetToken.setExpiryDate(java.util.Calendar.getInstance().getTime());
    passwordResetToken.setUser(user);
    passwordResetToken.setToken(token);
    passwordResetTokenRepository.save(passwordResetToken);
    return passwordResetToken;
  }

  public PasswordResetToken getPasswordResetToken(String token){
    PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
    return passwordResetToken;
  }

}
