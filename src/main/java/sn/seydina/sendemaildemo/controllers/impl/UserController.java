package sn.seydina.sendemaildemo.controllers.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sn.seydina.sendemaildemo.controllers.UserApi;
import sn.seydina.sendemaildemo.models.Confirmation;
import sn.seydina.sendemaildemo.models.User;
import sn.seydina.sendemaildemo.services.ConfirmationService;
import sn.seydina.sendemaildemo.services.UserService;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;
    private final ConfirmationService confirmationService;

    @Override
    public ResponseEntity<User> save(@Valid @RequestBody User user) {
        User userSaved =  this.userService.save(user);
        return ResponseEntity.created(URI.create("/api/v1/users/"+userSaved.getId()))
                .body(userSaved);
    }

    @Override
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(this.userService.findAll());
    }

    @Override
    public ResponseEntity<User> findById(Long id) {
        return ResponseEntity.ok(this.userService.findById(id));
    }

    @Override
    public ResponseEntity<Boolean> verifyToken(String token) {
        boolean isVerified = this.userService.verifyToken(token);
        return ResponseEntity.ok(isVerified);
    }

    @Override
    public ResponseEntity<List<Confirmation>> getAllConfirmation() {
        List<Confirmation> confirmations = confirmationService.getAllConfirmations();
        return ResponseEntity.ok(confirmations);
    }

    @Override
    public void delete(Long id) {
        if (id == null) throw new IllegalArgumentException("Id cannot be null");
        this.userService.delete(id);
    }
}
