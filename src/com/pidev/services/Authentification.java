package com.pidev.services;

import com.pidev.entities.User;

public interface Authentification {
    User login(String username, String password);
    Boolean signUp(User user);

}
