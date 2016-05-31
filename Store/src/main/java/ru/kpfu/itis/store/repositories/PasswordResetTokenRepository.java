package ru.kpfu.itis.store.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.itis.store.models.PasswordResetToken;

public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, Integer>{

        PasswordResetToken findByToken(String token);

}