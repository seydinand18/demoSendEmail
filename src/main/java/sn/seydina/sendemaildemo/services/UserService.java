package sn.seydina.sendemaildemo.services;

import sn.seydina.sendemaildemo.models.User;
import java.util.List;

public interface UserService {

    User save(User user);

    Boolean verifyToken(String token);

    List<User> findAll();

    User findById(Long id);

    void delete(Long id);
}
