package server.services;

import server.entities.UserEntity;

/**
 * Created by Артем on 28.12.2015.
 */
public interface UserService {

    UserEntity login(String login, String password);

    UserEntity loginFromSession();

    void logout();
}
