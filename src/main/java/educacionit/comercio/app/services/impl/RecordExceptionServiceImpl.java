package educacionit.comercio.app.services.impl;

import educacionit.comercio.app.entities.baseuno.RecordException;
import educacionit.comercio.app.repositories.baseuno.RecordExceptionRepository;
import educacionit.comercio.app.services.RecordExceptionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class RecordExceptionServiceImpl implements RecordExceptionService {

    @Autowired
    private RecordExceptionRepository repository;

    @Override
    public RecordException save(RecordException exception) {
        return repository.save(exception);
    }
}
