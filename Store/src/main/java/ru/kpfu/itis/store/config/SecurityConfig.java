package ru.kpfu.itis.store.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.kpfu.itis.store.services.UserService;

@ComponentScan("ru.kpfu.itis.store")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  UserService userService;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
      .antMatchers("/shop").access("hasRole('ROLE_USER')")
      .antMatchers("/cart").access("hasRole('ROLE_USER')")
      .antMatchers("/account").access("hasRole('ROLE_USER')")
      .antMatchers("/checkout").access("hasRole('ROLE_USER')")
      .antMatchers("/goods/**").access("hasRole('ROLE_USER')")
        .and()
      .formLogin().loginPage("/login").failureUrl("/login?error").usernameParameter("username").passwordParameter("password")
        .and()
      .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
        .and()
      .exceptionHandling().accessDeniedPage("/403")
        .and()
      .csrf().disable();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    PasswordEncoder encoder = new BCryptPasswordEncoder();
    return encoder;
  }
}
