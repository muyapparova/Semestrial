package ru.kpfu.itis.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.store.models.Cart;
import ru.kpfu.itis.store.models.Good;
import ru.kpfu.itis.store.models.User;

import java.util.List;

/**
 * Created by perfect on 5/6/16.
 */

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findOneByGoodAndUser(Good good, User user);

    List<Cart> findAllByUser(User currentUser);
}
