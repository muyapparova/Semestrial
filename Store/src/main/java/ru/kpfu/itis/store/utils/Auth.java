package ru.kpfu.itis.store.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import ru.kpfu.itis.store.models.User;


public class Auth {

    public static User getCurrentUser() {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }

}
