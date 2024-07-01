package sn.seydina.sendemaildemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.seydina.sendemaildemo.models.Confirmation;

public interface ConfirmationRepository extends JpaRepository<Confirmation, Long> {
    Confirmation findByToken(String token);
}
