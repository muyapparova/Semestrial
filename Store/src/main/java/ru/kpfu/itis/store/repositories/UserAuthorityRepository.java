package ru.kpfu.itis.store.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.itis.store.models.UserAuthority;

public interface UserAuthorityRepository extends CrudRepository<UserAuthority, Integer> {

  public UserAuthority findByAuthority(String role_user);
}
