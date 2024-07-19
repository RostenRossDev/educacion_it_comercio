package educacionit.comercio.app.services.impl;

import educacionit.comercio.app.entities.Invoice;
import educacionit.comercio.app.entities.RecordInteraction;
import educacionit.comercio.app.repositories.InteractionRepository;
import educacionit.comercio.app.repositories.InvoiceRepository;
import educacionit.comercio.app.services.InteractionService;
import educacionit.comercio.app.services.InvoiceService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
