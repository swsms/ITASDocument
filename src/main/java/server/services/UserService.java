package server.services;

import server.entities.UserEntity;


public interface UserService {

    UserEntity login(String login, String password);

    Long register(String name, String login, String password);


//    UserEntity getUserBySessionId(String sessionId);
//
//    void addSession(String sessionId, UserEntity profile);
//
//    void deleteSession(String sessioId);
}
