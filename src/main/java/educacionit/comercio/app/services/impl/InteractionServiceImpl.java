package educacionit.comercio.app.services.impl;

import educacionit.comercio.app.entities.baseuno.RecordInteraction;
import educacionit.comercio.app.repositories.baseuno.InteractionRepository;
import educacionit.comercio.app.services.InteractionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class InteractionServiceImpl implements InteractionService {

    @Autowired
    private InteractionRepository repository;

    @Override
    public RecordInteraction save(RecordInteraction interaction) {
        return repository.save(interaction);
    }
}
