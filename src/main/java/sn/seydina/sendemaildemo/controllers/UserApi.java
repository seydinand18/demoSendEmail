package sn.seydina.sendemaildemo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.seydina.sendemaildemo.models.Confirmation;
import sn.seydina.sendemaildemo.models.User;
import java.util.List;

@RequestMapping("/api/v1/users")
public interface UserApi {

    @PostMapping
    ResponseEntity<User> save(User user);

    @GetMapping
    ResponseEntity<List<User>> findAll();

    @GetMapping("/{id}")
    ResponseEntity<User> findById(@PathVariable(name = "id") Long id);

    @GetMapping("/verify/{token}")
    ResponseEntity<Boolean> verifyToken(@PathVariable String token);

    @GetMapping("/confirmations")
    ResponseEntity<List<Confirmation>> getAllConfirmation();

    @DeleteMapping("/{id}")
    void delete(Long id);
}
