package ru.kpfu.itis.store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.store.models.Cart;
import ru.kpfu.itis.store.models.Good;
import ru.kpfu.itis.store.models.User;
import ru.kpfu.itis.store.repositories.CartRepository;
import ru.kpfu.itis.store.repositories.GoodRepository;
import ru.kpfu.itis.store.repositories.UserRepository;
import ru.kpfu.itis.store.services.CartService;
import ru.kpfu.itis.store.utils.Auth;

/**
 * Created by perfect on 5/6/16.
 */

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    CartRepository cartRepository;

    @Autowired
    GoodRepository goodRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public void addGood(Long goodId) {
        User user = userRepository.findOne(Auth.getCurrentUser().getId());
        Good good = goodRepository.findOne(goodId);
        Cart cart = cartRepository.findOneByGoodAndUser(good, user);
        if (cart == null) {
            cart = new Cart();
            cart.setGoodAmount(1);
            cart.setUser(user);
            cart.setGood(good);
        }else{
            cart.setGoodAmount(cart.getGoodAmount() + 1);
        }

        cartRepository.save(cart);
    }

    @Override
    @Transactional
    public void addGoodCart(Long goodId, Integer goodAmount) {
        User user = userRepository.findOne(Auth.getCurrentUser().getId());
        Good good = goodRepository.findOne(goodId);
        Cart cart = cartRepository.findOneByGoodAndUser(good, user);
        if (cart == null) {
            cart = new Cart();
            cart.setGoodAmount(goodAmount);
            cart.setUser(user);
            cart.setGood(good);
        }else{
            cart.setGoodAmount(cart.getGoodAmount() + goodAmount);
        }

        cartRepository.save(cart);
    }


    @Override
    @Transactional
    public void deleteGood(Long id) {
        cartRepository.delete(id);
    }


    @Override
    @Transactional
    public void changeGoodAmount(Long cartId, Integer goodAmount) {
        Cart cart = cartRepository.findOne(cartId);
        cart.setGoodAmount(goodAmount);
        cartRepository.save(cart);
    }

}
