package server.services;

import server.entities.UserEntity;


public interface UserService {

    UserEntity login(String login, String password);

    UserEntity loginFromSession();

    Long register(String name, String login, String password);

    void logout();
}
