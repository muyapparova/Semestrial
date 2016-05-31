package ru.kpfu.itis.store.services;

/**
 * Created by perfect on 5/6/16.
 */

public interface CartService {

    void addGood(Long goodId);

    void addGoodCart(Long goodId, Integer goodAmount);

    void deleteGood(Long id);

    void changeGoodAmount(Long cartId, Integer goodAmount);

}
