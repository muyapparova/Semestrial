package ru.kpfu.itis.store.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.itis.store.models.User;

public interface UserRepository extends CrudRepository<User, Integer>{
  User findByUsername(String username);

}