package sn.seydina.sendemaildemo.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sn.seydina.sendemaildemo.models.Confirmation;
import sn.seydina.sendemaildemo.repositories.ConfirmationRepository;
import sn.seydina.sendemaildemo.services.ConfirmationService;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ConfirmationApiImpl implements ConfirmationService {

    private final ConfirmationRepository confirmationRepository;

    @Override
    public List<Confirmation> getAllConfirmations() {
        return this.confirmationRepository.findAll();
    }
}
