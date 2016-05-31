package ru.kpfu.itis.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.kpfu.itis.store.repositories.BrandRepository;
import ru.kpfu.itis.store.repositories.GoodRepository;
import ru.kpfu.itis.store.services.CartService;

/**
 * Created by perfect on 5/7/16.
 */

@Controller
public class CartController {

    @Autowired
    private GoodRepository goodRepo;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CartService cartService;



}
