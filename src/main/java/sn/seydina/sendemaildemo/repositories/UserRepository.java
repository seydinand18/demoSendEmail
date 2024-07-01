package sn.seydina.sendemaildemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.seydina.sendemaildemo.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailIgnoreCase(String email);
    boolean existsByEmail(String email);
}
